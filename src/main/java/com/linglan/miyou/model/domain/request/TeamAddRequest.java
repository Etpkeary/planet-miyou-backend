package com.linglan.miyou.model.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录请求体
 *
 * @author Linglan
 */
@Data
public class TeamAddRequest implements Serializable {


    /**
     * 盐值,混淆密码
     */
    private static final long serialVersionUID = 3191241716373120793L;

    /**
     * 队伍名称
     */
    @ApiModelProperty(value = "用户名", required = true)
    private String name;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", required = true)
    private String description;

    /**
     * 最大人数
     */
    @ApiModelProperty(value = "最大人数", required = true)
    private Integer maxNum;

    /**
     * 过期时间
     */
    @ApiModelProperty(value = "过期时间", required = true)
    private Date expireTime;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", required = true)
    private Long userId;

    /**
     * 0 - 公开，1 - 私有，2 - 加密
     */
    @ApiModelProperty(value = "0 - 公开，1 - 私有，2 - 加密", required = true)
    private Integer status;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = false)
    private String password;
}
