package com.muhammedtopgul.springexcel.entity;

import com.muhammedtopgul.springexcel.annotation.ExcelColumn;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author muhammed-topgul
 * @since 18.04.2022 11:05
 */

@Entity
@Table(name = "t_student")
public class StudentEntity extends BaseEntity {
    @ExcelColumn(columnHeader = "Address")
    private String address;
    @ExcelColumn(columnHeader = "Province")
    private String city;
    @ExcelColumn
    private String pin;

    public StudentEntity() {
    }

    public StudentEntity(Long id, String name, String address, String city, String pin) {
        super(id, name);
        this.address = address;
        this.city = city;
        this.pin = pin;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPin() {
        return pin;
    }
}
