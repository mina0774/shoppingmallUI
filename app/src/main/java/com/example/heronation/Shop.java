package com.example.heronation;

public class Shop {
    String shop_num;
    String shop_name;
    String shop_tag;
    String shop_image1;
    String shop_image2;
    String shop_image3;

    public Shop(String shop_num, String shop_name, String shop_tag, String shop_image1, String shop_image2, String shop_image3) {
        this.shop_num = shop_num;
        this.shop_name = shop_name;
        this.shop_tag = shop_tag;
        this.shop_image1 = shop_image1;
        this.shop_image2 = shop_image2;
        this.shop_image3 = shop_image3;
    }

    public String getShop_num() {
        return shop_num;
    }

    public String getShop_name() {
        return shop_name;
    }

    public String getShop_tag() {
        return shop_tag;
    }

    public String getShop_image1() {
        return shop_image1;
    }

    public String getShop_image2() {
        return shop_image2;
    }

    public String getShop_image3() {
        return shop_image3;
    }
}
