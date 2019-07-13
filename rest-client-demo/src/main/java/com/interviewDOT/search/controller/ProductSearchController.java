package com.interviewDOT.search.controller;


import com.interviewDOT.search.model.ProductCatalog;
import com.interviewDOT.search.service.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductSearchController {

    @Autowired
    private ProductSearchService productSearchService;

    @GetMapping("/products")
    public List<ProductCatalog> getProductCatalogs() {
        return productSearchService.getProductCatalogs();
    }

}
