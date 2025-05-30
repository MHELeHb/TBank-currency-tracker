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

    private String baseCurrency;

    private String priceChangeRange;

    private String description;
}