package com.infogain.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Data
@Entity
public class TransactionModel extends Model {
    private double value;
    private Date date;
}
