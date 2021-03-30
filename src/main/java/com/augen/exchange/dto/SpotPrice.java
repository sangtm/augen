package com.augen.exchange.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName(SpotPrice.ROOT_NAME)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpotPrice implements Serializable {
    static final String ROOT_NAME = "data";

    private String base;
    private String currency;
    private double amount;
}
