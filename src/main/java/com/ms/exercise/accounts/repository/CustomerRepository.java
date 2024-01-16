package com.ms.exercise.accounts.repository;

import com.ms.exercise.accounts.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
