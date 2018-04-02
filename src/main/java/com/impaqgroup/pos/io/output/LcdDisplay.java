package com.impaqgroup.pos.io.output;

import com.impaqgroup.pos.model.Product;
import com.impaqgroup.pos.model.repository.PurchaseRepository;

public class LcdDisplay extends MainOutput implements Output {

    @Override
    public void print(Product product) {
        System.out.print(getMessage(product));
    }

    @Override
    public void print(PurchaseRepository purchaseRepository) {
        System.out.print(getMessage(purchaseRepository));
    }

    @Override
    public void print(String message) {
        System.out.print(getMessage(message));
    }


}
