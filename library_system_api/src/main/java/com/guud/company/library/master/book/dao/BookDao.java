package com.guud.company.library.master.book.dao;

import com.guud.company.library.core.GenericDao;
import com.guud.company.library.master.book.dto.Book;
import com.guud.company.library.master.book.model.TBook;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BookDao  extends GenericDao<TBook, String> {

}
