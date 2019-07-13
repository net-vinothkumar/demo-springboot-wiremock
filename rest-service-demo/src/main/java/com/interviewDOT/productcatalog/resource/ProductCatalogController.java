package com.interviewDOT.productcatalog.resource;

import com.interviewDOT.productcatalog.model.ProductCatalog;
import com.interviewDOT.productcatalog.service.ProductCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductCatalogController {

    @Autowired
    private ProductCatalogService productCatalogService;

    @GetMapping("/productcatalogs")
    public List<ProductCatalog> getProductCatalogs() {
        return productCatalogService.getProductCatalogList();
    }

}
