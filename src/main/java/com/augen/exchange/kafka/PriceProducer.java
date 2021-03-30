package com.augen.exchange.kafka;


import com.augen.exchange.dto.PriceFactor;
import com.augen.exchange.dto.SpotPrice;
import com.augen.exchange.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.function.Supplier;

@Configuration
public class PriceProducer {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final PriceService priceService;

    @Autowired
    public PriceProducer(PriceService priceService) {
        this.priceService = priceService;
    }

    @Bean
    public Supplier<PriceFactor> spotPriceProducer(){
        return () -> {
            SpotPrice spotPrice = priceService.getSpotPrice();
            PriceFactor priceFactor = new PriceFactor(spotPrice.getAmount(), 0);
            logger.info("PriceProducer Sent priceFactor: {}", priceFactor);
            return priceFactor;
        };
    };
}
