package com.impaqgroup.pos.infrastructure;

import com.impaqgroup.pos.model.Product;
import com.impaqgroup.pos.model.repository.ProductRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryProductRepository implements ProductRepository {

    private static final Map<String, Product> REPO;

    static {
        REPO = new HashMap<>();
        REPO.put("123451", new Product("123451", "Product1", 22.22));
        REPO.put("123452", new Product("123452", "Product2", 20.22));
        REPO.put("123453", new Product("123453", "Product3", 30.22));
        REPO.put("123454", new Product("123454", "Product4", 22.11));
        REPO.put("123455", new Product("123455", "Product5", 22.13));
    }

    @Override
    public Product get(String barCode) {
        Product product = REPO.get(barCode);
        if (product == null) {
            throw new PosException("Product not found");
        }
        return product;
    }

    @Override
    public void add(Product product) {
        REPO.put(product.getBarCode(), product);
    }
}
