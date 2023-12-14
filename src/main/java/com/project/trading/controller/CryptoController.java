package com.project.trading.controller;

import com.project.trading.model.entity.Crypto;
import com.project.trading.service.CryptoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/cryptos")
public class CryptoController {

    @Autowired
    CryptoService cryptoService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(method = "GET", description = "retrieve the latest best aggregated price",summary = "retrieve the latest best aggregated price")
    public ResponseEntity<List<Crypto>> getAllAggregatedPrice() {
        try {
            log.debug("getAllAggregatedPrice");
            List<Crypto> cryptos = cryptoService.getAllAggregatedPrice();

            if (cryptos.isEmpty()) {
                log.debug("no cryptos found");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.debug("cryptos size: " + cryptos.size());

            return new ResponseEntity<>(cryptos, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception :",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/retrieve")
    @ResponseStatus(HttpStatus.OK)
    @Operation(method = "GET", description = "retrieve the latest best aggregated price from source",summary = "retrieve the latest best aggregated price from source")
    public ResponseEntity<List<Crypto>> retrieveCryptosFromSource() {
        try {
            log.debug("retrieveCryptosFromSource");
            cryptoService.retrieveCryptosFromSource();

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception :",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}