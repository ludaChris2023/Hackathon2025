package com.example.numberdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class DatabaseAPIService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL = "https://ucgilmxzpbqoiohcoyru.supabase.co/rest/v1/Games";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InVjZ2lsbXh6cGJxb2lvaGNveXJ1Iiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc0MzgyMDQ5NiwiZXhwIjoyMDU5Mzk2NDk2fQ.zPvPArjFWdeVJ2Y3_xuVMBY4sAJjkq7oPLR98ZVDdzU";

    public String fetchGamesByLeague(String league) {
        //Set up API-Key Header
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", API_KEY);
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.set("Content-Type", "application/json");

        //The Request
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make GET request and fetch response as String
        ResponseEntity<String> response = restTemplate.exchange(URL + "?League=eq." + league, HttpMethod.GET, entity,  String.class);

        return response.getBody(); // Return the response
    }
}