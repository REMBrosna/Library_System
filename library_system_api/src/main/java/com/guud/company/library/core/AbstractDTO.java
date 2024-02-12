package com.guud.company.library.core;

import com.guud.company.library.utils.COBeansUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public abstract class AbstractDTO<D, E> extends COAbstractEntity<D> {
    private static final long serialVersionUID = 2161178260327301388L;
    protected static Logger log = Logger.getLogger(COAbstractEntity.class);
    private String otherLangDesc;

    public AbstractDTO() {
    }

    public AbstractDTO(E entity) {
        this.fromEntity(entity);
    }

    public E toEntity(E entity) {
        try {
            if (entity != null) {
                BeanUtils.copyProperties(this, entity, COBeansUtil.getNullPropertyNames(this));
            }
        } catch (Exception var3) {
            log.error("toEntity");
        }

        return entity;
    }

    public void fromEntity(E entity) {
        if (entity != null) {
            BeanUtils.copyProperties(entity, this, COBeansUtil.getNullPropertyNames(entity));
        }

    }

    public Set<ConstraintViolation<D>> validate(D dto) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(dto, new Class[0]);
    }

    public String getOtherLangDesc() {
        return this.otherLangDesc;
    }

    public void setOtherLangDesc(String otherLangDesc) {
        this.otherLangDesc = otherLangDesc;
    }
}
