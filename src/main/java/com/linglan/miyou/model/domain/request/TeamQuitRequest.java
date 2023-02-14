package com.linglan.miyou.model.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户退出队伍请求体
 *
 * @author linglan
 */
@Data
public class TeamQuitRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    /**
     * id
     */
    @ApiModelProperty(value = "推出的队伍id", required = true)
    private Long teamId;

}
