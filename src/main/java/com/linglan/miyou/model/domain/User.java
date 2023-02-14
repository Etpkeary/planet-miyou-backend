package com.linglan.miyou.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * @author LingLan
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * @description: id
     */
    @ApiModelProperty(value = "id", required = true)
    @TableId(type = IdType.AUTO)
    private long id;

    /**
     * @description: 用户昵称
     */
    @ApiModelProperty(value = "用户昵称", required = true)
    private String username;

    /**
     * @description: 用户账号
     */
    @ApiModelProperty(value = "用户账号", required = true)
    private String userAccount;

    /**
     * @description: 用户头像
     */
    @ApiModelProperty(value = "用户头像", required = true)
    private String avatarUrl;

    /**
     * @description: 性别
     */
    @ApiModelProperty(value = "性别", required = true)
    private Integer gender;

    /**
     * @description: 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    private String userPassword;

    /**
     * @description: 电话
     */
    @ApiModelProperty(value = "电话", required = true)
    private String phone;

    /**
     * @description: 邮箱
     */
    @ApiModelProperty(value = "邮箱", required = true)
    private String email;

    /**
     * @description: 状态 0 - 正常
     */
    @ApiModelProperty(value = "状态 0 - 正常", required = true)
    private Integer userStatus;

    /**
     * @description: 标签列表 json
     */
    @ApiModelProperty(value = "标签列表 json", required = true)
    private String tags;

    /**
     * @description: 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    /**
     * @description: 更新时间
     */
    @ApiModelProperty(value = "更新时间", required = true)
    private Date updateTime;

    /**
     * @description: 是否删除
     */
    @ApiModelProperty(value = "是否删除", required = true)
    @TableLogic
    private Integer isDelete;

    /**
     * @description: 用户角色 0 - 普通用户 1 - 管理员
     */
    @ApiModelProperty(value = "用户角色 0 - 普通用户 1 - 管理员", required = true)
    private Integer userRole;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}