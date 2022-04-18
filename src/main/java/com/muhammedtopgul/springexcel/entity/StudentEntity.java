package com.muhammedtopgul.springexcel.entity;

import javax.persistence.*;

/**
 * @author muhammed-topgul
 * @since 18.04.2022 11:05
 */

@Entity
@Table(name = "t_student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String city;
    private String pin;

    public StudentEntity() {
    }

    public StudentEntity(Long id, String name, String address, String city, String pin) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.pin = pin;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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
