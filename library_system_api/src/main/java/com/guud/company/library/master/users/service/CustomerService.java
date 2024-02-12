package com.guud.company.library.master.users.service;

import com.guud.company.library.administrator.domain.AppUser;
import com.guud.company.library.core.AbstractEntityService;
import com.guud.company.library.core.dto.EntityOrderBy;
import com.guud.company.library.core.dto.EntityWhere;
import com.guud.company.library.core.payload.EntityFilterRequest;
import com.guud.company.library.core.payload.EntityFilterResponse;
import com.guud.company.library.enums.ApplicationTypeEnum;
import com.guud.company.library.master.users.dto.Customer;
import com.guud.company.library.master.users.model.TCustomer;
import com.guud.company.library.utils.PediUtility;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerService extends AbstractEntityService<TCustomer, String, Customer> {

    private static final String auditTag = "T MST PROVINCE";
    private static final String tableName = "t_customer";
    protected static Logger log = Logger.getLogger(CustomerService.class);

    public CustomerService() {
        super("customerDao", auditTag, TCustomer.class.getName(), tableName);
    }

    @Override
    protected TCustomer initEnity(TCustomer entity) throws Exception {
        return entity;
    }

    @Override
    protected TCustomer entityFromDTO(Customer dto) throws Exception {
        log.debug("CustomerService.entityFromDTO");
        try {
            if (null == dto) {
                throw new Exception("dto is null");
            }

            TCustomer entity = new TCustomer();
            entity = dto.toEntity(entity);

            return entity;
        } catch (Exception ex) {
            log.error("CustomerService.entityFromDTO", ex);
            throw ex;
        }
    }

    @Override
    protected Customer dtoFromEntity(TCustomer entity) throws Exception {
        log.debug("CustomerService.dtoFromEntity");
        try {
            if (null == entity) {
                throw new Exception("param entity null");
            }
            Customer dto = new Customer(entity);

            return dto;
        } catch (Exception ex) {
            log.error("CustomerService.dtoFromEntity", ex);
            throw ex;
        }

    }

    @Override
    protected String entityKeyFromDTO(Customer dto) throws Exception {
        log.debug("CustomerService.entityKeyFromDTO");

        try {
            if (null == dto) {
                throw new Exception("dto param null");
            }

            return dto.getCusId();
        } catch (Exception ex) {
            log.error("CustomerService.entityKeyFromDTO", ex);
            throw ex;
        }
    }

    @Override
    protected TCustomer updateEntity(ACTION attribute, TCustomer entity, AppUser principal, Date date) throws Exception {
        log.debug("CustomerService.updateEntity");

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
                    entity.setCusId(PediUtility.generateId(ApplicationTypeEnum.CUS.name()));
                    entity.setUidCreate(opUserId.orElse("SYS"));
                    entity.setDtCreate(date);
                    entity.setUidLupd(opUserId.orElse("SYS"));
                    entity.setDtLupd(date);
                    entity.setRecStatus('A');
                    break;

                case MODIFY:
                    entity.setUidLupd(opUserId.orElse("SYS"));
                    entity.setDtLupd(date);
                    break;

                default:
                    break;
            }

            return entity;
        } catch (Exception ex) {
            log.error("CustomerService.updateEntity", ex);
            throw ex;
        }
    }

    @Override
    protected TCustomer updateEntityStatus(TCustomer entity, char status) throws Exception {
        log.debug("CustomerService.updateEntityStatus");

        try {
            if (null == entity) {
                throw new Exception("entity param is null");
            }

            entity.setRecStatus(status);
            return entity;
        } catch (Exception ex) {
            log.error("CustomerService.updateEntityStatus", ex);
            throw ex;
        }

    }

    @Override
    protected Customer preSaveUpdateDTO(TCustomer storedEntity, Customer dto) throws Exception {
        log.debug("CustomerService.preSaveUpdateDTO");

        try {
            if (null == storedEntity) {
                throw new Exception("param storedEntity is null");
            }
            if (null == dto) {
                throw new Exception("param dto is null");
            }

            dto.setUidCreate(storedEntity.getUidCreate());
            dto.setDtCreate(storedEntity.getDtCreate());

            return dto;
        } catch (Exception ex) {
            log.error("CustomerService.preSaveUpdateDTO", ex);
            throw ex;
        }

    }

    @Override
    protected void preSaveValidation(Customer var1, AppUser var2) throws Exception {
        // No implementation
    }

    @Override
    protected String getWhereClause(Customer dto, boolean wherePrinted) throws Exception {
        log.debug("CustomerService.getWhereClause");

        try {
            if (null == dto) {
                throw new Exception("param dto null");
            }

            StringBuffer searchStatement = new StringBuffer();
            if (!StringUtils.isEmpty(dto.getCusId())) {
                searchStatement.append(getOperator(wherePrinted) + "o.cusId LIKE :cusId");
                wherePrinted = true;
            }
            if (Character.isAlphabetic(dto.getRecStatus())) {
                searchStatement.append(getOperator(wherePrinted) + "o.recStatus = :recStatus");
                wherePrinted = true;
            }
            return searchStatement.toString();
        } catch (Exception ex) {
            log.error("CustomerService.getWhereClause", ex);
            throw ex;
        }
    }

    @Override
    protected HashMap<String, Object> getParameters(Customer dto) throws Exception {
        log.debug("CustomerService.getParameters");

        try {
            if (null == dto) {
                throw new Exception("param dto null");
            }

            HashMap<String, Object> parameters = new HashMap<String, Object>();
            if (!StringUtils.isEmpty(dto.getCusId())) {
                parameters.put("cusId", "%" + dto.getCusId() + "%");
            }
            if (Character.isAlphabetic(dto.getRecStatus())) {
                parameters.put("recStatus", dto.getRecStatus());
            }

            return parameters;
        } catch (Exception ex) {
            log.error("CustomerService.getParameters", ex);
            throw ex;
        }
    }

    @Override
    protected Customer whereDto(EntityFilterRequest filterRequest) throws Exception {
        try {
            if (null == filterRequest)
                throw new Exception("param filterRequest null");

            Customer dto = new Customer();
            for (EntityWhere entityWhere : filterRequest.getWhereList()) {
                Optional<String> opValue = Optional.ofNullable(entityWhere.getValue());
                if (!opValue.isPresent())
                    continue;

                if (entityWhere.getAttribute().equalsIgnoreCase("cusId")) {
                    dto.setCusId(opValue.get());
                }
                if (entityWhere.getAttribute().equalsIgnoreCase("recStatus")) {
                    dto.setRecStatus(opValue.get().charAt(0));
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
    public Customer findById(String id) throws Exception {
        log.debug("CustomerService.findById");

        try {
            if (StringUtils.isEmpty(id)) {
                throw new Exception("param id is null or empty");
            }

            TCustomer entity = dao.find(id);
            if (null == entity) {
                throw new Exception("id: " + id);
            }
            this.initEnity(entity);

            return this.dtoFromEntity(entity);
        } catch (Exception ex) {
            log.error("CustomerService.findById", ex);
            throw ex;
        }
    }

    @Override
    public Customer deleteById(String id, AppUser principal) throws Exception {
        log.debug("CustomerService.deleteById");

        Date now = Calendar.getInstance().getTime();
        try {
            if (StringUtils.isEmpty(id))
                throw new Exception("param id null or empty");
            if (null == principal)
                throw new Exception("param prinicipal null");

            TCustomer entity = dao.find(id);
            if (null == entity)
                throw new Exception("id: " + id);
            this.initEnity(entity);

            this.updateEntityStatus(entity, 'I');
            this.updateEntity(ACTION.MODIFY, entity, principal, now);

            Customer dto = dtoFromEntity(entity);
            this.delete(dto, principal);
            return dto;
        } catch (Exception ex) {
            log.error("CustomerService.deleteById", ex);
            throw ex;
        }
    }

    @Override
    public List<Customer> filterBy(EntityFilterRequest filterRequest) throws Exception {
        try {
            if (null == filterRequest)
                throw new Exception("param filterRequest null");

            Customer dto = this.whereDto(filterRequest);
            if (null == dto)
                throw new Exception("whereDto null");

            filterRequest.setTotalRecords(super.countByAnd(dto));

            String selectClause = "from TCustomer o ";
            String orderByClause = filterRequest.getOrderBy().toString();
            List<TCustomer> entities = super.findEntitiesByAnd(dto, selectClause, orderByClause,
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
    public Customer findML(Customer dto) throws Exception {
        return null;
    }
}
