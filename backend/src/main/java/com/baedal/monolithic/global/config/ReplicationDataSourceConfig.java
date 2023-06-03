package com.baedal.monolithic.global.config;

import com.baedal.monolithic.global.util.replication.ReplicationDataSourceProperties;
import com.baedal.monolithic.global.util.replication.ReplicationRoutingDataSource;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ReplicationDataSourceConfig {

    private final ReplicationDataSourceProperties replicationDataSourceProperties;

    @Bean
    public DataSource routingDataSource() {

        // 쓰기 db 생성
        ReplicationRoutingDataSource replicationRoutingDataSource = new ReplicationRoutingDataSource();

        ReplicationDataSourceProperties.Write write = replicationDataSourceProperties.getWrite();
        DataSource writeDataSource = createDataSource(write.getUrl());

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(write.getName(), writeDataSource);

        // 읽기 db 생성
        List<ReplicationDataSourceProperties.Read> reads = replicationDataSourceProperties.getReads();

        for (ReplicationDataSourceProperties.Read read : reads) {
            dataSourceMap.put(read.getName(), createDataSource(read.getUrl()));
        }

        replicationRoutingDataSource.setDefaultTargetDataSource(writeDataSource);
        replicationRoutingDataSource.setTargetDataSources(dataSourceMap);
        replicationRoutingDataSource.afterPropertiesSet();

        return new LazyConnectionDataSourceProxy(replicationRoutingDataSource);
    }

    private DataSource createDataSource(String url) {

        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(replicationDataSourceProperties.getDriverClassName());
        hikariDataSource.setUsername(replicationDataSourceProperties.getUsername());
        hikariDataSource.setPassword(replicationDataSourceProperties.getPassword());
        hikariDataSource.setJdbcUrl(url);
        hikariDataSource.setMaximumPoolSize(replicationDataSourceProperties.getMaximumPoolSize());
        hikariDataSource.setConnectionTimeout(replicationDataSourceProperties.getConnectionTimeout());

        return hikariDataSource;
    }
}
