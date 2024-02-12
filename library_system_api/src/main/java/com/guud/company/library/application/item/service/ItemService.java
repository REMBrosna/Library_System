package com.guud.company.library.application.item.service;

import com.guud.company.library.administrator.domain.AppUser;
import com.guud.company.library.application.apps.dto.Application;
import com.guud.company.library.application.apps.model.TApplication;
import com.guud.company.library.application.apps.service.ApplicationService;
import com.guud.company.library.application.borrow.model.TBorrowForm;
import com.guud.company.library.application.item.dto.Items;
import com.guud.company.library.application.item.model.TItems;
import com.guud.company.library.application.returns.dto.ReturnForm;
import com.guud.company.library.application.returns.model.TReturnForm;
import com.guud.company.library.configuration.principal.AccountPrincipalService;
import com.guud.company.library.core.AbstractEntityService;
import com.guud.company.library.core.GenericDao;
import com.guud.company.library.core.dto.EntityWhere;
import com.guud.company.library.core.payload.EntityFilterRequest;
import com.guud.company.library.enums.ApplicationStatusEnum;
import com.guud.company.library.enums.ApplicationTypeEnum;
import com.guud.company.library.infrastructure.api.ApiResponse;
import com.guud.company.library.master.appStatus.dto.MstApplicationStatus;
import com.guud.company.library.master.appStatus.model.TMstApplicationStatus;
import com.guud.company.library.master.appType.dto.MstApplicationType;
import com.guud.company.library.master.appType.model.TMstApplicationType;
import com.guud.company.library.master.book.dto.Book;
import com.guud.company.library.master.book.model.TBook;
import com.guud.company.library.master.book.service.BookService;
import com.guud.company.library.utils.PediUtility;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service("itemService")
public class ItemService extends AbstractEntityService<TItems, String, Items> {

    private static final String auditTag = "T MST PROVINCE";
    private static final String tableName = "t_items";
    protected static Logger log = Logger.getLogger(ItemService.class);

    @Autowired
    private BookService bookService;

    @Autowired
    @Qualifier("bookDao")
    protected GenericDao<TBook, String> bookDao;

    @Autowired
    @Qualifier("ItemDao")
    protected GenericDao<TItems, String> ItemDao;

    @Autowired
    @Qualifier("borrowFormDao")
    protected GenericDao<TBorrowForm, String> borrowFormDao;

    @Autowired
    @Qualifier("returnFormDao")
    protected GenericDao<TReturnForm, String> returnFormDao;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private AccountPrincipalService principalService;


    public ItemService() {
        super("ItemDao", auditTag, TItems.class.getName(), tableName);
    }

    @Override
    protected TItems initEnity(TItems entity) throws Exception {
        return entity;
    }

    public void updateBook(String bokId, int bokQty, String bokBookStatus ) throws Exception {
        String sql = "UPDATE TBook o SET o.bokQty =:bokQty , o.bokBookStatus =:bokBookStatus WHERE o.bokId=:bokId";
        Map<String, Object> param = new HashMap<>();
        param.put("bokId", bokId);
        param.put("bokQty", bokQty);
        param.put("bokBookStatus", bokBookStatus);
        bookDao.executeUpdate(sql, param);
    }
    public void updateBorrow(String applicationId, int borTotalQty, int borTotalOwe) throws Exception {
        String sql = "UPDATE TBorrowForm o SET o.borTotalQty =:borTotalQty, o.borTotalOwe =:borTotalOwe WHERE o.TApplication.appID =:appId";
        Map<String, Object> param = new HashMap<>();
        param.put("appId", applicationId);
        param.put("borTotalQty", borTotalQty);
        param.put("borTotalOwe", borTotalOwe);
        borrowFormDao.executeUpdate(sql, param);
    }

    public void updateTotalOwe(String applicationId, int borTotalOwe) throws Exception {
        String sql = "UPDATE TBorrowForm o SET o.borTotalOwe =:borTotalOwe WHERE o.TApplication.appID =:appId";
        Map<String, Object> param = new HashMap<>();
        param.put("appId", applicationId);
        param.put("borTotalOwe", borTotalOwe);
        borrowFormDao.executeUpdate(sql, param);
    }

