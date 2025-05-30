package com.example.currencytracker.entity;

public class Currency {
    private Long id;
    private String name;
    private String description;
    private String priceChangeRange;
    private String baseCurrency;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPriceChangeRange() {
        return priceChangeRange;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriceChangeRange(String priceChangeRange) {
        this.priceChangeRange = priceChangeRange;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }
}
