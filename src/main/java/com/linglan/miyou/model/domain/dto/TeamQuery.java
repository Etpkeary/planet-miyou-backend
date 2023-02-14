package com.linglan.miyou.model.domain.dto;

import com.linglan.miyou.common.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: 队伍查询封装类
 * @author LingLan
 */
@Data
public class TeamQuery extends PageRequest {
    /**
     * id
     */
    @ApiModelProperty(value = "根据队伍查询id", required = true)
    private Long id;

    /**
     * id 列表
     */
    @ApiModelProperty(value = "根据队伍id列表", required = true)
    private List<Long> idList;

    /**
     * 搜索关键词（同时对队伍名称和描述搜索）
     */
    @ApiModelProperty(value = "搜索关键词", required = false)
    private String searchText;

    /**
     * 队伍名称
     */
    @ApiModelProperty(value = "搜索关键词", required = true)
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
     * 用户id
     */
    @ApiModelProperty(value = "用户id", required = true)
    private Long userId;

    /**
     * 0 - 公开，1 - 私有，2 - 加密
     */
    @ApiModelProperty(value = "0 - 公开，1 - 私有，2 - 加密", required = true)
    private Integer status;

}
