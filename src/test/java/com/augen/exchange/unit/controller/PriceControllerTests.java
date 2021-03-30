package com.augen.exchange.unit.controller;

import com.augen.exchange.controller.PriceController;
import com.augen.exchange.dto.PriceResponse;
import com.augen.exchange.global.Static;
import com.augen.exchange.service.MarketStorage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceController.class)
public class PriceControllerTests {

    @MockBean
    MarketStorage marketStorage;

    @Autowired
    private MockMvc mvc;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testCanGetPrice() throws Exception {
        double testingAmount = 2;
        double testingSpotPrice = 4.500;
        double testingProfitFactor = 0.05;
        double testingTotal = testingAmount * testingSpotPrice * (1 + testingProfitFactor);

        String url = Static.API_MAIN_PATH + "/price?amount=" + testingAmount;
        PriceResponse priceResponse = PriceResponse.builder().spotPrice(100).profitFactor(0).amount(testingAmount).totalPrice(testingTotal).build();
        String expectResponse = mapper.writeValueAsString(priceResponse);
        Mockito.when(marketStorage.calculatePrice(eq(testingAmount))).thenReturn(priceResponse);
        String response = mvc.perform(get(url)
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals(response, expectResponse);
    }

    @Test
    public void testWithInvalidInputAmount() throws Exception {
        String testingAmount = null;

        String url = Static.API_MAIN_PATH + "/price?amount=" + testingAmount;
        mvc.perform(get(url)
            .accept(APPLICATION_JSON))
            .andExpect(status().isBadRequest());

        testingAmount = "abc";
        url = Static.API_MAIN_PATH + "/price?amount=" + testingAmount;

        mvc.perform(get(url)
                .accept(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
