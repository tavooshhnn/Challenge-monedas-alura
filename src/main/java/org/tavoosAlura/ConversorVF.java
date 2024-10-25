package org.tavoosAlura;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.tavoosAlura.consultaAPI.*;

public class ConversorVF {

    public void getConversionRates(String ... args)  {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .header("Accept", "application/json")
                .GET();
        URI uri;

        // Construir la URL según el número de argumentos
        if(args == null || args.length == 0) {
            // Sin argumentos, usar la URL por defecto
            uri = URI.create(getDefaultRatesUrl());
        } else if(args.length == 1) {
            // Un argumento, obtener tasas para una moneda específica
            uri = URI.create(getRatesByCurrency(args[0]));
        } else if(args.length == 2) {
            // Dos argumentos, obtener tasa entre dos monedas
            uri = URI.create(getPairRate(args[0], args[1]));
        } else {
            throw new IllegalArgumentException("Numero y argumento incorrectos. Usa 0, 1 o 2 argumentos.");
        }

        // Construir y enviar la solicitud HTTP
        HttpRequest request = requestBuilder.uri(uri).build();
        HttpResponse<String> response;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("\n" + response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error solicitando en HTTP: " + e.getMessage(), e);
        }
    }
}
