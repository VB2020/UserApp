package com.example.useroperationsapp.security;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/actuator/**", "/actuator")
                .permitAll()
                .anyRequest()
                .authenticated();
                /*.and()
                .oauth2Client()
                .and()
                .oauth2ResourceServer()
                .jwt();
                .jwtAuthenticationConverter(new JwtAuthenticationConverter());
                 */
    }
/*
    @Bean
    public JwtDecoder customDecoder(OAuth2ResourceServerProperties properties) {
        properties.getJwt().setJwkSetUri("https://localhost:8080/swagger-ui/index.html");
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(
                properties.getJwt().getJwkSetUri()).build();

        jwtDecoder.setClaimSetConverter(new MyJwtConverter());
        return jwtDecoder;
    }
 */
}