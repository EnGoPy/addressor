package com.engobytes.addressor.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

    @Autowired
    SearchProperties searchProperties;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
