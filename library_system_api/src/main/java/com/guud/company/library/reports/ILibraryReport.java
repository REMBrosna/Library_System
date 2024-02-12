package com.guud.company.library.reports;

import org.apache.poi.ss.usermodel.Workbook;

public interface ILibraryReport {
    byte[] excel(String filter) throws Exception;

    byte[] pdf(String filter) throws Exception;

    void excelRowMapping(Workbook workbook, String filter) throws Exception;
}
