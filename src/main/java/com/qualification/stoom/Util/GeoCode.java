package com.qualification.stoom.Util;

import com.qualification.stoom.model.Address;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Component
public class GeoCode {

    private static final String URL_MAPS = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private static final String API_KEY = "AIzaSyCj0cY2yEvVfYhAaTz3-P2MW-YRKmhz5Uw";

    private String GeocodeSync(String query) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();

        String encodedQuery = URLEncoder.encode(query,"UTF-8");
        String requestUri = URL_MAPS + encodedQuery + "CA&key=" + API_KEY;

        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(requestUri))
                .timeout(Duration.ofMillis(2000)).build();

        HttpResponse response = httpClient.send(request,
                HttpResponse.BodyHandlers.ofString());

        return response.body().toString();
    }

    public Map<String, Float> getGeolocationFromAddress(Address address) throws IOException, InterruptedException {

        Map<String, Float> coordinates = new HashMap<String, Float>();

        var response = GeocodeSync(address.getStreetName()+","+address.getNumber()+","+ address.getCity()+" "+address.getState()+", "+address.getZipCode());
        JSONObject json = new JSONObject(response);

        if (json.getString("status").equals("OK")){
            JSONArray results = json.getJSONArray("results");
            JSONObject firstResult = (JSONObject) results.get(0);

            JSONObject location = firstResult.getJSONObject("geometry").getJSONObject("location");

            coordinates.put("lat", location.getFloat("lat"));
            coordinates.put("lng", location.getFloat("lng"));
        }else{
            throw new NoResultException("Error to get the lat/lng from address: "+ response);
        }

        return coordinates;
    }
}
