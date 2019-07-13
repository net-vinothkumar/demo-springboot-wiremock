package com.interviewDOT.productcatalog.service;


import com.interviewDOT.productcatalog.model.ProductCatalog;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductCatalogService {

    public List<ProductCatalog> getProductCatalogList() {
        return Arrays.asList(
                new ProductCatalog("1","Apple Watch Series 4 LTE", "Q501-060","60000"),
                new ProductCatalog("2","Samsung Galaxy Tab 2 10.1", "Q501-061","100000"),
                new ProductCatalog("3","Virtual Reality 3D 4K Camera", "Q501-062","20000"),
                new ProductCatalog("4","Apple MacBook Air, 13-inch, Intel Dual-Core i5, Silver 128 GB", "Q501-063","110000"),
                new ProductCatalog("5","HyperX Cloud Gaming Headset Flight", "Q501-064","9000")
        );
    }
}
