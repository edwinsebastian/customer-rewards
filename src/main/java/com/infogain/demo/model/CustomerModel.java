package com.infogain.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.List;

@Data
@Entity
public class CustomerModel extends Model {
    private String personalId;
    private String name;
    @JsonManagedReference
    @OneToMany(mappedBy = "customerModel")
    private List<TransactionModel> transactions = Collections.emptyList();

    public CustomerModel(String name, String personalId){
        this.name = name;
        this.personalId = personalId;
    }

    public CustomerModel(){}
}
