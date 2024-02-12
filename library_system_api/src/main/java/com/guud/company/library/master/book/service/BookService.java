package com.guud.company.library.master.book.service;

import com.guud.company.library.administrator.domain.AppUser;
import com.guud.company.library.core.AbstractEntityService;
import com.guud.company.library.core.GenericDao;
import com.guud.company.library.core.dto.EntityOrderBy;
import com.guud.company.library.core.dto.EntityWhere;
import com.guud.company.library.core.payload.EntityFilterRequest;
import com.guud.company.library.core.payload.EntityFilterResponse;
import com.guud.company.library.master.book.dto.Book;
import com.guud.company.library.master.book.model.TBook;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("bookService")
public class BookService extends AbstractEntityService<TBook, String, Book> {

    private static final String auditTag = "T MST PROVINCE";
    private static final String tableName = "t_book";
    protected static Logger log = Logger.getLogger(BookService.class);

    public BookService() {
        super("bookDao", auditTag, TBook.class.getName(), tableName);
    }

    @Autowired
    @Qualifier("bookDao")
    protected GenericDao<TBook, String> bookDao;

    public List<TBook> findAllBook() throws Exception {
        String sql = "SELECT o FROM TBook o WHERE o.bokRecStatus = 'A' ";
        return bookDao.getByQuery(sql);
    }

    @Override
    public Book add(Book dto, AppUser principal) throws Exception {
        try {
            if (this.isRecordExists(dto)) {
                throw new ArithmeticException("Book already exist !");
            }
            if (dto.getBokQty() > 20 || dto.getBokQty() < 1) {
                throw new ArithmeticException("Check Maximum of books can create only 20 books !");
            }
            return super.add(dto, principal);
        }catch (ExceptionInInitializerError ex) {
            log.error("BookService.entityFromDTO", ex);
            throw ex;
        }
    }

    @Override
    public boolean isRecordExists(Book dto) throws Exception {
        String sql = "SELECT o FROM TBook o WHERE o.bokAuthor =:bokAuthor AND o.bokPublicDate =:bokPublicDate AND o.bokTitle =:bokTitle AND o.bokRecStatus = 'A' ";
        Map<String, Object> param = new HashMap<>();
        param.put("bokAuthor", dto.getBokAuthor());
        param.put("bokPublicDate", dto.getBokPublicDate());
        param.put("bokTitle", dto.getBokTitle());
        List<TBook> list = bookDao.getByQuery(sql, param);
        return !list.isEmpty();
    }

    @Override
    public List<Book> findAll() throws Exception {
        List<Book> list = new ArrayList<>();
        this.findAllBook().forEach(book -> {
           Book obj = new Book();
            BeanUtils.copyProperties(book, obj);
            list.add(obj);
        });
        return list;
    }

    @Override
    protected TBook initEnity(TBook entity) throws Exception {
        return entity;
    }

    @Override
    protected TBook entityFromDTO(Book dto) throws Exception {
        log.debug("BookService.entityFromDTO");
        try {
            if (null == dto) {
                throw new Exception("dto is null");
            }
            TBook entity = new TBook();
            entity = dto.toEntity(entity);
            return entity;
        } catch (Exception ex) {
            log.error("BookService.entityFromDTO", ex);
            throw ex;
        }
    }

    @Override
    protected Book dtoFromEntity(TBook entity) throws Exception {
        log.debug("BookService.dtoFromEntity");
        try {
            if (null == entity) {
                throw new Exception("param entity null");
            }
            Book dto = new Book(entity);
            return dto;
        } catch (Exception ex) {
            log.error("BookService.dtoFromEntity", ex);
            throw ex;
        }
    }

    @Override
    protected String entityKeyFromDTO(Book dto) throws Exception {
        log.debug("BookService.entityKeyFromDTO");

        try {
            if (null == dto) {
                throw new Exception("dto param null");
            }
            return dto.getBokId();
        } catch (Exception ex) {
            log.error("BookService.entityKeyFromDTO", ex);
            throw ex;
        }
    }

