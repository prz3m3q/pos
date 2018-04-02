package com.impaqgroup.pos.model.repository;

import com.impaqgroup.pos.model.Product;

public interface ProductRepository {
    Product get(String barCode);
    void add(Product product);
}
