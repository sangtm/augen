package com.augen.exchange.kafka;


import com.augen.exchange.dto.PriceFactor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

@Configuration
public class ProfitProducer {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${app.augen.profitRates:0.05,0.1,0.12}")
    private List<Double> profitRates;

    @Bean
    public Supplier<PriceFactor> profitFactorProducer(){
        return () -> {
            PriceFactor priceFactor = new PriceFactor(0, profitRates.get(new Random().nextInt(profitRates.size())));
            logger.info("ProfitProducer Sent priceFactor: {}", priceFactor);
            return priceFactor;
        };
    };
}
