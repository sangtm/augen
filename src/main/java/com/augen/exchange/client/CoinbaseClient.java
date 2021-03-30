package com.augen.exchange.client;

import com.augen.exchange.dto.SpotPrice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "coinbase-client", url = "${com.coinbase.url}")
public interface CoinbaseClient {
    @RequestMapping(method = RequestMethod.GET, value = "/prices/spot")
    SpotPrice getSpotPrice(@RequestParam(value = "currency") String currency);

}
