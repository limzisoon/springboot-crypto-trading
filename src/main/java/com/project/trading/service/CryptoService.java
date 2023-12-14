package com.project.trading.service;

import com.project.trading.model.entity.Crypto;
import com.project.trading.repository.CryptoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CryptoService {

    @Autowired
    CryptoRepository cryptoRepository;

    public List<Crypto> getAllAggregatedPrice() throws Exception {
        List<Crypto> stocks = new ArrayList<Crypto>();

        cryptoRepository.findAll().forEach(stocks::add);

        return stocks;
    }

    public void retrieveCryptosFromSource() throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.binance.com/api/v3/ticker/bookTicker";

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);

        ResponseEntity<Crypto[]> response = restTemplate.getForEntity(url, Crypto[].class);

        List<Crypto> cryptos = Arrays.asList(response.getBody());

        cryptoRepository.truncateTable();

        cryptoRepository.saveAll(cryptos);
    }
}
