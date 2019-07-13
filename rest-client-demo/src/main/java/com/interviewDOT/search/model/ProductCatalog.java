package com.interviewDOT.search.model;

public class ProductCatalog {

    private String productCatalogId;
    private String productCatalogName;
    private String modelNumber;
    private String price;

    public ProductCatalog() {
    }

    public ProductCatalog(String productCatalogId, String productCatalogName, String modelNumber, String price) {
        this.productCatalogId = productCatalogId;
        this.productCatalogName = productCatalogName;
        this.modelNumber = modelNumber;
        this.price = price;
    }

    public String getProductCatalogId() {
        return productCatalogId;
    }

    public void setProductCatalogId(String productCatalogId) {
        this.productCatalogId = productCatalogId;
    }

    public String getProductCatalogName() {
        return productCatalogName;
    }

    public void setProductCatalogName(String productCatalogName) {
        this.productCatalogName = productCatalogName;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
