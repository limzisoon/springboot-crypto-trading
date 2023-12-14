package com.project.trading.controller;

import com.project.trading.model.response.AccountResponseDTO;
import com.project.trading.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(method = "GET", description = "Retrieve the Account information",summary = "Retrieve the Account information")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable("id") Integer id) {
        try {
            System.out.println("id" + id);
            AccountResponseDTO accountResponseDTO = accountService.getAccount(id);

            return new ResponseEntity<>(accountResponseDTO,HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception :",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}