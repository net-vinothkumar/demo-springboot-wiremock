package com.interviewDOT.search.service;



import com.interviewDOT.search.model.ProductCatalog;

import java.util.List;

public interface RemoteProductCatalogServiceClient {

    List<ProductCatalog> getProductCatalogs();
}
