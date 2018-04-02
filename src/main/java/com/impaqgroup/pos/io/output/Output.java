package com.impaqgroup.pos.io.output;

import com.impaqgroup.pos.model.Product;
import com.impaqgroup.pos.model.repository.PurchaseRepository;

public interface Output {
    void print(Product product);
    void print(PurchaseRepository purchaseRepository);
    void print(String message);
}
