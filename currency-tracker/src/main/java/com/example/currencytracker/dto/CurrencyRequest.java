package com.example.currencytracker.dto;

public class CurrencyRequest {
    private Long id;
    private String name;
    private String baseCurrency;
    private String priceChangeRange;
    private String description;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getPriceChangeRange() {
        return priceChangeRange;
    }

    public String getDescription() {
        return description;
    }
}
