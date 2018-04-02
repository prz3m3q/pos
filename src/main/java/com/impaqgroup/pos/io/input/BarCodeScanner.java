package com.impaqgroup.pos.io.input;

public class BarCodeScanner implements Input {
    private String barCode;

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBarCode() {
        return barCode;
    }
}
