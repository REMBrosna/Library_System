package com.guud.company.library.reports.repository;

import com.guud.company.library.reports.model.TReportTemplateConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TReportTemplateConfigRepository extends JpaRepository<TReportTemplateConfig, Long> {

    @Query(value = "select * from treport_template_config where service = ?1 and rec_status ='A' ", nativeQuery = true)
    List<TReportTemplateConfig> getReportTemplateByServiceId(Long service);
}
