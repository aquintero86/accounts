package com.ms.exercise.accounts.repository;

import com.ms.exercise.accounts.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {


    Optional<Customer> findByMobileNumber(String mobileNumber);
}
