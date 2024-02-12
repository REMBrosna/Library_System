package com.guud.company.library.infrastructure.domain;
import com.guud.company.library.administrator.domain.AppUser;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@MappedSuperclass
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditableCustom extends AbstractPersistableCustom implements Auditable<AppUser, Long, Instant> {

    private static final long serialVersionUID = 141481953116476081L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdby_id")
    private AppUser createdBy;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lastmodifiedby_id")
    private AppUser lastModifiedBy;

    @Column(name = "lastmodified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Override
    public Optional<AppUser> getCreatedBy() {
        return Optional.ofNullable(this.createdBy);
    }

    @Override
    public void setCreatedBy(final AppUser createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Optional<Instant> getCreatedDate() {
        return null == this.createdDate ? Optional.empty() : Optional.of(this.createdDate.toInstant());
    }

    @Override
    public void setCreatedDate(final Instant createdDate) {
        this.createdDate = null == createdDate ? null : Date.from(createdDate);
    }

    @Override
    public Optional<AppUser> getLastModifiedBy() {
        return Optional.ofNullable(this.lastModifiedBy);
    }

    @Override
    public void setLastModifiedBy(final AppUser lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public Optional<Instant> getLastModifiedDate() {
        return null == this.lastModifiedDate ? Optional.empty() : Optional.of(this.lastModifiedDate.toInstant());
    }

    @Override
    public void setLastModifiedDate(final Instant lastModifiedDate) {
        this.lastModifiedDate = null == lastModifiedDate ? null : Date.from(lastModifiedDate);
    }

}
