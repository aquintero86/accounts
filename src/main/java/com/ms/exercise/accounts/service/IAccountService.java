package com.ms.exercise.accounts.service;

import com.ms.exercise.accounts.dto.CustomerDTO;

public interface IAccountService {
    void createAccount(CustomerDTO customerDTO);
    CustomerDTO fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDTO customerDto);


    boolean deleteAccount(String mobileNumber);

}
