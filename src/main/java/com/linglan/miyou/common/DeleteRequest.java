package com.linglan.miyou.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用删除请求
 *
 * @author Linglan
 */
@Data
public class DeleteRequest implements Serializable {

    /**
     * @description: 盐值，混淆密码
     */
    private static final long serialVersionUID = -5860707094194210842L;

    @ApiModelProperty(value = "要删除队伍的id", required = true)
    private long id;
}
