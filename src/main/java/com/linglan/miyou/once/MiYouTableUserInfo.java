package com.linglan.miyou.once;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 星球表格用户信息
 * @author LingLan
 */
@Data
@EqualsAndHashCode
public class MiYouTableUserInfo {

    /**
     * id
     */
    @ExcelProperty(index = 0)
    private String planetCode;
    /**
     * 用户昵称
     */
    @ExcelProperty(index = 1)
    private String username;
}

