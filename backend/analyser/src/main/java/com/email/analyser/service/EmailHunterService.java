package com.email.analyser.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.email.analyser.config.EmailHunterConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmailHunterService {
    private final EmailHunterConfig emailHunterConfig;
    private final ObjectMapper objectMapper;
    private final WebClient webClient = WebClient.builder().baseUrl("https://api.hunter.io/v2").build();

    public Map<String, String> getOrganisationName(String domainString) {
        String data = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/companies")
                        .path("/find")
                        .queryParam("domain", domainString)
                        .queryParam("api_key", emailHunterConfig.getKey())
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        try {
            JsonNode jsonNode = objectMapper.readTree(data);
            Map<String, String> result = new HashMap<String, String>();
            result.put("name", jsonNode.get("data").path("name").asText());
            result.put("logoUrl", jsonNode.get("data").path("logo").asText());
            return result;
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
