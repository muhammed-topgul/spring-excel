package com.muhammedtopgul.springexcel.entity;

import com.muhammedtopgul.springexcel.annotation.ExcelColumn;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author muhammed-topgul
 * @since 18.04.2022 14:28
 */

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ExcelColumn
    private String name;

    public BaseEntity() {
    }

    public BaseEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
