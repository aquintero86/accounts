package com.ms.exercise.accounts.mapper;



import com.ms.exercise.accounts.dto.CustomerDTO;
import com.ms.exercise.accounts.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );

    CustomerDTO mapToCustomerDTO(Customer customer );


    Customer mapToCustomer(CustomerDTO customer );
}
