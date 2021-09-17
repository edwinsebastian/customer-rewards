package com.infogain.demo.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@Data
@MappedSuperclass
public class Model implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;
}
