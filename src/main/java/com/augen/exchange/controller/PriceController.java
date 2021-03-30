package com.augen.exchange.controller;


import com.augen.exchange.dto.PriceResponse;
import com.augen.exchange.service.MarketStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.augen.exchange.global.Static;

@RestController
@RequestMapping(Static.API_MAIN_PATH + "/")
public class PriceController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MarketStorage marketStorageService;

    @Autowired
    public PriceController(MarketStorage marketStorageService) {
        this.marketStorageService = marketStorageService;
    }

    @GetMapping("price")
    public PriceResponse getPrice(@RequestParam(value = "amount") double amount) {
        logger.info("Received price request for amount: {}", amount);
        return marketStorageService.calculatePrice(amount);
    }
}
