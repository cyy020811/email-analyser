package com.email.analyser.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class EmailHunterConfigAPI {
    @Value("${api.keys.emailHunter}")
    private String key;
}
