package com.ms.exercise.accounts.mapper;

import com.ms.exercise.accounts.dto.AccountDto;
import com.ms.exercise.accounts.entity.Accounts;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper( AccountMapper.class );

    public AccountDto mapToAccountDTO(Accounts accounts );

    public Accounts mapToAccounts(AccountDto accounts );

}
