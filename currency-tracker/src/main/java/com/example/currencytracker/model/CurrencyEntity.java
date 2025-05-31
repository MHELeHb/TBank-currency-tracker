package com.example.currencytracker.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "currencies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @Column(name = "base_currency")
    private String baseCurrency;

    @Column(name = "price_change_range")
    private String priceChangeRange;

    private String description;
}