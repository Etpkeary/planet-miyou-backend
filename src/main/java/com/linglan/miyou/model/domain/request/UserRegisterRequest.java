package com.linglan.miyou.model.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 *
 * @author Linglan
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号", required = true)
    private String userAccount;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码", required = true)
    private String userPassword;

    /**
     * 校验密码
     */
    @ApiModelProperty(value = "校验密码", required = true)
    private String checkPassword;

}
