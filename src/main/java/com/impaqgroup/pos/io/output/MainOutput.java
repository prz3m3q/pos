package com.impaqgroup.pos.io.output;

import com.impaqgroup.pos.model.Product;
import com.impaqgroup.pos.model.repository.PurchaseRepository;

public class MainOutput {
    public String getMessage(Product product) {
        return String.format("%s - %4.2f\n", product.getName(), product.getPrice());
    }

    public String getMessage(PurchaseRepository purchaseRepository) {
        return String.format("Total sum: %4.2f\n", purchaseRepository.getTotalSum());
    }

    public String getMessage(String message) {
        return message + "\n";
    }
}
