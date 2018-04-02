package com.impaqgroup.pos.io.output;

import com.impaqgroup.pos.model.Product;
import com.impaqgroup.pos.model.repository.PurchaseRepository;

public class Printer extends MainOutput implements Output {

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

    @Override
    public String getMessage(PurchaseRepository purchaseRepository) {
        StringBuilder st = new StringBuilder();
        purchaseRepository.getAll().stream().forEach(product -> st.append(this.getMessage(product)));
        st.append(String.format("Total sum: %4.2f\n", purchaseRepository.getTotalSum()));
        return st.toString();
    }
}
