# demo-springboot-wiremock

A spring boot demo application with wiremock

This project explains how to use wiremock in springboot application

## WireMock
> Mock your APIs for fast, robust and comprehensive testing
WireMock is a simulator for HTTP-based APIs. Some might consider it a service virtualization tool or a mock server.

> It enables you to stay productive when an API you depend on doesn't exist or isn't complete. It supports testing of edge cases and failure modes that the real API won't reliably produce. And because it's fast it can reduce your build time from hours down to minutes.

Refer : http://wiremock.org/

Let assume, you are using some external service which is not ready yet in our example 'product catalog service'. Its still in development. But the external service team shared the request and response to you for the respective 'product catalog service'.

You dont need to wait for the external service to make your development complete. You can create a mock api using wiremock based on the given response which they provided.

It will be helpful when you run integration test.

Hereby I have shared the example for the wiremock with springboot.

## Application Demo:

## 'Product Catalog Service':

I have created the simple rest service application which return list of product catalogs.

## Application Name: rest-service-demo

## Output:

Note: (Assume this an external service / remote service)

<img width="1398" alt="Screen Shot 2019-07-13 at 09 06 34" src="https://user-images.githubusercontent.com/30971809/61168394-f26ff880-a54d-11e9-844c-2cce67665b75.png">

## Application 2:

I have created the client application 'Search Client Service' which consumes the 'Product Catalog Service' (external one)

## Scenario :

Assume the external rest service is not ready but we have a sample response. Using wiremock, we can continue our development and do integration testing.

pom.xml (Added wiremock dependency)
```
<dependency>
    <groupId>com.github.tomakehurst</groupId>
	<artifactId>wiremock</artifactId>
	<version>2.19.0</version>
	<scope>test</scope>
</dependency>
```
ProductSearchControllerTest.java
```
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
```

Used application-integration.properties for integration testing
```
remote.url=http://localhost:9998/productcatalogs
server.port=8093
```
Created Sample response.json in test resource folder for mock
```
[
  {
    "productCatalogId": "1",
    "productCatalogName": "Apple Watch Series 4 LTE",
    "modelNumber": "Q501-060",
    "price": "60000"
  },
  {
    "productCatalogId": "2",
    "productCatalogName": "Samsung Galaxy Tab 2 10.1",
    "modelNumber": "Q501-061",
    "price": "100000"
  },
  {
    "productCatalogId": "3",
    "productCatalogName": "Virtual Reality 3D 4K Camera",
    "modelNumber": "Q501-062",
    "price": "20000"
  },
  {
    "productCatalogId": "4",
    "productCatalogName": "Apple MacBook Air, 13-inch, Intel Dual-Core i5, Silver 128 GB",
    "modelNumber": "Q501-063",
    "price": "110000"
  },
  {
    "productCatalogId": "5",
    "productCatalogName": "HyperX Cloud Gaming Headset Flight",
    "modelNumber": "Q501-064",
    "price": "9000"
  }
]
```
Run the integration test ProductSearchControllerTest.java

<img width="1646" alt="Screen Shot 2019-07-13 at 09 11 12" src="https://user-images.githubusercontent.com/30971809/61168419-44b11980-a54e-11e9-8e08-8306cb599ac2.png">

