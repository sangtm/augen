package com.augen.exchange.unit.dto;

import com.augen.exchange.dto.PriceResponse;
import com.augen.exchange.dto.SpotPrice;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PriceResponseTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testSerialize() throws IOException {
        double testingAmount = 2;
        double testingSpotPrice = 4.500;
        double testingProfitFactor = 0.05;

        PriceResponse priceResponse = PriceResponse.builder()
                .spotPrice(testingSpotPrice)
                .profitFactor(testingProfitFactor)
                .Id("testing-id")
                .amount(testingAmount).build();
        String serialized = mapper.writeValueAsString(priceResponse);

        String expected ="{\"spotPrice\":4.5,\"profitFactor\":0.05,\"amount\":2.0,\"totalPrice\":0.0,\"id\":\"testing-id\"}";
        assertEquals(expected, serialized);
    }

    @Test
    public void testDeserialize() throws IOException {
        double testingAmount = 2;
        double testingSpotPrice = 4.500;
        double testingProfitFactor = 0.05;
        String inputJson = "{\"spotPrice\":4.5,\"profitFactor\":0.05,\"amount\":2.0,\"totalPrice\":0.0,\"id\":\"testing-id\"}";
        PriceResponse deserialized = mapper.readValue(inputJson, PriceResponse.class);
        PriceResponse expected = PriceResponse.builder()
                .spotPrice(testingSpotPrice)
                .profitFactor(testingProfitFactor)
                .Id("testing-id")
                .amount(testingAmount).build();
        assertTrue(expected.equals(deserialized));
    }
}
