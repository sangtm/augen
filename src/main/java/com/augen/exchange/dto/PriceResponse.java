package com.augen.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceResponse implements Serializable {
    private String Id;
    private double spotPrice;
    private double profitFactor;
    private double amount;
    private double totalPrice;
}
