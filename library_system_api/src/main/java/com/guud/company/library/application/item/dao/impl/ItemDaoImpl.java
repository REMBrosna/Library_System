package com.guud.company.library.application.item.dao.impl;

import com.guud.company.library.application.item.dao.ItemDao;
import com.guud.company.library.application.item.model.TItems;
import com.guud.company.library.core.GenericDaoImpl;
import org.springframework.stereotype.Service;

@Service("ItemDao")
public class ItemDaoImpl extends GenericDaoImpl<TItems, String> implements ItemDao {
}
