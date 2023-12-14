package com.project.trading.controller;

import com.project.trading.model.entity.Transaction;
import com.project.trading.model.request.TransactionRequestDTO;
import com.project.trading.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping(value = "/trade", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(method = "POST", description = "trade cryptos",summary = "to buy/sell cryptos")
    public ResponseEntity<Transaction> trade(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        try
        {
            Transaction t = transactionService.trade(transactionRequestDTO);

            return new ResponseEntity<>(t, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/history/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(method = "GET", description = "Get the history transactions by Account Id",summary = "Get the history transactions by Account Id")
    public ResponseEntity<List<Transaction>> getTransactionsByAcctId(@PathVariable("accountId") Integer accountId) {
        try {
            System.out.println("getTransactionsByAcctId");
            List<Transaction> transactionList = transactionService.getTransactionsByAcctId(accountId);

            return new ResponseEntity<>(transactionList, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
