package com.cyn.dynamic.utils;

/**
 * @author Godc
 * @description: 存放当前线程所使用数据源的名字
 * @date 2023/6/19/0019 10:44
 */

public class DynamicDataSourceContextHolder {
    /**
     * 使用ThreadLocal存储选择的数据源
     * 1.用户隔离，且是动态的
     */
    private static ThreadLocal<String> DATA_SOURCE = new ThreadLocal<>();

    /**
     * 设置数据源
     * @param dataSource
     */
    public static void setDataSource(String dataSource) {
        DATA_SOURCE.set(dataSource);
    }

    /**
     * 获取当前数据源
     * @return
     */
    public static String getDataSource() {
        return DATA_SOURCE.get();
    }

    /**
     * 线程结束后 释放内存，防止内存泄露。
     */
    public static void removeDataSource() {
        DATA_SOURCE.remove();
    }
}
