package com.infogain.demo.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class CustomerModel extends Model {
    private String personalId;
    private String name;
}
