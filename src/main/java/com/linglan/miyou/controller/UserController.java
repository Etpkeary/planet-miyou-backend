package com.linglan.miyou.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linglan.miyou.common.BaseResponse;
import com.linglan.miyou.common.ErrorCode;
import com.linglan.miyou.common.ResultUtils;
import com.linglan.miyou.contant.UserConstant;
import com.linglan.miyou.model.domain.User;
import com.linglan.miyou.model.domain.request.UserLoginRequest;
import com.linglan.miyou.model.domain.request.UserRegisterRequest;
import com.linglan.miyou.exception.BusinessException;
import com.linglan.miyou.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 用户接口
 *
 * @author Linglan
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://127.0.0.1:5173/"},allowCredentials = "true")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @param userRegisterRequest 用户注册请求体
     * @description: 用户请求
     * @return result
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        if (!userPassword.equals(checkPassword)) {
            return ResultUtils.error(ErrorCode.ACCOUNT_OR_PASSWORD_ERROR);
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }

    /**
     * @param userLoginRequest 用户登录请求体
     * @param request 请求体
     * @description: 用户登录
     * @return user
     */
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    /**
     * @param request 请求体
     * @description: 用户退出
     * @return result
     */
    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        int result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    /**
     * @param request 请求体
     * @description: 获取当前用户
     * @return safetyUser
     */
    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        long userId = currentUser.getId();
        User user = userService.getById(userId);
        User safetyUser = userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }

    /**
     * @param username 用户名字
     * @param request 请求体
     * @description: 查询用户
     * @return list
     */
    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {
        if (isAdmin(request)) {
           throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> userList = userService.list(queryWrapper);
        List<User> list = userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
        return ResultUtils.success(list);
    }

    /**
     * @param user 用户
     * @param request 请求体
     * @description: 更新用户信息
     * @return result
     */
    @PostMapping("/update")
    public BaseResponse<Integer> updateUser(User user,HttpServletRequest request){
        //1. 校验参数是否为空
        if (user == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getCurrentUser(request);
        int result = userService.updateUser(user, loginUser);
        return ResultUtils.success(result);
    }

    /**
     * @param id 用户id
     * @param request 请求体
     * @description: 删除用户
     * @return delete
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request) {
        if (isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean delete = userService.removeById(id);
        return ResultUtils.success(delete);
    }

    /**
     * @param tagNameList 标签名字列表
     * @description: 根据标签查询用户
     * @return userList
     */
    @GetMapping("/search/tags")
    public BaseResponse<List<User>> searchUserByTags(@RequestParam(required = false) List<String> tagNameList){
        if (CollectionUtils.isEmpty(tagNameList)){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        List<User> userList = userService.searchUsersByTags(tagNameList);
        return ResultUtils.success(userList);
    }

    /**
     * @param pageSize 每页显示多少条
     * @param pageNum 页数
     * @param request 请求体
     * @description: 推荐用户
     * @return userPage
     * todo 推荐多个，未实现
     */
    @GetMapping("/recommend")
    public BaseResponse<Page<User>> recommendUsers(long pageSize,long pageNum,HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        String redisKey = String.format("miyou:user:recommend:%s", loginUser.getId());
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        // 如果有缓存，直接读缓存
        Page<User> userPage = (Page<User>) valueOperations.get(redisKey);
        if (userPage != null) {
            return ResultUtils.success(userPage);
        }
        // 无缓存，查数据库
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        userPage = userService.page(new Page<>(pageNum, pageSize), queryWrapper);
        // 写缓存
        try {
            valueOperations.set(redisKey, userPage, 30000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.error("redis set key error", e);
        }
        return ResultUtils.success(userPage);
    }

    /**
     * @param request 请求体
     * @description: 是否为管理员
     * @return 用户等于空并且用户权限不等于 权限 1
     */
    private boolean isAdmin(HttpServletRequest request) {
        // 仅管理员可查询
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User user = (User) userObj;
        return user == null || user.getUserRole() != UserConstant.ADMIN_ROLE;
    }

    /**
     * @param num 匹配用户限制的大小
     * @param request 请求体
     * @description: 获取最匹配的用户
     * @return userService.matchUsers(num, user)
     */
    @GetMapping("/match")
    public BaseResponse<List<User>> matchUsers(long num, HttpServletRequest request) {
        if (num <= 0 || num > 20) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        return ResultUtils.success(userService.matchUsers(num, user));
    }
}
