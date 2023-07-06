package com.cyn.dynamic.anno;

import com.cyn.dynamic.constants.DataSourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Godc
 * @description: 该注解将放置在类/方法上，用于指定使用的数据源。
 * @date 2023/6/19/0019 10:40
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DynamicDataSource {
    String dataSource() default DataSourceType.DEFAULT_DATA_SOURCE;
}
