package com.guud.company.library.reports.service.impl;

import com.guud.company.library.master.book.model.TBook;
import com.guud.company.library.master.book.service.BookService;
import com.guud.company.library.reports.AbstractCoreLibraryReport;
import com.guud.company.library.reports.dto.print.book.BookDTO;
import com.guud.company.library.reports.dto.print.book.BookHelperDTO;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
public class BookReportServiceImpl extends AbstractCoreLibraryReport {

    // Static Attributes
    ////////////////////
    public static final String ENTITY_NAME = "BookReportServiceImpl";
    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    // Attributes
    ////////////////////
    @Autowired
    private BookService bookService;

    @Override
    public byte[] excel(String filter) throws Exception {
        return super.exportExcel(super.getReportServiceByValAndServiceName("excel", ENTITY_NAME), filter);
    }

    @Override
    public byte[] pdf(String filter) throws Exception {
        BookDTO bookDTO = getBookDTO();
        Map<String, Object> map = param(getBookDTO());
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(getItems(bookDTO));
        return super.exportPDF(super.getReportServiceByValAndServiceName("pdf", ENTITY_NAME), map, beanColDataSource, new ByteArrayOutputStream());
    }

    @Override
    public void excelRowMapping(Workbook workbook, String filter) throws Exception{
        Sheet sheet = workbook.getSheetAt(0);
        int rowCount = 0;
        List<TBook> bookList = bookService.findAllBook();
        for (TBook obj : bookList) {
            Row row = sheet.createRow(++rowCount);
            row.createCell(0).setCellValue(Objects.isNull(obj.getBokId()) ? "" : obj.getBokId().toString());
            row.createCell(1).setCellValue(obj.getBokAuthor());
            row.createCell(2).setCellValue(obj.getBokTitle());
            row.createCell(3).setCellValue(obj.getBokQty());
            row.createCell(4).setCellValue(df.format(obj.getBokPublicDate()));
            row.createCell(5).setCellValue(obj.getBokBookStatus());
        }
    }

    private Map<String, Object> param(BookDTO model) {
        Map<String, Object> param = new HashMap<>();
        return param;
    }

    private List<BookDTO> getItems(BookDTO dto) {
        List<BookDTO> list = new ArrayList<>();
        list.add(dto);
        return list;
    }

    private BookDTO getBookDTO() throws Exception {
        BookDTO bookDTO = new BookDTO();
        List<TBook> bookList = bookService.findAllBook();
        List<BookHelperDTO> bookHelperDTOS = new ArrayList<>();
        for (TBook book: bookList) {
            BookHelperDTO helper = new BookHelperDTO();
            helper.setId(Objects.nonNull(book.getBokId())? book.getBokId().toString(): "");
            helper.setTitle(book.getBokTitle());
            helper.setAuthor(book.getBokAuthor());
            helper.setQty(String.valueOf(book.getBokQty()));
            helper.setPublicDate(df.format(book.getBokPublicDate()));
            helper.setBookStatus(book.getBokBookStatus());
            bookHelperDTOS.add(helper);
        }
        bookDTO.setBookReport(bookHelperDTOS);
        return bookDTO;
    }
}
