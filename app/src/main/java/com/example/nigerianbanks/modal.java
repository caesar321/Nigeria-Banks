package com.example.nigerianbanks;

public class modal {
    private String bankName,UssdCode,ImageView;

    public modal(String bankName, String ussdCode, String imageView) {
        this.bankName = bankName;
        UssdCode = ussdCode;
        ImageView = imageView;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getUssdCode() {
        return UssdCode;
    }

    public void setUssdCode(String ussdCode) {
        UssdCode = ussdCode;
    }

    public String getImageView() {
        return ImageView;
    }

    public void setImageView(String imageView) {
        ImageView = imageView;
    }
}
