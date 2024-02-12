package com.guud.company.library.application.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guud.company.library.application.utils.ApplicationServiceEntity;
import com.guud.company.library.core.AbstractDTO;
import com.guud.company.library.core.dto.EntityOrderBy;
import com.guud.company.library.core.dto.EntityWhere;
import com.guud.company.library.core.payload.EntityFilterRequest;
import com.guud.company.library.core.payload.EntityFilterResponse;
import com.guud.company.library.infrastructure.api.ApiResponse;
import io.jsonwebtoken.lang.Collections;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractApplicationController {

    // Static Attributes
    ////////////////////
    private static final Logger log = Logger.getLogger(AbstractApplicationController.class);

    // Attributes
    //////////////
    @Autowired
    protected ApplicationContext applicationContext;
    protected ObjectMapper objectMapper = new ObjectMapper();

    public ResponseEntity<Object> newApp(String appType, String parentId) {
        ApiResponse<Object> serviceStatus = new ApiResponse<>();
        try {
            if (StringUtils.isEmpty(appType)){
                serviceStatus.setMessage("param appType null or empty");
                serviceStatus.setCode(ApiResponse.INTERNAL_SERVER_ERROR);
                return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            Optional<Object> opDto = this.initApp(appType, parentId);

            if (!opDto.isPresent()) {
                serviceStatus.setMessage("entity null or empty");
                serviceStatus.setCode(ApiResponse.INTERNAL_SERVER_ERROR);
                return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            serviceStatus.setCode(ApiResponse.OK);
            serviceStatus.setData(opDto.get());
            return ResponseEntity.ok(serviceStatus);

        } catch (Exception ex) {
            serviceStatus.setMessage("newApp error");
            serviceStatus.setCode(ApiResponse.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> createApp(String appType, String object) {
        log.debug("createApp");

        ApiResponse<Object> serviceStatus = new ApiResponse<>();
        try {
            if (StringUtils.isEmpty(appType)){
                serviceStatus.setMessage("param appType null or empty");
                serviceStatus.setCode(ApiResponse.INTERNAL_SERVER_ERROR);
                return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Optional<Object> opDto = this.createAppType(appType, object);

            if (!opDto.isPresent()) {
                serviceStatus.setMessage("entity null or empty");
                serviceStatus.setCode(ApiResponse.INTERNAL_SERVER_ERROR);
                return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            serviceStatus.setData(opDto.get());
            serviceStatus.setCode(ApiResponse.OK);
            return ResponseEntity.ok(serviceStatus);

        } catch (Exception ex) {
            serviceStatus.setMessage("createApp error");
            serviceStatus.setCode(ApiResponse.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getApp(String appType, String appId) {
        log.debug("getApp");
        ApiResponse<Object> serviceStatus = new ApiResponse<>();

        try {
            if (StringUtils.isEmpty(appType)){
                serviceStatus.setMessage("param appType null or empty");
                serviceStatus.setCode(ApiResponse.INTERNAL_SERVER_ERROR);
                return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Optional<Object> opDto = this.getAppById(appType, appId);

            if (!opDto.isPresent()) {
                serviceStatus.setMessage("entity null or empty");
                serviceStatus.setCode(ApiResponse.INTERNAL_SERVER_ERROR);
                return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            serviceStatus.setData(opDto.get());
            serviceStatus.setCode(ApiResponse.OK);
            return ResponseEntity.ok(opDto.get());

        } catch (Exception ex) {
            serviceStatus.setMessage("getApp error");
            serviceStatus.setCode(ApiResponse.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getApps(String appType, @RequestParam Map<String, String> params) {
        ApiResponse<Object> serviceStatus = new ApiResponse<>();
        try {
            if (StringUtils.isEmpty(appType)){
                serviceStatus.setMessage("param appType null or empty");
                serviceStatus.setCode(ApiResponse.INTERNAL_SERVER_ERROR);
                return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            Optional<Object> opEntity = this.getAppsBy(appType, params);
            return ResponseEntity.ok(opEntity.get());
        } catch (Exception ex) {
            serviceStatus.setMessage("getApps error");
            serviceStatus.setCode(ApiResponse.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateApp(String appType, String appId, String object) {
        log.debug("updateApp");
        ApiResponse<Object> serviceStatus = new ApiResponse<>();
        try {

            if (StringUtils.isEmpty(appType)){
                serviceStatus.setMessage("param appType null or empty");
                serviceStatus.setCode(ApiResponse.INTERNAL_SERVER_ERROR);
                return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            Optional<Object> opDto = this.doUpdateApp(appType, appId, object);

            if (!opDto.isPresent()) {
                serviceStatus.setData(null);
                throw new Exception("entity null or empty");
            }
            serviceStatus.setData(opDto.get());
            serviceStatus.setCode(ApiResponse.OK);
            return ResponseEntity.ok(serviceStatus);

        } catch (Exception ex) {
            serviceStatus.setMessage("updateApp error");
            serviceStatus.setCode(ApiResponse.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteApp(String appType, String appId) {
        log.debug("deleteApp");
        ApiResponse<Object> serviceStatus = new ApiResponse<>();
        try {

            if (StringUtils.isEmpty(appType)) {
                serviceStatus.setMessage("param appType null or empty");
                serviceStatus.setCode(ApiResponse.INTERNAL_SERVER_ERROR);
                return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            this.doDeleteApp(appType, appId);
            serviceStatus.setCode(ApiResponse.OK);
            serviceStatus.setData(true);
            return ResponseEntity.ok(serviceStatus);

        } catch (Exception ex) {
            serviceStatus.setMessage("deleteApp error");
            serviceStatus.setData(false);
            serviceStatus.setCode(ApiResponse.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> confirmApp(String appType, String object) {
        log.debug("confirmApp");

        ApiResponse<Object> serviceStatus = new ApiResponse<>();
        try {

            if (StringUtils.isEmpty(appType)){
                serviceStatus.setMessage("param appType null or empty");
                serviceStatus.setCode(ApiResponse.INTERNAL_SERVER_ERROR);
                return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            Optional<Object> opDto = this.confirmAppType(appType, object);
            if (!opDto.isPresent()) {
                serviceStatus.setMessage("entity null or empty");
                serviceStatus.setCode(ApiResponse.INTERNAL_SERVER_ERROR);
                return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            serviceStatus.setData(objectMapper.writeValueAsString(opDto.get()).getBytes(StandardCharsets.UTF_8));
            serviceStatus.setCode(ApiResponse.OK);
            return ResponseEntity.ok(serviceStatus);

        } catch (Exception ex) {
            serviceStatus.setMessage("createApp error");
            serviceStatus.setData(false);
            serviceStatus.setCode(ApiResponse.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Helper Methods
    /////////////////
    protected Optional<Object> initApp(String appType, String parentId) throws Exception {

        log.debug("initApp");
        try {
            if (StringUtils.isEmpty(appType))
                throw new Exception("param appType null or empty");
            if (!ApplicationServiceEntity.isExisting(appType).isPresent())
                throw new Exception("appType not mapped: " + appType);

            Object bean = applicationContext.getBean(ApplicationServiceEntity.getMasterServiceEntityByEntityName(appType).getEntityServices());
            IApplication<?, ?> app = (IApplication<?, ?>) bean;
            AbstractDTO<?, ?> appDto = (AbstractDTO<?, ?>) app.newApp(parentId);

            return Optional.of(appDto);

        } catch (Exception ex) {
            log.error("initApp", ex);
            throw ex;
        }
    }

    protected Optional<Object> createAppType(String appType, String object) throws Exception {
        log.debug("createAppType");
        try {
            if (StringUtils.isEmpty(appType))
                throw new Exception("param appType null or empty");
            if (StringUtils.isEmpty(object))
                throw new Exception("param object null or empty");
            if (!ApplicationServiceEntity.isExisting(appType).isPresent())
                throw new Exception("appType not mapped: " + appType);

            Class<?> appFormClass = Class.forName(ApplicationServiceEntity.getMasterServiceEntityByEntityName(appType).getEntityDTOs());
            Object dto = objectMapper.readValue(object, appFormClass);
            AbstractDTO<?, ?> appDto = (AbstractDTO<?, ?>) dto;

            Object bean = applicationContext.getBean(ApplicationServiceEntity.getMasterServiceEntityByEntityName(appType).getEntityServices());
            IApplication<?, ?> app = (IApplication<?, ?>) bean;
            dto = app.createAppObj(appDto);
            return Optional.of(dto);

        } catch (Exception ex) {
            log.error("createAppType", ex);
            throw ex;
        }
    }

    protected Optional<Object> confirmAppType(String appType, String object) throws Exception {
        log.debug("submitAppType");
        try {
            if (StringUtils.isEmpty(appType))
                throw new Exception("param appType null or empty");
            if (StringUtils.isEmpty(object))
                throw new Exception("param object null or empty");
            if (!ApplicationServiceEntity.isExisting(appType).isPresent())
                throw new Exception("appType not mapped: " + appType);

            Class<?> appFormClass = Class.forName(ApplicationServiceEntity.getMasterServiceEntityByEntityName(appType).getEntityDTOs());
            Object dto = objectMapper.readValue(object, appFormClass);
            AbstractDTO<?, ?> appDto = (AbstractDTO<?, ?>) dto;

            Object bean = applicationContext.getBean(ApplicationServiceEntity.getMasterServiceEntityByEntityName(appType).getEntityServices());
            IApplication<?, ?> app = (IApplication<?, ?>) bean;
            dto = app.confirmAppObj(appDto);
            return Optional.of(dto);
        } catch (Exception ex) {
            log.error("submitAppType", ex);
            throw ex;
        }
    }

    protected Optional<Object> getAppById(String appType, String appId) throws Exception {
        log.debug("getAppById");

        try {
            if (StringUtils.isEmpty(appType))
                throw new Exception("param appType null or empty");
            if (StringUtils.isEmpty(appId))
                throw new Exception("param id null or empty");
            if (!ApplicationServiceEntity.isExisting(appType).isPresent())
                throw new Exception("appType not mapped: " + appType);

            Object bean = applicationContext.getBean(ApplicationServiceEntity.getMasterServiceEntityByEntityName(appType).getEntityServices());
            IApplication<?, ?> app = (IApplication<?, ?>) bean;
            return Optional.of(app.getApp(appId));

        } catch (Exception ex) {
            log.error("getAppById", ex);
            throw ex;
        }
    }

    protected Optional<Object> getAppsBy(String appType, Map<String, String> params)
            throws Exception {
        log.debug("getAppsBy");

        try {
            if (StringUtils.isEmpty(appType))
                throw new Exception("param entity null or empty");
            if (Collections.isEmpty(params))
                throw new Exception("param params null or empty");

            if (!ApplicationServiceEntity.isExisting(appType).isPresent())
                throw new Exception("entity not mapped: " + appType);

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
                String searchParam = params.get("sSearch_" + String.valueOf(nIndex));
                String valueParam = params.get("mDataProp_" + String.valueOf(nIndex));
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

            if (!filterRequest.isValid())
                throw new Exception("Invalid request: " + filterRequest.toString());

            Object bean = applicationContext.getBean(ApplicationServiceEntity.getMasterServiceEntityByEntityName(appType).getEntityServices());
            IApplication<?, ?> app = (IApplication<?, ?>) bean;

            @SuppressWarnings("unchecked")
            List<Object> entities = (List<Object>) app.filterBy(filterRequest);
            EntityFilterResponse filterResponse = new EntityFilterResponse();
            filterResponse.setiTotalRecords(entities.size());
            filterResponse.setiTotalDisplayRecords(filterRequest.getTotalRecords());
            filterResponse.setAaData((ArrayList<Object>) entities);

            return Optional.of(filterResponse);
        } catch (Exception ex) {
            log.error("getAppsBy", ex);
            throw ex;
        }
    }

    protected Optional<Object> doUpdateApp(String appType, String appId, String object) throws Exception {
        log.debug("doUpdateApp");

        try {
            if (StringUtils.isEmpty(appType))
                throw new Exception("param appType null or empty");
            if (StringUtils.isEmpty(appId))
                throw new Exception("param id null or empty");
            if (StringUtils.isEmpty(object))
                throw new Exception("param object null or empty");
            if (!ApplicationServiceEntity.isExisting(appType).isPresent())
                throw new Exception("appType not mapped: " + appType);

            Class<?> dtoClass = Class.forName(ApplicationServiceEntity.getMasterServiceEntityByEntityName(appType).getEntityDTOs());
            Object dto = objectMapper.readValue(object, dtoClass);
            AbstractDTO<?, ?> entityDto = (AbstractDTO<?, ?>) dto;

            Object bean = applicationContext.getBean(ApplicationServiceEntity.getMasterServiceEntityByEntityName(appType).getEntityServices());
            IApplication<?, ?> app = (IApplication<?, ?>) bean;
            dto = app.updateAppObj(entityDto);
            return Optional.of(dto);

        } catch (Exception ex) {
            log.error("doUpdateApp", ex);
            throw ex;
        }
    }

    protected void doDeleteApp(String appType, String appId) throws Exception {
        log.debug("doDeleteApp");

        try {
            if (StringUtils.isEmpty(appType))
                throw new Exception("param appType null or empty");
            if (StringUtils.isEmpty(appId))
                throw new Exception("param id null or empty");
            if (!ApplicationServiceEntity.isExisting(appType).isPresent())
                throw new Exception("appType not mapped: " + appType);

            Object bean = applicationContext.getBean(ApplicationServiceEntity.getMasterServiceEntityByEntityName(appType).getEntityServices());
            IApplication<?, ?> app = (IApplication<?, ?>) bean;

            AbstractDTO<?, ?> appDto = (AbstractDTO<?, ?>) app.getApp(appId);
            if (null == appDto) {
                throw new Exception("appDto not found: " + appId);
            }
            app.deleteObj(appDto);

        } catch (Exception ex) {
            log.error("doDeleteApp", ex);
            throw ex;
        }
    }

    /**
     * @return the objectMapper
     */
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * @param objectMapper the objectMapper to set
     */
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
