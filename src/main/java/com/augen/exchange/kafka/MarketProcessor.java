package com.augen.exchange.kafka;

import com.augen.exchange.dto.PriceFactor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MarketProcessor {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static double lastSportPrice = 0;
    private static double lastProfitFactor = 0;

    @Bean
    public Function<KStream<String, PriceFactor>, KStream<String, PriceFactor>> doMarketProcessor(){
        return kStream ->  kStream
                .filter((key,value) -> (value.getSpotPrice() != 0 && value.getSpotPrice() != lastSportPrice)
                        || (value.getProfitFactor() != 0  && value.getProfitFactor() != lastProfitFactor))
                .peek( (key,value) -> logger.info("Processing the Price Factor:  {}", value) )
                .map ( (key,value) -> {
                    double currentSpotPrice = value.getSpotPrice();
                    double currentProfitFactor = value.getProfitFactor();
                    if ( currentSpotPrice != 0 ) { // From spot price stream
                        logger.info("The Spot Price has been changed to {}, delta={}", currentSpotPrice, currentSpotPrice - lastSportPrice);
                        lastSportPrice = currentSpotPrice ;
                    }
                    if ( currentProfitFactor != 0 ) { // From profit stream
                        logger.info("The Profit Factor has been changed to {}, delta={}", currentProfitFactor, currentProfitFactor - lastProfitFactor);
                        lastProfitFactor = currentProfitFactor;
                    }
                    return new KeyValue<>(key, new PriceFactor(lastSportPrice, lastProfitFactor));
                })
                .mapValues(value -> value);
    }

}
