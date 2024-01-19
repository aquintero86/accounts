package com.ms.exercise.accounts.service.impl;

import com.ms.exercise.accounts.constants.AccountsConstants;
import com.ms.exercise.accounts.dto.AccountDto;
import com.ms.exercise.accounts.dto.CustomerDTO;
import com.ms.exercise.accounts.entity.Accounts;
import com.ms.exercise.accounts.entity.Customer;
import com.ms.exercise.accounts.exception.CustomerAlreadyExistsException;
import com.ms.exercise.accounts.exception.ResourceNotFoundException;
import com.ms.exercise.accounts.mapper.AccountMapper;
import com.ms.exercise.accounts.mapper.CustomerMapper;
import com.ms.exercise.accounts.repository.AccountsRepository;
import com.ms.exercise.accounts.repository.CustomerRepository;
import com.ms.exercise.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {


    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        System.out.println(customer.toString());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("customer is already register "+customerDTO.getMobileNumber());
        }

        Customer customerSave= customerRepository.save(customer);
        accountsRepository.save(createNewAccount(customerSave));

    }

    @Override
    public CustomerDTO fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId((long) customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", String.valueOf(customer.getCustomerId()))
        );
        CustomerDTO customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDTO());
        customerDto.setAccountsDto(AccountMapper.mapToAccountsDto(accounts, new AccountDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDto) {
        boolean isUpdated = false;
        AccountDto accountsDto = customerDto.getAccountsDto();

        if(accountsDto !=null ){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", String.valueOf(accountsDto.getAccountNumber()))
            );
            AccountMapper.mapToAccounts(accountsDto, accounts);

            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId((long) customer.getCustomerId());
        customerRepository.deleteById((long) customer.getCustomerId());
        return true;
    }


    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }




}
