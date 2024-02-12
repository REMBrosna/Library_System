package com.guud.company.library.master.province.service;

import com.guud.company.library.administrator.domain.AppUser;
import com.guud.company.library.core.AbstractEntityService;
import com.guud.company.library.core.dto.EntityOrderBy;
import com.guud.company.library.core.dto.EntityWhere;
import com.guud.company.library.core.payload.EntityFilterRequest;
import com.guud.company.library.core.payload.EntityFilterResponse;
import com.guud.company.library.master.province.dto.MstProvince;
import com.guud.company.library.master.province.model.TMstProvince;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProvinceService extends AbstractEntityService<TMstProvince, String, MstProvince> {

    private static final String auditTag = "T MST PROVINCE";
    private static final String tableName = "t_mst_province";
    protected static Logger log = Logger.getLogger(ProvinceService.class);

    public ProvinceService() {
        super("mstProvinceDao", auditTag, TMstProvince.class.getName(), tableName);
    }

    @Override
    protected TMstProvince initEnity(TMstProvince entity) throws Exception {
        return entity;
    }

    @Override
    protected TMstProvince entityFromDTO(MstProvince dto) throws Exception {
        log.debug("ProvinceService.entityFromDTO");
        try {
            if (null == dto) {
                throw new Exception("dto is null");
            }

            TMstProvince entity = new TMstProvince();
            entity = dto.toEntity(entity);

            return entity;
        } catch (Exception ex) {
            log.error("ProvinceService.entityFromDTO", ex);
            throw ex;
        }
    }

    @Override
    protected MstProvince dtoFromEntity(TMstProvince entity) throws Exception {
        log.debug("ProvinceService.dtoFromEntity");
        try {
            if (null == entity) {
                throw new Exception("param entity null");
            }
            MstProvince dto = new MstProvince(entity);

            return dto;
        } catch (Exception ex) {
            log.error("ProvinceService.dtoFromEntity", ex);
            throw ex;
        }

    }

    @Override
    protected String entityKeyFromDTO(MstProvince dto) throws Exception {
        log.debug("ProvinceService.entityKeyFromDTO");

        try {
            if (null == dto) {
                throw new Exception("dto param null");
            }

            return dto.getCode();
        } catch (Exception ex) {
            log.error("ProvinceService.entityKeyFromDTO", ex);
            throw ex;
        }
    }

    @Override
    protected TMstProvince updateEntity(ACTION attribute, TMstProvince entity, AppUser principal, Date date) throws Exception {
        log.debug("ProvinceService.updateEntity");

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
                    entity.setUidCreate(opUserId.orElse("SYS"));
                    entity.setDtCreate(date);
                    entity.setUidLupd(opUserId.orElse("SYS"));
                    entity.setDtLupd(date);
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
            log.error("ProvinceService.updateEntity", ex);
            throw ex;
        }
    }

    @Override
    protected TMstProvince updateEntityStatus(TMstProvince entity, char status) throws Exception {
        log.debug("ProvinceService.updateEntityStatus");

        try {
            if (null == entity) {
                throw new Exception("entity param is null");
            }

            entity.setRecStatus(status);
            return entity;
        } catch (Exception ex) {
            log.error("ProvinceService.updateEntityStatus", ex);
            throw ex;
        }

    }

    @Override
    protected MstProvince preSaveUpdateDTO(TMstProvince storedEntity, MstProvince dto) throws Exception {
        log.debug("ProvinceService.preSaveUpdateDTO");

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
            log.error("ProvinceService.preSaveUpdateDTO", ex);
            throw ex;
        }

    }

    @Override
    protected void preSaveValidation(MstProvince var1, AppUser var2) throws Exception {
        // No implementation
    }

    @Override
    protected String getWhereClause(MstProvince dto, boolean wherePrinted) throws Exception {
        log.debug("ProvinceService.getWhereClause");

        try {
            if (null == dto) {
                throw new Exception("param dto null");
            }

            StringBuffer searchStatement = new StringBuffer();
            if (!StringUtils.isEmpty(dto.getCode())) {
                searchStatement.append(getOperator(wherePrinted) + "o.code LIKE :code");
                wherePrinted = true;
            }
            Optional<String> opActnDesc = Optional.ofNullable(dto.getDesc());
            if (opActnDesc.isPresent()) {
                searchStatement.append(getOperator(wherePrinted) + "o.desc LIKE :desc");
                wherePrinted = true;
            }
            Optional<String> opActnDescOth = Optional.ofNullable(dto.getDescOth());
            if (opActnDescOth.isPresent()) {
                searchStatement.append(getOperator(wherePrinted) + "o.descOth LIKE :descOth");
                wherePrinted = true;
            }
            if (Character.isAlphabetic(dto.getRecStatus())) {
                searchStatement.append(getOperator(wherePrinted) + "o.recStatus = :recStatus");
                wherePrinted = true;
            }
            return searchStatement.toString();
        } catch (Exception ex) {
            log.error("ProvinceService.getWhereClause", ex);
            throw ex;
        }
    }

    @Override
    protected HashMap<String, Object> getParameters(MstProvince dto) throws Exception {
        log.debug("ProvinceService.getParameters");

        try {
            if (null == dto) {
                throw new Exception("param dto null");
            }

            HashMap<String, Object> parameters = new HashMap<String, Object>();
            if (!StringUtils.isEmpty(dto.getCode())) {
                parameters.put("code", "%" + dto.getCode() + "%");
            }
            Optional<String> opActnDesc = Optional.ofNullable(dto.getDesc());
            if (opActnDesc.isPresent()) {
                parameters.put("desc", "%" + opActnDesc.get() + "%");
            }
            Optional<String> opActnDescOth = Optional.ofNullable(dto.getDescOth());
            if (opActnDescOth.isPresent()) {
                parameters.put("descOth", "%" + opActnDescOth.get() + "%");
            }
            if (Character.isAlphabetic(dto.getRecStatus())) {
                parameters.put("recStatus", dto.getRecStatus());
            }

            return parameters;
        } catch (Exception ex) {
            log.error("ProvinceService.getParameters", ex);
            throw ex;
        }
    }

    @Override
    protected MstProvince whereDto(EntityFilterRequest filterRequest) throws Exception {
        try {
            if (null == filterRequest)
                throw new Exception("param filterRequest null");

            MstProvince dto = new MstProvince();
            for (EntityWhere entityWhere : filterRequest.getWhereList()) {
                Optional<String> opValue = Optional.ofNullable(entityWhere.getValue());
                if (!opValue.isPresent())
                    continue;

                if (entityWhere.getAttribute().equalsIgnoreCase("code")) {
                    dto.setCode(opValue.get());
                }
                if (entityWhere.getAttribute().equalsIgnoreCase("desc")) {
                    dto.setDesc(opValue.get());
                }
                if (entityWhere.getAttribute().equalsIgnoreCase("descOth")) {
                    dto.setDescOth(opValue.get());
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
    public MstProvince findById(String id) throws Exception {
        log.debug("ProvinceService.findById");

        try {
            if (StringUtils.isEmpty(id)) {
                throw new Exception("param id is null or empty");
            }

            TMstProvince entity = dao.find(id);
            if (null == entity) {
                throw new Exception("id: " + id);
            }
            this.initEnity(entity);

            return this.dtoFromEntity(entity);
        } catch (Exception ex) {
            log.error("ProvinceService.findById", ex);
            throw ex;
        }
    }

    @Override
    public MstProvince deleteById(String id, AppUser principal) throws Exception {
        log.debug("ProvinceService.deleteById");

        Date now = Calendar.getInstance().getTime();
        try {
            if (StringUtils.isEmpty(id))
                throw new Exception("param id null or empty");
            if (null == principal)
                throw new Exception("param prinicipal null");

            TMstProvince entity = dao.find(id);
            if (null == entity)
                throw new Exception("id: " + id);
            this.initEnity(entity);

            this.updateEntityStatus(entity, 'I');
            this.updateEntity(ACTION.MODIFY, entity, principal, now);

            MstProvince dto = dtoFromEntity(entity);
            this.delete(dto, principal);
            return dto;
        } catch (Exception ex) {
            log.error("ProvinceService.deleteById", ex);
            throw ex;
        }
    }

    @Override
    public List<MstProvince> filterBy(EntityFilterRequest filterRequest) throws Exception {
        try {
            if (null == filterRequest)
                throw new Exception("param filterRequest null");

            MstProvince dto = this.whereDto(filterRequest);
            if (null == dto)
                throw new Exception("whereDto null");

            filterRequest.setTotalRecords(super.countByAnd(dto));

            String selectClause = "from TMstProvince o ";
            String orderByClause = filterRequest.getOrderBy().toString();
            List<TMstProvince> entities = super.findEntitiesByAnd(dto, selectClause, orderByClause,
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

    public Optional<Object> getProvinceList(Map<String, String> params) throws Exception {
        log.debug("getProvinceList");
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

            List<MstProvince> en = this.filterBy(filterRequest);

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
    public MstProvince findML(MstProvince dto) throws Exception {
        return null;
    }
}
