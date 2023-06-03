package com.baedal.monolithic.global.util.replication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

    private static final String READ = "read";
    private static final String WRITE = "write";
    private final ReadOnlyDataSourceCycle<String> readOnlyDataSourceCycle = new ReadOnlyDataSourceCycle<>();

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        List<String> readOnlyDataSourceLookupKeys = targetDataSources.keySet()
                .stream()
                .map(String::valueOf)
                .filter(lookupKey -> lookupKey.contains(READ))
                .collect(Collectors.toList());
        readOnlyDataSourceCycle.setReadOnlyDataSourceLookupKeys(readOnlyDataSourceLookupKeys);
    }

    @Override
    public Object determineCurrentLookupKey() {
        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();

        if (isReadOnly) {
            log.info("read DB 연결");
            return readOnlyDataSourceCycle.getReadOnlyDataSourceLookupKey();
        } else {
            log.info("write DB 연결");
            return WRITE;
        }

    }
}