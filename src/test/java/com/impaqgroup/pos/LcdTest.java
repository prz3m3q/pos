package com.impaqgroup.pos;

import com.impaqgroup.pos.application.ProductSaleHandler;
import com.impaqgroup.pos.infrastructure.PosException;
import com.impaqgroup.pos.io.input.Input;
import com.impaqgroup.pos.io.output.LcdDisplay;
import com.impaqgroup.pos.model.Product;
import com.impaqgroup.pos.model.repository.ProductRepository;
import com.impaqgroup.pos.model.repository.PurchaseRepository;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LcdTest {

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
    public void shouldGetProductNotFoundMessage() {
        when(input.getBarCode()).thenReturn("123");
        when(productRepository.get("123")).thenThrow(new PosException("Product not found"));
        ProductSaleHandler productHandler = new ProductSaleHandler(productRepository, purchaseRepository);
        LcdDisplay lcdDisplay = new LcdDisplay();
        try {
            productHandler.handle(input);
        } catch (PosException ex) {
            assertEquals("Product not found\n", lcdDisplay.getMessage(ex.getMessage()));
        }
    }

    @Test
    public void shouldGetInvalidBarCodeMessage() {
        when(input.getBarCode()).thenReturn("123");
        when(productRepository.get("123")).thenReturn(null);
        ProductSaleHandler productHandler = new ProductSaleHandler(productRepository, purchaseRepository);
        LcdDisplay lcdDisplay = new LcdDisplay();
        try {
            productHandler.handle(input);
        } catch (PosException ex) {
            assertEquals("Invalid bar-code\n", lcdDisplay.getMessage(ex.getMessage()));
        }
    }

    @Test
    public void shouldGetProductLineMessage() {
        when(productRepository.get("123")).thenReturn(new Product("123", "Prod1", 22.00));
        ProductSaleHandler productHandler = new ProductSaleHandler(productRepository, purchaseRepository);
        LcdDisplay lcdDisplay = new LcdDisplay();
        when(input.getBarCode()).thenReturn("123");
        Product product = productHandler.handle(input);
        assertEquals("Prod1 - 22,00\n", lcdDisplay.getMessage(product));
    }

    @Test
    public void shouldGetProductSumMessage() {
        when(purchaseRepository.getTotalSum()).thenReturn(new Double(22.00));
        LcdDisplay lcdDisplay = new LcdDisplay();
        assertEquals("Total sum: 22,00\n", lcdDisplay.getMessage(purchaseRepository));
    }
}
