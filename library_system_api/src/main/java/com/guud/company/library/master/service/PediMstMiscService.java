package com.guud.company.library.master.service;

import com.guud.company.library.administrator.domain.AppUser;
import com.guud.company.library.administrator.repository.AppUserRepository;
import com.guud.company.library.application.item.model.TItems;
import com.guud.company.library.application.item.service.ItemService;
import com.guud.company.library.core.GenericDao;
import com.guud.company.library.infrastructure.api.ApiResponse;
import com.guud.company.library.master.appType.model.TMstApplicationType;
import com.guud.company.library.master.book.model.TBook;
import com.guud.company.library.master.province.model.TMstProvince;
import com.guud.company.library.master.users.model.TCustomer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;


@Service("pediMstMiscService")
public class PediMstMiscService {
    private static final String ALL = "ALL";


    // Static Attributes
    ////////////////////
    private static Logger log = Logger.getLogger(PediMstMiscService.class);
    @Autowired
    @Qualifier("mstProvinceDao")
    private GenericDao<TMstProvince, String> tMstProvinceStringGenericDao;

    @Autowired
    @Qualifier("applicationTypeDao")
    private GenericDao<TMstApplicationType, String> mstApplicationTypeStringGenericDao;

    @Autowired
    @Qualifier("bookDao")
    private GenericDao<TBook, String> bookDao;

    @Autowired
    private ItemService itemService;


    @Autowired
    @Qualifier("customerDao")
    private GenericDao<TCustomer, String> customerDao;


    @Autowired
    private AppUserRepository appUserRepository;

    public ResponseEntity<Object> getNumberProvince() throws Exception {
        log.debug("getNumberProvince");
        try {
            List<TMstProvince> provinceList = tMstProvinceStringGenericDao.getAll();
            if (provinceList == null)
                throw new Exception("getNumberProvince");
            return ResponseEntity.ok(provinceList);

        } catch (Exception ex) {
            log.error("ProvinceService.getWhereClause", ex);
            throw ex;
        }
    }
    public ResponseEntity<Object> getNumberApplication() throws Exception {
        log.debug("getNumberEngine");
        try {
            List<TMstApplicationType> tApplicationList = mstApplicationTypeStringGenericDao.getAll();
            if (tApplicationList == null)
                throw new Exception("getNumberApplication");
            return ResponseEntity.ok(tApplicationList);

        } catch (Exception ex) {
            log.error("ProvinceService.getWhereClause", ex);
            throw ex;
        }
    }


    public ResponseEntity<Object> getNumberCustomers() throws Exception {
        log.debug("getNumberCustomers");
        try {
            List<TCustomer> customers = customerDao.getAll();
            if (customers == null)
                throw new Exception("getNumberCustomers");
            return ResponseEntity.ok(customers);

        } catch (Exception ex) {
            log.error("ProvinceService.getWhereClause", ex);
            throw ex;
        }
    }

    public ResponseEntity<Object> getNumberUsers() throws Exception {
        log.debug("getNumberCustomers");
        try {
            List<AppUser> appUsers = appUserRepository.findAll();
            if (appUsers == null)
                throw new Exception("getNumberAppUsers");
            return ResponseEntity.ok(appUsers);

        } catch (Exception ex) {
            log.error("ProvinceService.getWhereClause", ex);
            throw ex;
        }
    }

    public ResponseEntity<Object> getNumberBooks() throws Exception {
        log.debug("getNumberBooks");
        try {
            List<TBook> bookList = bookDao.getAll();
            if (bookList == null)
                throw new Exception("getNumberBooks");
            return ResponseEntity.ok(bookList);

        } catch (Exception ex) {
            log.error("ProvinceService.getWhereClause", ex);
            throw ex;
        }
    }

    public ResponseEntity<Object> getNumberItems(String applicationId) throws Exception {
        log.debug("getListItems");
        try {
            List<TItems> itemsList = itemService.listItems(applicationId);

            if (itemsList == null)
                throw new Exception("getListItems");
            return ResponseEntity.ok(itemsList);
        } catch (Exception ex) {
            log.error("ProvinceService.getWhereClause", ex);
            throw ex;
        }
    }

}
