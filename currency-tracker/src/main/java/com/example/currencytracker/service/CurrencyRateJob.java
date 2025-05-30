package com.example.currencytracker.service;

import com.example.currencytracker.entity.Currency;
import com.example.currencytracker.repository.CurrencyRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CurrencyRateJob {
    private final RestTemplate restTemplate;
    private final CurrencyRepository currencyRepository;

    public CurrencyRateJob(RestTemplate restTemplate, CurrencyRepository currencyRepository) {
        this.restTemplate = restTemplate;
        this.currencyRepository = currencyRepository;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void fetchCurrencyRates() {
        String url = "https://www.cbr-xml-daily.ru/daily_json.js";

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.getBody()).get("Valute");

                List<Currency> tracked = currencyRepository.findAll();
                for (Currency currency : tracked) {
                    String code = currency.getName();
                    JsonNode valute = root.get(code);
                    if (valute != null) {
                        double value = valute.get("Value").asDouble();
                        double previous = valute.get("Previous").asDouble();
                        double changePercent = ((value - previous) / previous) * 100;

                        if (isSignificantChange(changePercent, currency.getPriceChangeRange())) {
                            System.out.printf("Уведомление: %s — %s%n", currency.getName(), currency.getDescription());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка при выполнении джобы: " + e.getMessage());
        }
    }

    private boolean isSignificantChange(double actualChange, String thresholdRange) {
        try {
            String[] parts = thresholdRange.split("/");
            for (String part : parts) {
                part = part.trim().replace("%", "");
                double expectedChange = Double.parseDouble(part);
                if ((expectedChange < 0 && actualChange <= expectedChange) ||
                        (expectedChange > 0 && actualChange >= expectedChange)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println("Невозможно разобрать threshold: " + thresholdRange);
        }
        return false;
    }
}
