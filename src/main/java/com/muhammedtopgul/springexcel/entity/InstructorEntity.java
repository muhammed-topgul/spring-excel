package com.muhammedtopgul.springexcel.entity;

import javax.persistence.Entity;

/**
 * @author muhammed-topgul
 * @since 18.04.2022 14:31
 */

@Entity
public class InstructorEntity extends BaseEntity {
    private String department;
    private String title;

    public InstructorEntity() {
    }

    public InstructorEntity(Long id, String name, String department, String title) {
        super(id, name);
        this.department = department;
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public String getTitle() {
        return title;
    }
}
