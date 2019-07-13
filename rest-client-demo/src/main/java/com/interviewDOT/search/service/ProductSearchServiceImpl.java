package com.interviewDOT.search.service;

        import com.interviewDOT.search.model.ProductCatalog;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;

@Service
public class ProductSearchServiceImpl implements ProductSearchService {

    @Autowired
    RemoteProductCatalogServiceClient remoteProductCatalogServiceClient;

    public List<ProductCatalog> getProductCatalogs() {
        return remoteProductCatalogServiceClient.getProductCatalogs();
    }
}
