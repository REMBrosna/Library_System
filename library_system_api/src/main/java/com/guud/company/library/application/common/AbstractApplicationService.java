package com.guud.company.library.application.common;

import com.guud.company.library.application.apps.dto.Application;
import com.guud.company.library.application.apps.model.TApplication;
import com.guud.company.library.enums.ApplicationStatusEnum;
import com.guud.company.library.enums.ApplicationTypeEnum;
import com.guud.company.library.master.appStatus.dto.MstApplicationStatus;
import com.guud.company.library.master.appStatus.model.TMstApplicationStatus;
import com.guud.company.library.master.appType.dto.MstApplicationType;
import com.guud.company.library.master.appType.model.TMstApplicationType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractApplicationService<D> {

    /**
     *
     *                 Construct the FormDTO D. By initializing the associated DTOs.
     * @return
     */
    protected abstract D newApp(String parentId) throws Exception;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    protected abstract D createApp(D dto)
            throws Exception;

    /**
     * @param key
     *
     *            Fetch the form details based on Key from T_PEDI_APPS.
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    protected abstract D getApp(String key) throws Exception;

    /**
     * @param dto
     * @return
     *
     *         Method to save the content in D dto. If already present in DB, update
     *         the content.
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    protected abstract D saveUpdateData(D dto)
            throws Exception;

    /**
     * @param dto
     * @return
     *
     *         Do a soft delete for transactional tables, associated non-important
     *         tables will be hard delete.
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    protected abstract D deleteData(D dto) throws Exception;

    /**
     * Submits the application to respective port agency.
     *
     * @param dto
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    protected abstract D confirmApp(D dto)
            throws Exception;

    protected Application initApplicationByType(String applicationType){
        Application application = new Application();
        MstApplicationType mstApplicationType = new MstApplicationType();
        mstApplicationType.setAptCode(applicationType);
        MstApplicationStatus mstApplicationStatus = new MstApplicationStatus();
        mstApplicationStatus.setApsCode(ApplicationStatusEnum.DRF.name());
        application.setMstApplicationStatus(mstApplicationStatus);
        application.setMstApplicationType(mstApplicationType);
        return application;
    }

}
