package com.example.currencytracker.service;

import com.example.currencytracker.dto.CurrencyRequest;
import com.example.currencytracker.entity.Currency;
import com.example.currencytracker.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    private final CurrencyRepository repository;

    public CurrencyService(CurrencyRepository repository) {
        this.repository = repository;
    }

    public List<Currency> findAll() {
        return repository.findAll();
    }

    public Optional<Currency> findById(String id) {
        return repository.findById(id);
    }

    public void save(CurrencyRequest request) {
        Currency currency = new Currency();
        currency.setId(request.getId());
        currency.setName(request.getName());
        currency.setBaseCurrency(request.getBaseCurrency());
        currency.setPriceChangeRange(request.getPriceChangeRange());
        currency.setDescription(request.getDescription());
        repository.save(currency);
    }

    public void createCurrency(CurrencyRequest request) {
        save(request);
    }

    public void update(String id, CurrencyRequest request) {
        Optional<Currency> existing = repository.findById(id);
        existing.ifPresent(currency -> {
            currency.setName(request.getName());
            currency.setBaseCurrency(request.getBaseCurrency());
            currency.setPriceChangeRange(request.getPriceChangeRange());
            currency.setDescription(request.getDescription());
            repository.save(currency);
        });
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}