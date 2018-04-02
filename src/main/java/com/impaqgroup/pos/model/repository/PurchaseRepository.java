package com.impaqgroup.pos.model.repository;

import com.impaqgroup.pos.model.Product;

import java.util.List;

public interface PurchaseRepository {
    void add(Product product);
    List<Product> getAll();
    void clear();
    Double getTotalSum();
}
