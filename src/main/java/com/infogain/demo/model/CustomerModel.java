package com.infogain.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
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
