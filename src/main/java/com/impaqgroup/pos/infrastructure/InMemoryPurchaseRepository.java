package com.impaqgroup.pos.infrastructure;

import com.impaqgroup.pos.model.Product;
import com.impaqgroup.pos.model.repository.PurchaseRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryPurchaseRepository implements PurchaseRepository {

    private static final List<Product> REPO;

    static {
        REPO = new ArrayList<>();
    }

    @Override
    public void add(Product product) {
        REPO.add(product);
    }

    @Override
    public List<Product> getAll() {
        return REPO;
    }

    @Override
    public void clear() {
        REPO.clear();
    }

    @Override
    public Double getTotalSum() {
        Double totalSum = 0.0;
        for (Product product: REPO) {
            totalSum += product.getPrice();
        }
        return totalSum;
    }
}
