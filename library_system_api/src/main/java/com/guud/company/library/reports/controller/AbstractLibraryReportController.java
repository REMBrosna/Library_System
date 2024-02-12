package com.guud.company.library.reports.controller;

import com.guud.company.library.reports.ILibraryReport;
import com.guud.company.library.reports.payload.BookRequest;
import com.guud.company.library.reports.utils.EnumReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public abstract class AbstractLibraryReportController {

    @Autowired
    protected ApplicationContext applicationContext;

    public ResponseEntity<Object> exportExcel(BookRequest filter) throws Exception {

        EnumReportService enumReportService = EnumReportService.valueOf(filter.getReportId());
        Object bean = applicationContext.getBean(enumReportService.getServiceName());
        ILibraryReport fileApplication = (ILibraryReport) bean;

        ByteArrayResource byteArrayOutputStream = new ByteArrayResource(fileApplication.excel(filter.getReportId()));
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "force-download"));
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + enumReportService.getReportName() + ".xlsx");
        return ResponseEntity.ok().headers(header)
                .contentType(MediaType.APPLICATION_OCTET_STREAM).
                        body(byteArrayOutputStream.getByteArray());
    }

    public ResponseEntity<Object> exportPdf(BookRequest filter) throws Exception {

        EnumReportService enumReportService = EnumReportService.valueOf(filter.getReportId());
        Object bean = applicationContext.getBean(enumReportService.getServiceName());
        ILibraryReport fileApplication = (ILibraryReport) bean;

        ByteArrayResource byteArrayOutputStream = new ByteArrayResource(fileApplication.pdf(filter.getReportId()));
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "force-download"));
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + enumReportService.getReportName() + ".pdf");
        return ResponseEntity.ok().headers(header)
                .contentType(MediaType.APPLICATION_OCTET_STREAM).
                        body(byteArrayOutputStream.getByteArray());
    }
}
