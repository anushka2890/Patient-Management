package com.pm.api_gateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

//this is a custom filter class to intercept http requests and decide whether to proceed with them or not based on this logic
@Component
public class JwtValidationGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    private final WebClient webClient;

    public JwtValidationGatewayFilterFactory(WebClient.Builder webClientBuilder,
           @Value("${auth.service.url}") String authServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(authServiceUrl).build();
    }
    //extending AbstractGatewayFilterFactory and implementing apply function tells spring cloud gateway to apply our filter automatically to the request lifecycle
    @Override
    public GatewayFilter apply(Object config) {
        //exchange is passed by the gateway for the current request
        //chain manages the chain of filters that currently exists in the filter chain
        return (exchange, chain) -> {
            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if(token == null || !token.startsWith("Bearer")){
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            return webClient.get()
                    .uri("/validate")//sets the uri for validate endpoint of auth
                    .header(HttpHeaders.AUTHORIZATION, token)//set headers
                    .retrieve()//retrieve the response
                    .toBodilessEntity()//tells the web client that there is no Body
                    .then(chain.filter(exchange));//if response is success then continue with request
        };
    }
}
