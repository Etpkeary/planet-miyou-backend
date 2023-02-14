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
    @TableId(type = IdType.AUTO)
    private long id;

    /**
     * @description: 用户昵称
     */

    private String username;

    /**
     * @description: 用户账号
     */

    private String userAccount;

    /**
     * @description: 用户头像
     */

    private String avatarUrl;

    /**
     * @description: 性别
     */

    private Integer gender;

    /**
     * @description: 密码
     */

    private String userPassword;

    /**
     * @description: 电话
     */

    private String phone;

    /**
     * @description: 邮箱
     */

    private String email;

    /**
     * @description: 状态 0 - 正常
     */

    private Integer userStatus;

    /**
     * @description: 标签列表 json
     */

    private String tags;

    /**
     * @description: 创建时间
     */

    private Date createTime;

    /**
     * @description: 更新时间
     */

    private Date updateTime;

    /**
     * @description: 是否删除
     */

    @TableLogic
    private Integer isDelete;

    /**
     * @description: 用户角色 0 - 普通用户 1 - 管理员
     */

    private Integer userRole;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}