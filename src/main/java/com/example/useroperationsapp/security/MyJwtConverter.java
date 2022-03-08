package com.example.useroperationsapp.security;

import org.springframework.core.convert.converter.Converter;
//import org.springframework.security.oauth2.jwt.MappedJwtClaimSetConverter;
import java.util.Collections;
import java.util.Map;

public class MyJwtConverter {
        //implements Converter<Map<String, Object>, Map<String, Object>> {
/*
    private final MappedJwtClaimSetConverter delegate = MappedJwtClaimSetConverter.withDefaults(Collections.emptyMap());
    private static final String URL = "https://localhost:8080/swagger-ui/index.html";

    public Map<String, Object> convert(Map<String, Object> claims) {
        var convertedClaims = this.delegate.convert(claims);
        var userApp = convertedClaims.get(URL) != null ? (String) convertedClaims.get(URL) : "unknown";

        convertedClaims.put(URL, userApp.toUpperCase());

        return convertedClaims;
    }

 */
}