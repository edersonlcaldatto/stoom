package com.qualification.stoom.Util;

import com.qualification.stoom.model.Address;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Component
public class GeoCode {

    @Value("${google.maps.baseUrl}")
    private String URL_MAPS;

    @Value("${google.maps.api-key}")
    private String API_KEY;

    public void findAndGeolocationFromAddress(Address address) {

        StringBuilder addressBuilder = new StringBuilder();
        addressBuilder.append(address.getStreetName())
                .append(" ")
                .append(address.getNumber())
                .append(" ")
                .append(address.getNeighborhood())
                .append(" ")
                .append(address.getCity())
                .append(" ")
                .append(address.getState())
                .append(" ")
                .append(address.getZipCode())
                .append(" ")
                .append(address.getCountry());


        String encodedQuery = URLEncoder.encode(addressBuilder.toString(), StandardCharsets.UTF_8);
        StringBuilder requestUri = new StringBuilder();
        requestUri.append(URL_MAPS)
                .append(encodedQuery)
                .append(String.format("CA&key=%s", API_KEY));

        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(requestUri.toString()))
                .timeout(Duration.ofMillis(2000)).build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject json = new JSONObject(response.body().toString());

        if (json.getString("status").equals("OK")){
            JSONArray results = json.getJSONArray("results");
            JSONObject firstResult = (JSONObject) results.get(0);

            JSONObject location = firstResult.getJSONObject("geometry").getJSONObject("location");

            address.setLatitude(location.getBigDecimal("lat"));
            address.setLongitude(location.getBigDecimal("lng"));
        }else{
            throw new NoResultException("Error to get the lat/lng from address: "+ response);
        }
    }
}
