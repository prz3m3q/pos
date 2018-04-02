package com.impaqgroup.pos.application;

import com.impaqgroup.pos.infrastructure.PosException;
import com.impaqgroup.pos.io.output.Output;
import com.impaqgroup.pos.model.Product;
import com.impaqgroup.pos.model.repository.PurchaseRepository;
import com.impaqgroup.pos.io.input.Input;
import com.impaqgroup.pos.model.repository.ProductRepository;

public class ProductSaleHandler {

    private ProductRepository productRepository;
    private PurchaseRepository purchaseRepository;

    public ProductSaleHandler(ProductRepository productRepository, PurchaseRepository purchaseRepository) {
        this.productRepository = productRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public Product handle(Input input) {
        validate(input);
        Product product = productRepository.get(input.getBarCode());
        purchaseRepository.add(product);
        return product;
    }

    private void validate(Input input) {
        if (input.getBarCode() == null || input.getBarCode().trim().equals("")) {
            throw new PosException("Invalid bar-code");
        }
    }
}
