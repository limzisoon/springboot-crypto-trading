package com.project.trading.service;

import com.project.trading.model.entity.Account;
import com.project.trading.model.response.AccountResponseDTO;
import com.project.trading.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public AccountResponseDTO getAccount(Integer id) throws Exception {
        Optional<Account> account = accountRepository.findById(id);

        List<Account> accountList = (List) accountRepository.findAll();

        System.out.println(accountList.size());

        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        if(account.isPresent())
        {
            BeanUtils.copyProperties(account.get(), accountResponseDTO);
        }

        return accountResponseDTO;
    }

}
