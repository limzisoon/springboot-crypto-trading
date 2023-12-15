package com.project.trading.service;

import com.project.trading.constant.CommonConstant;
import com.project.trading.model.entity.Account;
import com.project.trading.model.entity.Trade;
import com.project.trading.model.entity.Transaction;
import com.project.trading.model.request.AccountRequestDTO;
import com.project.trading.model.request.TradeRequestDTO;
import com.project.trading.model.request.TransactionRequestDTO;
import com.project.trading.repository.AccountRepository;
import com.project.trading.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    public Transaction trade(TransactionRequestDTO transactionRequestDTO)
    {
        AccountRequestDTO accountRequestDTO = transactionRequestDTO.getAccountRequestDTO();
        Account account = Account.builder().build();
        BeanUtils.copyProperties(accountRequestDTO, account);

        List<TradeRequestDTO> tradeDtoList = transactionRequestDTO.getTradeRequestDTOs();
        List<Trade> sellTradesSet = new ArrayList<>();

        Double totalSell = new Double(0);
        Double totalBuy = new Double(0);
        for(TradeRequestDTO tradeRequestDTO: tradeDtoList)
        {
            Trade t = new Trade();
            t.setSymbol(tradeRequestDTO.getSymbol());
            t.setBuySell(tradeRequestDTO.getBuySell());
            t.setIndividualPrice(tradeRequestDTO.getIndividualPrice());
            t.setQuantity(tradeRequestDTO.getQuantity());

            if(CommonConstant.SELL.equals(tradeRequestDTO.getBuySell()))
            {
                totalSell =+ tradeRequestDTO.getIndividualPrice() * tradeRequestDTO.getQuantity();
            }
            else if(CommonConstant.BUY.equals(tradeRequestDTO.getBuySell()))
            {
                totalBuy =+ tradeRequestDTO.getIndividualPrice() * tradeRequestDTO.getQuantity();
            }
            sellTradesSet.add(t);
        }

        System.out.print("totalBuy :" +totalBuy+ " ,totalSell :" + totalSell);

        Transaction trx = Transaction.builder()
                .accountId(account.getId())
                .timestampdate(new Date())
                .allTrades(sellTradesSet)
                .build();

        Transaction currenTransaction = transactionRepository.save(trx);

        //trade buy/sale cryptos, deduct/add into wallet
        Optional<Account> acct = accountRepository.findById(accountRequestDTO.getId());
        if(acct.isPresent())
        {
            if(acct.get().getWalletBalance()!=null)
            {
                acct.get().setWalletBalance(acct.get().getWalletBalance() + totalSell);
                acct.get().setWalletBalance(acct.get().getWalletBalance() - totalBuy);
                accountRepository.save(acct.get());
            }
        }
        return currenTransaction;
    }

    public List<Transaction> getTransactionsByAcctId(Integer accountId)
    {
        return transactionRepository.findByAccountId(accountId);
    }
}
