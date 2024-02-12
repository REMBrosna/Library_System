package com.guud.company.library.master.book.dao.impl;

import com.guud.company.library.core.GenericDaoImpl;
import com.guud.company.library.master.book.dao.BookDao;
import com.guud.company.library.master.book.dto.Book;
import com.guud.company.library.master.book.model.TBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bookDao")
public class BookDaoImpl extends GenericDaoImpl<TBook, String> implements BookDao {

}
