package com.augen.exchange.service;

import com.augen.exchange.client.CoinbaseClient;
import com.augen.exchange.dto.SpotPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PriceService {
    final private CoinbaseClient coinbaseClient;

    @Value("${app.augen.baseCurrency:NZD}")
    private String baseCurrency;

    @Autowired
    public PriceService(CoinbaseClient coinbaseClient) {
        this.coinbaseClient = coinbaseClient;
    }

    public SpotPrice getSpotPrice() {
        return coinbaseClient.getSpotPrice(baseCurrency);
    }
}
