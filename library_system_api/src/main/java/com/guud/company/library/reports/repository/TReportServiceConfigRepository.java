package com.guud.company.library.reports.repository;

import com.guud.company.library.reports.model.TReportServiceConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TReportServiceConfigRepository extends JpaRepository<TReportServiceConfig, Long> {

    @Query(value = "select * from treport_service_config where val = ?1 and service_name = ?2 and rec_status ='A' ", nativeQuery = true)
    TReportServiceConfig getReportServiceByValAndServiceName(String val, String serviceName);
}
