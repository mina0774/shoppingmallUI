package com.example.heronation;

public class ClosetItem {
    String category;
    String item_name;
    String date;
    String shop_name;
    String measurement_type;

    public ClosetItem(String category, String item_name, String date, String shop_name, String measurement_type) {
        this.category = category;
        this.item_name = item_name;
        this.date = date;
        this.shop_name = shop_name;
        this.measurement_type = measurement_type;
    }

    public String getCategory() {
        return category;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getDate() {
        return date;
    }

    public String getShop_name() {
        return shop_name;
    }

    public String getMeasurement_type() {
        return measurement_type;
    }
}
