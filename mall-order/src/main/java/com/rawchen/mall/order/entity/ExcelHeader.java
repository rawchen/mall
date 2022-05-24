package com.rawchen.mall.order.entity;

import java.lang.annotation.*;

/**
 * @ClassName ExcelHeader
 * @Author zhaokz
 * @Date 2021/10/18 11:10
 * @Description ExcelHeader
 * @Version 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelHeader {

    /**
     * 表头
     * @return
     */
    String value() default "";

    /**
     * 列索引
     * @return
     */
    int columnIndex() default 0;

}
