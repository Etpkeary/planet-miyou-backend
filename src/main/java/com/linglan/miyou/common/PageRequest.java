package com.linglan.miyou.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用分页请求参数
 *
 * @author Linglan
 */
@Data
public class PageRequest implements Serializable {

    /**
     * @description: 盐值，混淆密码
     */
    private static final long serialVersionUID = -5860707094194210842L;

    /**
     * @description: 页面大小
     */
    protected int pageSize = 10;

    /**
     * @description: 当前是第几页
     */
    protected int pageNum = 1;
}
