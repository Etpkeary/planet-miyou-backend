package com.linglan.miyou.model.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 队伍更新请求体
 *
 * @author linglan
 */
@Data
public class TeamUpdateRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    /**
     * id
     */
    @ApiModelProperty(value = "要获取队伍的id", required = true)
    private Long id;

    /**
     * 队伍名称
     */
    @ApiModelProperty(value = "队伍名称", required = true)
    private String name;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", required = true)
    private String description;

    /**
     * 过期时间
     */
    @ApiModelProperty(value = "过期时间", required = true)
    private Date expireTime;

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
