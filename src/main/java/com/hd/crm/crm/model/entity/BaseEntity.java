package com.hd.crm.crm.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MappedSuperclass
public class BaseEntity implements Serializable {


    private static final long serialVersionUID = 7409271061329838159L;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "CREATE_DATE", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateDate() {
        String localDate = createDate.format(formatter);
        return LocalDateTime.parse(localDate, formatter);
    }

    @PrePersist
    public void setCreateDate() {
        this.createDate = LocalDateTime.now();
    }

    public LocalDateTime getUpdateDate() {
        if (updateDate != null) {
            String localDate = updateDate.format(formatter);
            return LocalDateTime.parse(localDate, formatter);
        }
        return updateDate;
    }

    @PreUpdate
    public void setUpdateDate() {
        this.updateDate = LocalDateTime.now();
    }
}
