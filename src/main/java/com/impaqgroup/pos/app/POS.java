package com.impaqgroup.pos.app;

import com.impaqgroup.pos.application.ProductSaleHandler;
import com.impaqgroup.pos.infrastructure.InMemoryProductRepository;
import com.impaqgroup.pos.infrastructure.InMemoryPurchaseRepository;
import com.impaqgroup.pos.infrastructure.PosException;
import com.impaqgroup.pos.io.input.BarCodeScanner;
import com.impaqgroup.pos.io.input.Input;
import com.impaqgroup.pos.io.output.LcdDisplay;
import com.impaqgroup.pos.io.output.Output;
import com.impaqgroup.pos.io.output.Printer;
import com.impaqgroup.pos.model.Product;
import com.impaqgroup.pos.model.repository.ProductRepository;
import com.impaqgroup.pos.model.repository.PurchaseRepository;

import java.util.Scanner;

public class POS {
    public static void main(String[] args) {
        ProductRepository productRepository = new InMemoryProductRepository();
        PurchaseRepository purchaseRepository = new InMemoryPurchaseRepository();
        Output lcdDisplay = new LcdDisplay();
        Input barCodeScanner = new BarCodeScanner();
        ProductSaleHandler productHandler = new ProductSaleHandler(productRepository, purchaseRepository);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String barCode = scanner.nextLine();
            if (barCode.equals("exit")) {
                break;
            }
            barCodeScanner.setBarCode(barCode);
            try {
                Product product = productHandler.handle(barCodeScanner);
                lcdDisplay.print(product);
            } catch (PosException ex) {
                lcdDisplay.print(ex.getMessage());
            }
        }
        lcdDisplay.print(purchaseRepository);
        Output printer = new Printer();
        printer.print(purchaseRepository);
        purchaseRepository.clear();
    }
}
