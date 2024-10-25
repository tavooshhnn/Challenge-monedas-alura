package org.tavoosAlura;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public record consultaAPI() {
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/5efb2bbc100a8f559967b6ce";

    private static final String DEFAULT_CURRENCY = "USD";

    // Manejo de URL
    public static String getDefaultRatesUrl() {
        return BASE_URL + "/latest/" + DEFAULT_CURRENCY;
    }


    public static String getRatesByCurrency(String currencyCode) {
        return BASE_URL + "/latest/" + encodeValue(currencyCode);
    }


    public static String getPairRate(String currencyFrom, String currencyTo) {
        return BASE_URL + "/pair/" + encodeValue(currencyFrom) + "/" + encodeValue(currencyTo);
    }

    // Método para codificar valores y evitar errores de URL
    private static String encodeValue(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

}
