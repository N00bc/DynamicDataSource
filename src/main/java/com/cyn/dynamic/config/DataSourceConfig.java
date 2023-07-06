package com.cyn.dynamic.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.cyn.dynamic.properties.DruidProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Godc
 * @description:
 * @date 2023/6/19/0019 13:18
 */
@Component
@EnableConfigurationProperties(DruidProperties.class)
public class DataSourceConfig {
    @Autowired
    private DruidProperties druidProperties;

    public Map<String, DataSource> loadAllDataSource() {
        Map<String, DataSource> map = new HashMap<>();
        Map<String, Map<String, String>> dataSource = druidProperties.getDataSource();
        try {
            Set<String> dataSourceNames = dataSource.keySet();
            for (String dataSourceName : dataSourceNames) {
                // DruidDataSourceFactory可以通过一个Map创建一个DruidDataSource源
                map.put(dataSourceName, druidProperties.dataSource((DruidDataSource) DruidDataSourceFactory.createDataSource(dataSource.get(dataSourceName))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
