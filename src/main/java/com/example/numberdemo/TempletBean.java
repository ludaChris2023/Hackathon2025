package com.example.numberdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TempletBean {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
