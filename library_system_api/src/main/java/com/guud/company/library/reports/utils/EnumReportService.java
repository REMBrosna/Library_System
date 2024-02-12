package com.guud.company.library.reports.utils;

public enum EnumReportService {

    BOOK("bookReportServiceImpl", "Book Report");

    private final String serviceName;
    private final String reportName;

    EnumReportService(String serviceName, String reportName) {
        this.serviceName = serviceName;
        this.reportName = reportName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getReportName() {
        return reportName;
    }
}