    @Override
    protected TBook updateEntity(ACTION attribute, TBook entity, AppUser principal, Date date) throws Exception {
        log.debug("BookService.updateEntity");

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
                    entity.setBokBookStatus("Available");
                    entity.setBokRecStatus('A');
                    entity.setBokUidCreate(opUserId.orElse("SYS"));
                    entity.setBokDtCreate(date);
                    entity.setBokUidLupd(opUserId.orElse("SYS"));
                    entity.setBokDtLupd(date);
                    break;
                case MODIFY:
                    entity.setBokUidLupd(opUserId.orElse("SYS"));
                    entity.setBokDtLupd(date);
                    break;
                default:
                    break;
            }
            return entity;
        } catch (Exception ex) {
            log.error("BookService.updateEntity", ex);
            throw ex;
        }
    }

    @Override
    protected TBook updateEntityStatus(TBook entity, char status) throws Exception {
        log.debug("BookService.updateEntityStatus");

        try {
            if (null == entity) {
                throw new Exception("entity param is null");
            }

            entity.setBokRecStatus(status);
            return entity;
        } catch (Exception ex) {
            log.error("BookService.updateEntityStatus", ex);
            throw ex;
        }
    }

    @Override
    protected Book preSaveUpdateDTO(TBook storedEntity, Book dto) throws Exception {
        log.debug("BookService.preSaveUpdateDTO");

        try {
            if (null == storedEntity) {
                throw new Exception("param storedEntity is null");
            }
            if (null == dto) {
                throw new Exception("param dto is null");
            }

            dto.setBokUidCreate(storedEntity.getBokUidCreate());
            dto.setBokDtCreate(storedEntity.getBokDtCreate());

            return dto;
        } catch (Exception ex) {
            log.error("BookService.preSaveUpdateDTO", ex);
            throw ex;
        }
    }

    @Override
    protected void preSaveValidation(Book var1, AppUser var2) throws Exception {
        // No implementation
    }

    @Override
    protected String getWhereClause(Book dto, boolean wherePrinted) throws Exception {
        log.debug("BookService.getWhereClause");

        try {
            if (null == dto) {
                throw new Exception("param dto null");
            }

            StringBuffer searchStatement = new StringBuffer();
            if (!StringUtils.isEmpty(dto.getBokId())) {
                searchStatement.append(getOperator(wherePrinted) + "o.bokId LIKE :bokId");
                wherePrinted = true;
            }
            return searchStatement.toString();
        } catch (Exception ex) {
            log.error("BookService.getWhereClause", ex);
            throw ex;
        }
    }

    @Override
    protected HashMap<String, Object> getParameters(Book dto) throws Exception {
        log.debug("BookService.getParameters");

        try {
            if (null == dto) {
                throw new Exception("param dto null");
            }

            HashMap<String, Object> parameters = new HashMap<String, Object>();
            if (StringUtils.isNotBlank(dto.getBokId())) {
                parameters.put("bokId", "%" + dto.getBokId() + "%");
            }

            return parameters;
        } catch (Exception ex) {
            log.error("BookService.getParameters", ex);
            throw ex;
        }
    }

    @Override
    protected Book whereDto(EntityFilterRequest filterRequest) throws Exception {
        try {
            if (null == filterRequest)
                throw new Exception("param filterRequest null");

            Book dto = new Book();
            for (EntityWhere entityWhere : filterRequest.getWhereList()) {
                Optional<String> opValue = Optional.ofNullable(entityWhere.getValue());
                if (!opValue.isPresent())
                    continue;

                if (entityWhere.getAttribute().equalsIgnoreCase("bokId")) {
                    dto.setBokId(opValue.get());
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
    public Book findById(String id) throws Exception {
        log.debug("BookService.findById");

        try {
            if (StringUtils.isEmpty(id)) {
                throw new Exception("param id is null or empty");
            }

            TBook entity = dao.find(id);
            if (null == entity) {
                throw new Exception("id: " + id);
            }
            this.initEnity(entity);

            return this.dtoFromEntity(entity);
        } catch (Exception ex) {
            log.error("BookService.findById", ex);
            throw ex;
        }
    }

    @Override
    public Book deleteById(String id, AppUser principal) throws Exception {
        log.debug("BookService.deleteById");

        Date now = Calendar.getInstance().getTime();
        try {
            if (StringUtils.isEmpty(id))
                throw new Exception("param id null or empty");
            if (null == principal)
                throw new Exception("param prinicipal null");
            TBook entity = dao.find(id);
            if (null == entity)
                throw new Exception("id: " + id);
            this.initEnity(entity);
            this.updateEntityStatus(entity, 'I');
            this.updateEntity(ACTION.MODIFY, entity, principal, now);

            Book dto = dtoFromEntity(entity);
            this.delete(dto, principal);
            return dto;
        } catch (Exception ex) {
            log.error("BookService.deleteById", ex);
            throw ex;
        }
    }

    @Override
    public List<Book> filterBy(EntityFilterRequest filterRequest) throws Exception {
        try {
            if (null == filterRequest)
                throw new Exception("param filterRequest null");

            Book dto = this.whereDto(filterRequest);
            if (null == dto)
                throw new Exception("whereDto null");

            filterRequest.setTotalRecords(super.countByAnd(dto));

            String selectClause = "from TBook o ";
            String orderByClause = filterRequest.getOrderBy().toString();
            List<TBook> entities = super.findEntitiesByAnd(dto, selectClause, orderByClause,
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

    public Optional<Object> getBookList(Map<String, String> params) throws Exception {
        log.debug("getBookList");
        try {
            if (io.jsonwebtoken.lang.Collections.isEmpty(params)) {
                throw new Exception("param params null or empty");
            }

            EntityFilterRequest filterRequest = new EntityFilterRequest();
            // start and length parameter extraction
            filterRequest.setDisplayStart(
                    params.containsKey("iDisplayStart") ? Integer.parseInt(params.get("iDisplayStart")) : -1);
            filterRequest.setDisplayLength(
                    params.containsKey("iDisplayLength") ? Integer.parseInt(params.get("iDisplayLength"))
                            : -1);
            // where parameters extraction
            ArrayList<EntityWhere> whereList = new ArrayList<>();
            List<String> searches = params.keySet().stream().filter(x -> x.contains("sSearch_"))
                    .collect(Collectors.toList());
            for (int nIndex = 1; nIndex <= searches.size(); nIndex++) {
                String searchParam = params.get("sSearch_" + nIndex);
                String valueParam = params.get("mDataProp_" + nIndex);
                log.info("searchParam: " + searchParam + " valueParam: " + valueParam);
                whereList.add(new EntityWhere(valueParam, searchParam));
            }
            filterRequest.setWhereList(whereList);
            // order by parameters extraction
            Optional<String> opSortAttribute = Optional.ofNullable(params.get("mDataProp_0"));
            Optional<String> opSortOrder = Optional.ofNullable(params.get("sSortDir_0"));
            if (opSortAttribute.isPresent() && opSortOrder.isPresent()) {
                EntityOrderBy orderBy = new EntityOrderBy();
                orderBy.setAttribute(opSortAttribute.get());
                orderBy.setOrdered(opSortOrder.get().equalsIgnoreCase("desc") ? EntityOrderBy.ORDERED.DESC : EntityOrderBy.ORDERED.ASC);
                filterRequest.setOrderBy(orderBy);
            }
            if (!filterRequest.isValid()) {
                throw new Exception("Invalid request: " + filterRequest.toString());
            }

            List<Book> en = this.filterBy(filterRequest);

            @SuppressWarnings("unchecked")
            List<Object> entities = List.class.cast(en);
            EntityFilterResponse filterResponse = new EntityFilterResponse();
            filterResponse.setiTotalRecords(entities.size());
            filterResponse.setiTotalDisplayRecords(filterRequest.getTotalRecords());
            filterResponse.setAaData((ArrayList<Object>) entities);

            return Optional.of(filterResponse);
        } catch (Exception ex) {
            log.error("getPaymentAdvices", ex);
            throw ex;
        }
    }

    @Override
    public Book findML(Book dto) throws Exception {
        return null;
    }
}
