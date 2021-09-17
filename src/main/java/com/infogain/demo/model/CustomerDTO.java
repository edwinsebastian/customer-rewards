package com.infogain.demo.model;

import lombok.Data;

@Data
public class CustomerDTO {
    private String name;
    private String personalId;

    public static CustomerModel toCustomerModel(CustomerDTO customerDTO){
        CustomerModel customerModel = new CustomerModel(customerDTO.getName(), customerDTO.getPersonalId());

        return customerModel;
    }
}
