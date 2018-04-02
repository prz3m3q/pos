package com.impaqgroup.pos;

import com.impaqgroup.pos.application.ProductSaleHandler;
import com.impaqgroup.pos.infrastructure.InMemoryProductRepository;
import com.impaqgroup.pos.infrastructure.PosException;
import com.impaqgroup.pos.io.input.Input;
import com.impaqgroup.pos.model.repository.ProductRepository;
import com.impaqgroup.pos.model.repository.PurchaseRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    private ProductRepository productRepository;
    private PurchaseRepository purchaseRepository;
    private Input input;

    @Before
    public void setUp() {
        productRepository = mock(ProductRepository.class);
        purchaseRepository = mock(PurchaseRepository.class);
        input = mock(Input.class);
    }

    @Test
    public void shouldThrowExceptionWhenNullBarCode() {
        expectedEx.expect(PosException.class);
        expectedEx.expectMessage("Invalid bar-code");
        when(input.getBarCode()).thenReturn(null);
        ProductSaleHandler productSaleHandler = new ProductSaleHandler(productRepository, purchaseRepository);
        productSaleHandler.handle(input);
    }

    @Test
    public void shouldThrowExceptionWhenEmptyBarCode() {
        expectedEx.expect(PosException.class);
        expectedEx.expectMessage("Invalid bar-code");
        when(input.getBarCode()).thenReturn("");
        ProductSaleHandler productSaleHandler = new ProductSaleHandler(productRepository, purchaseRepository);
        productSaleHandler.handle(input);
    }

    @Test
    public void shouldThrowExceptionWhenProductNotFound() {
        expectedEx.expect(PosException.class);
        expectedEx.expectMessage("Product not found");
        when(input.getBarCode()).thenReturn("123");
        when(productRepository.get("123")).thenThrow(new PosException("Product not found"));
        ProductSaleHandler productSaleHandler = new ProductSaleHandler(productRepository, purchaseRepository);
        productSaleHandler.handle(input);
    }

    @Test
    public void shouldThrowExceptionWhenProductNotFoundOtherVersion() {
        expectedEx.expect(PosException.class);
        expectedEx.expectMessage("Product not found");
        productRepository = new InMemoryProductRepository();
        when(input.getBarCode()).thenReturn("123");
        ProductSaleHandler productSaleHandler = new ProductSaleHandler(productRepository, purchaseRepository);
        productSaleHandler.handle(input);
    }
}
