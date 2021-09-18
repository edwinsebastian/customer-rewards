package com.infogain.demo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CustomerDTO {
    private String name;
    private String personalId;

    public CustomerDTO(String name, String personalId) {
        this.name = name;
        this.personalId = personalId;
    }

    public CustomerDTO(CustomerModel customerModel) {
        this.name = customerModel.getName();
        this.personalId = customerModel.getPersonalId();
    }

    public static CustomerModel toCustomerModel(CustomerDTO customerDTO){
        CustomerModel customerModel = new CustomerModel(customerDTO.getName(), customerDTO.getPersonalId());

        return customerModel;
    }
}
