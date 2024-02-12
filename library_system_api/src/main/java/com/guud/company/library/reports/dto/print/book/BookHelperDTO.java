package com.guud.company.library.reports.dto.print.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookHelperDTO {

    private String id;
    private String title;
    private String author;
    private String publicDate;
    private String qty;
    private String bookStatus;
}
