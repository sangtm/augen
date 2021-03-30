package com.augen.exchange.unit.dto;

import com.augen.exchange.dto.SpotPrice;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class SpotPriceTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testSerialize() throws IOException {
        SpotPrice spotPrice = SpotPrice.builder().base("BTC").currency("NZD").amount(515151.51).build();
        String serialized = mapper.writeValueAsString(spotPrice);
        String expected ="{\"data\":{\"base\":\"BTC\",\"currency\":\"NZD\",\"amount\":515151.51}}";
        assertEquals(expected, serialized);
    }

    @Test
    public void testDeserialize() throws IOException {
        String inputJson ="{\"data\":{\"base\":\"BTC\",\"currency\":\"NZD\",\"amount\":515151.51}}";
        SpotPrice deserialized = mapper.readValue(inputJson, SpotPrice.class);
        SpotPrice expectedPrice = SpotPrice.builder().base("BTC").currency("NZD").amount(515151.51).build();
        assertTrue(expectedPrice.equals(deserialized));
    }
}
