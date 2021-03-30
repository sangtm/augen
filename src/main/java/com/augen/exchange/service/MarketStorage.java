package com.augen.exchange.service;

import com.augen.exchange.dto.PriceFactor;
import com.augen.exchange.dto.PriceResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Just a DB simulator to store data for this test
 */

@Service
public class MarketStorage {
    private PriceFactor market = new PriceFactor();

    public void updateMarket(PriceFactor in){
        if ( in != null) {
            market.setProfitFactor(in.getProfitFactor());
            market.setSpotPrice(in.getSpotPrice());
        }
    }

    public PriceFactor getMarket(){
        return this.market;
    }

    public PriceResponse calculatePrice(double amount){
        return  PriceResponse.builder()
                .Id(UUID.randomUUID().toString())
                .spotPrice(this.market.getSpotPrice())
                .profitFactor(this.market.getProfitFactor())
                .amount(amount)
                .totalPrice(amount * this.market.getSpotPrice() * ( 1 + this.market.getProfitFactor()))
                .build();
    }
}
