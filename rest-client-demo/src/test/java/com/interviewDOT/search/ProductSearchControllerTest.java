package com.interviewDOT.search;


import com.github.tomakehurst.wiremock.junit.WireMockRule;

import com.interviewDOT.search.model.ProductCatalog;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = RestClientDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles(value = "integration")
public class ProductSearchControllerTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(9998);

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void setUp() {
        mockRemoteService();
    }

    @Test
    public void shouldGetAllTheProducts() {
        ResponseEntity<List<ProductCatalog>> responseEntity = testRestTemplate.exchange("http://localhost:8093/products", HttpMethod.GET,null,
                new ParameterizedTypeReference<List<ProductCatalog>>(){}
        );
        List<ProductCatalog> productCatalogList = responseEntity.getBody();
        System.out.println("Products List ---> " + productCatalogList.size());
    }

    private void mockRemoteService() {
        stubFor(get(urlEqualTo("/productcatalogs"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("response.json")
                ));
    }
}