    public void updateReturn(String applicationId, int returnQty) throws Exception {
        String sql = "UPDATE TReturnForm o SET o.returnQty =:returnQty WHERE o.TApplication.appID =:appId";
        Map<String, Object> param = new HashMap<>();
        param.put("appId", applicationId);
        param.put("returnQty", returnQty);
        returnFormDao.executeUpdate(sql, param);
    }
    public void updateItemReturn(String itmID, int itmOweQty) throws Exception {
        String sql = "UPDATE TItems o SET o.itmOweQty =:itmOweQty WHERE  o.itmID=:itmID";
        Map<String, Object> param = new HashMap<>();
        param.put("itmID", itmID);
        param.put("itmOweQty", itmOweQty);
        returnFormDao.executeUpdate(sql, param);
    }
    public int sumItem(String applicationId) throws Exception{
        String sql = "SELECT o FROM TItems o WHERE o.application.appID =:appId";
        Map<String, Object> param = new HashMap<>();
        param.put("appId", applicationId);
        int totalQty = 0;
        List<TItems> itemsList = ItemDao.getByQuery(sql, param);
        for (TItems obj : itemsList){
            totalQty = totalQty + obj.getItmOweQty();
        }
        return totalQty;
    }
    public int sumTotalReturn(String applicationId) throws Exception{
        String sql = "SELECT o FROM TItems o WHERE o.application.appID =:appId";
        Map<String, Object> param = new HashMap<>();
        param.put("appId", applicationId);
        int totalQtyReturn = 0;
        List<TItems> tReturnFormList = ItemDao.getByQuery(sql, param);
        for (TItems obj : tReturnFormList){
            totalQtyReturn = totalQtyReturn + obj.getItmQty();
        }
        return totalQtyReturn;
    }
    public List<TItems> listItems(String applicationId) throws Exception {
        String sql = "SELECT o FROM TItems o WHERE o.application.appID =:appId";
        Map<String, Object> param = new HashMap<>();
        param.put("appId", applicationId);
        return ItemDao.getByQuery(sql,param) ;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Items add(Items dto, AppUser principal) throws Exception {
            ApiResponse<Object> apiResponse = new ApiResponse<>();
            // Find items borrow and get application status
            if(dto.getApplication().getMstApplicationType().getAptCode().equalsIgnoreCase("RET")){
                //Get Original Item
                Items item =  findById(dto.getItmReference());
                //Set book for items
                dto.setBook(item.getBook());
                if(dto.getItmQty() > item.getItmQty()){
                    throw new Exception("Oop! book Qty is not correct!");
                }

                Items dtoItem = super.add(dto, principal);
                //total borrow - total return
                int recalculateBorrow = item.getItmOweQty() - dto.getItmQty();
                //Total in table Items will deduct base on return amount
                updateItemReturn(item.getItmID(), recalculateBorrow);
                //Result sum items from borrow table
                int resultBorrowQty = sumItem(item.getApplication().getAppID());
                //Result sum items from return table
                int resultReturnQty = sumTotalReturn(dto.getApplication().getAppID());
                //recalculate amount in table borrow and return
                int resultRecalculate = resultBorrowQty -  dto.getItmQty();
                //Set Quatity to Borrow Table
                updateTotalOwe(item.getApplication().getAppID(), resultRecalculate);
                //Set Total return
                updateReturn(dto.getApplication().getAppID(), resultReturnQty);
                return dtoItem;
        } else  {
                //Deduct book
                Book originalBook = bookService.findById(dto.getBook().getBokId());
                int originalQty = originalBook.getBokQty();
                int borrowQty = dto.getItmQty();
                dto.setItmOweQty(borrowQty);
                if(borrowQty > 5){
                    throw new ArithmeticException("Customer allow borrow only 5 books");
                }else if(borrowQty <= 0){
                    throw new ArithmeticException("Please add book Quantity");
                }else if (borrowQty > originalQty){
                    throw new ArithmeticException("Please check out stock before borrow");
                }
                int remainingQty = originalQty - borrowQty;
                String bokBookStatus = "Available";
                if (originalQty == 0){
                    bokBookStatus = "NotAvailable";
                }
                //Update total to book Quantity
                updateBook(originalBook.getBokId(), remainingQty, bokBookStatus);
                Items dtoItem = super.add(dto, principal);
                //Total sum items
                int total =  sumItem(dto.getApplication().getAppID());
                //Update total to Borrow Quantity
                updateBorrow(dto.getApplication().getAppID(),total, total);
                return dtoItem;
            }
    }
    @Override
    protected TItems entityFromDTO(Items dto) throws Exception {
        log.debug("ItemService.entityFromDTO");
        try {
            if (null == dto) {
                throw new Exception("dto is null");
            }

            TItems entity = new TItems();
            entity = dto.toEntity(entity);

            Optional<Book> bookList = Optional.of(dto.getBook());
            if(bookList.isPresent()){
                TBook tBook = new TBook();
                BeanUtils.copyProperties(bookList.get(), tBook);
                entity.setBook(tBook);
            }
            Optional<Application> application = Optional.of(dto.getApplication());
            if (application.isPresent()){
                TApplication tApplication = new TApplication();
                BeanUtils.copyProperties(application.get(), tApplication);
                entity.setApplication(tApplication);
            }

            return entity;
        } catch (Exception ex) {
            log.error("ItemService.entityFromDTO", ex);
            throw ex;
        }
    }

    @Override
    protected Items dtoFromEntity(TItems entity) throws Exception {
        log.debug("ItemService.dtoFromEntity");
        try {
            if (null == entity) {
                throw new Exception("param entity null");
            }
            Items dto = new Items();

            Application application = new Application();
            Book book  = new Book();

            BeanUtils.copyProperties(entity.getApplication(), application);
            BeanUtils.copyProperties(entity.getBook(), book);

            dto.setApplication(application);
            dto.setBook(book);

            BeanUtils.copyProperties(entity,dto);
            return dto;
        } catch (Exception ex) {
            log.error("ItemService.dtoFromEntity", ex);
            throw ex;
        }
    }

    @Override
    protected String entityKeyFromDTO(Items dto) throws Exception {
        log.debug("ItemService.entityKeyFromDTO");

        try {
            if (null == dto) {
                throw new Exception("dto param null");
            }
            return dto.getApplication().getAppID();
        } catch (Exception ex) {
            log.error("ItemService.entityKeyFromDTO", ex);
            throw ex;
        }
    }

    @Override
    protected TItems updateEntity(ACTION attribute, TItems entity, AppUser principal, Date date) throws Exception {
        log.debug("ItemService.updateEntity");
        try {
            if (null == entity)
                throw new Exception("param entity is null");
            if (null == principal)
                throw new Exception("param principal is null");
            if (null == date)
                throw new Exception("param date is null");

            Optional<String> opUserId = Optional.ofNullable(principal.getUsername());
            switch (attribute) {
                case CREATE:
                    entity.setItmID(PediUtility.generateId(ApplicationTypeEnum.ITM.name()));
                    entity.setItmUidCreate(opUserId.orElse("SYS"));
                    entity.setItmDtCreate(date);
                    entity.setItmUidLupd(opUserId.orElse("SYS"));
                    entity.setItmDtLupd(date);
                    entity.setItmRecStatus('A');
                    break;

                case MODIFY:
                    entity.setItmUidLupd(opUserId.orElse("SYS"));
                    entity.setItmDtLupd(date);
                    break;

                default:
                    break;
            }

            return entity;
        } catch (Exception ex) {
            log.error("ItemService.updateEntity", ex);
            throw ex;
        }
    }

    @Override
    protected TItems updateEntityStatus(TItems entity, char status) throws Exception {
        log.debug("ItemService.updateEntityStatus");

        try {
            if (null == entity) {
                throw new Exception("entity param is null");
            }

            entity.setItmRecStatus(status);
            return entity;
        } catch (Exception ex) {
            log.error("ItemService.updateEntityStatus", ex);
            throw ex;
        }

    }


    @Override
    protected Items preSaveUpdateDTO(TItems storedEntity, Items dto) throws Exception {
        log.debug("ItemService.preSaveUpdateDTO");

        try {
            if (null == storedEntity) {
                throw new Exception("param storedEntity is null");
            }
            if (null == dto) {
                throw new Exception("param dto is null");
            }

            dto.setItmUidCreate(storedEntity.getItmUidCreate());
            dto.setItmDtCreate(storedEntity.getItmDtCreate());

            return dto;
        } catch (Exception ex) {
            log.error("ItemService.preSaveUpdateDTO", ex);
            throw ex;
        }

    }

    @Override
    protected void preSaveValidation(Items var1, AppUser var2) throws Exception {
        // No implementation
    }

    @Override
    protected String getWhereClause(Items dto, boolean wherePrinted) throws Exception {
        log.debug("ItemService.getWhereClause");

        try {
            if (null == dto) {
                throw new Exception("param dto null");
            }

            StringBuffer searchStatement = new StringBuffer();
            if (!StringUtils.isEmpty(dto.getItmID())) {
                searchStatement.append(getOperator(wherePrinted) + "o.application.appID LIKE :itmID");
                wherePrinted = true;
            }
            return searchStatement.toString();
        } catch (Exception ex) {
            log.error("ItemService.getWhereClause", ex);
            throw ex;
        }
    }

    @Override
    protected HashMap<String, Object> getParameters(Items dto) throws Exception {
        log.debug("ItemService.getParameters");

        try {
            if (null == dto) {
                throw new Exception("param dto null");
            }
            HashMap<String, Object> parameters = new HashMap<String, Object>();
            if (!StringUtils.isEmpty(dto.getItmID())) {
                parameters.put("itmID", "%" + dto.getItmID() + "%");
            }

            return parameters;
        } catch (Exception ex) {
            log.error("ItemService.getParameters", ex);
            throw ex;
        }
    }

    @Override
    protected Items whereDto(EntityFilterRequest filterRequest) throws Exception {
        try {
            if (null == filterRequest)
                throw new Exception("param filterRequest null");

            Items dto = new Items();
            for (EntityWhere entityWhere : filterRequest.getWhereList()) {
                Optional<String> opValue = Optional.ofNullable(entityWhere.getValue());
                if (!opValue.isPresent()) {
                    continue;
                }

                if (entityWhere.getAttribute().equalsIgnoreCase("itmID")) {
                    dto.setItmID(opValue.get());
                }
            }

            log.info("dto: " + dto.toString());
            return dto;
        } catch (Exception ex) {
            log.error("whereDto", ex);
            throw ex;
        }
    }

    @Override
    public Items findById(String id) throws Exception {
        log.debug("ItemService.findById");

        try {
            if (StringUtils.isEmpty(id)) {
                throw new Exception("param id is null or empty");
            }

            TItems entity = dao.find(id);
            if (null == entity) {
                throw new Exception("id: " + id);
            }
            this.initEnity(entity);

            return this.dtoFromEntity(entity);
        } catch (Exception ex) {
            log.error("ItemService.findById", ex);
            throw ex;
        }
    }

    @Override
    public Items deleteById(String id, AppUser principal) throws Exception {
        log.debug("ItemService.deleteById");

        Date now = Calendar.getInstance().getTime();
        try {
            if (StringUtils.isEmpty(id))
                throw new Exception("param id null or empty");
            if (null == principal)
                throw new Exception("param prinicipal null");

            TItems entity = dao.find(id);
            if (null == entity)
                throw new Exception("id: " + id);
            this.initEnity(entity);

            this.updateEntityStatus(entity, 'I');
            this.updateEntity(ACTION.MODIFY, entity, principal, now);

            Items dto = dtoFromEntity(entity);
            this.delete(dto, principal);
            return dto;
        } catch (Exception ex) {
            log.error("ItemService.deleteById", ex);
            throw ex;
        }
    }

    @Override
    public List<Items> filterBy(EntityFilterRequest filterRequest) throws Exception {
        try {
            if (null == filterRequest)
                throw new Exception("param filterRequest null");

            Items dto = this.whereDto(filterRequest);
            if (null == dto)
                throw new Exception("whereDto null");

            filterRequest.setTotalRecords(super.countByAnd(dto));

            String selectClause = "from TItems o ";
            String orderByClause = filterRequest.getOrderBy().toString();
            List<TItems> entities = super.findEntitiesByAnd(dto, selectClause, orderByClause,
                    filterRequest.getDisplayLength(), filterRequest.getDisplayStart());

            return entities.stream().map(x -> {
                try {
                    return dtoFromEntity(x);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("filterBy", ex);
            throw ex;
        }
    }

    @Override
    public Items findML(Items dto) throws Exception {
        return null;
    }
}
