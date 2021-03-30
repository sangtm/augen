package com.augen.exchange.kafka;

import com.augen.exchange.dto.PriceFactor;
import com.augen.exchange.service.MarketStorage;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class MarketConsumer {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final MarketStorage marketStorageService;

    @Autowired
    public MarketConsumer(MarketStorage marketStorageService) {
        this.marketStorageService = marketStorageService;
    }

    @Bean
    public Consumer<KStream<String, PriceFactor>> marketUpdateConsumer(){
        return stream -> stream.foreach((key, value) -> {
            if (value != null) {
                marketStorageService.updateMarket(value);
                logger.info("Market Consumed: {}", value);
            }
        });
    }

}
