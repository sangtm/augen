package com.augen.exchange.integration;

import com.augen.exchange.dto.PriceResponse;
import com.augen.exchange.global.Static;
import com.augen.exchange.service.MarketStorage;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.yaml")
public class RestIntegration {

    @Autowired
    MarketStorage marketStorage;

    @Autowired
    private MockMvc mvc;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testCanGetPrice() throws Exception {
        double testingAmount = 2;

        String url = Static.API_MAIN_PATH + "/price?amount=" + testingAmount;
        String response = mvc.perform(get(url)
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        PriceResponse priceResponse = mapper.readValue(response, PriceResponse.class);
        assertEquals(priceResponse.getAmount(), testingAmount);
        assertEquals(priceResponse.getTotalPrice(), priceResponse.getSpotPrice() * testingAmount * (priceResponse.getProfitFactor() + 1));
    }


}
