package com.infogain.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@Entity
public class TransactionModel extends Model {
    private double value;
    private Date date;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(columnDefinition = "customer_model_id")
    private CustomerModel customerModel = new CustomerModel();

    public TransactionModel(double value, Date date){
        this.value = value;
        this.setDate(date);
    }

    public TransactionModel(){}
}
