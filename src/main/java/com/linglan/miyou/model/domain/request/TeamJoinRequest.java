package com.linglan.miyou.model.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户加入队伍请求体
 *
 * @author linglan
 */
@Data
public class TeamJoinRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    /**
     * id
     */
    @ApiModelProperty(value = "加入的队伍id", required = true)
    private Long teamId;

    /**
     * 密码
     */
    @ApiModelProperty(value = "0 - 公开，1 - 私有，2 - 加密", required = false)
    private String password;
}
