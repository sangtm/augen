package com.augen.exchange.unit.service;

import com.augen.exchange.dto.PriceFactor;
import com.augen.exchange.dto.PriceResponse;
import com.augen.exchange.service.MarketStorage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MarketStorageTest {

    private MarketStorage marketStorage = new MarketStorage();

    @Test
    public void testUpdateMarketAndGetMarket() throws Exception {
        double testingSpotPrice = 9.5;
        double testingProfitFactor = 0.12;
        PriceFactor input = new PriceFactor(testingSpotPrice, testingProfitFactor);
        marketStorage.updateMarket(input);
        PriceFactor output = marketStorage.getMarket();
        assertTrue(input.equals(output));
    }

    @Test
    public void testCalculateMarket() throws Exception {
        double testingAmount = 3.5;
        double testingSpotPrice = 9.5;
        double testingProfitFactor = 0.12;
        PriceFactor input = new PriceFactor(testingSpotPrice, testingProfitFactor);
        marketStorage.updateMarket(input);
        PriceResponse output = marketStorage.calculatePrice(testingAmount);
        assertEquals(output.getTotalPrice(), testingAmount * testingSpotPrice * (testingProfitFactor + 1), 0.01);
    }
}
