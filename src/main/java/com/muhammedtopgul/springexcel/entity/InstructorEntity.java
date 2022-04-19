package com.muhammedtopgul.springexcel.entity;

import com.muhammedtopgul.springexcel.annotation.ExcelColumn;
import lombok.Getter;

import javax.persistence.Entity;

/**
 * @author muhammed-topgul
 * @since 18.04.2022 14:31
 */

@Entity
@Getter
public class InstructorEntity extends BaseEntity {
    @ExcelColumn(exclude = true)
    private String department;
    @ExcelColumn(columnHeader = "Appellation")
    private String title;

    public InstructorEntity() {
    }

    public InstructorEntity(Long id, String name, String department, String title) {
        super(id, name);
        this.department = department;
        this.title = title;
    }
}
