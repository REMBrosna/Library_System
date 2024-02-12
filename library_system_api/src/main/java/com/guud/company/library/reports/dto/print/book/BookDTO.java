package com.guud.company.library.reports.dto.print.book;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class BookDTO {

    private List<BookHelperDTO> bookReport;
}
