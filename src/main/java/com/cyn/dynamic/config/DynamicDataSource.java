package com.cyn.dynamic.config;

import com.cyn.dynamic.utils.DynamicDataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author Godc
 * @description:
 * @date 2023/6/19/0019 13:45
 */
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {


    public DynamicDataSource(DataSourceConfig dataSourceConfig) {
        // 1.设置所有数据源
        HashMap<Object, Object> allDataSource = new HashMap<>(dataSourceConfig.loadAllDataSource());
        super.setTargetDataSources(allDataSource);
        // 2.设置默认数据源 没加注解的方法该使用哪个数据源
//        super.setDefaultTargetDataSource(allDataSource.get(DataSourceType.DEFAULT_DATA_SOURCE));
        //
        super.afterPropertiesSet();
    }

    /**
     * 返回数据源名称,当系统需要获取数据源时,会自动调用该方法获取数据源的名称。
     *
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSource();
    }
}
