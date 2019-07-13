package com.interviewDOT.search.service;


import com.interviewDOT.search.model.ProductCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RemoteProductCatalogServiceClientImpl implements RemoteProductCatalogServiceClient {

    @Value(value = "${remote.url}")
    private String remoteServiceURL;

    private final RestTemplate restTemplate;

    @Autowired
    public RemoteProductCatalogServiceClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ProductCatalog> getProductCatalogs() {
        ResponseEntity<List<ProductCatalog>> responseEntity = restTemplate.exchange(remoteServiceURL, HttpMethod.GET,null,
                new ParameterizedTypeReference<List<ProductCatalog>>(){});
        return responseEntity.getBody();
    }
}
