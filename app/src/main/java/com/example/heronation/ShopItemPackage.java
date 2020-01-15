package com.example.heronation;

import java.util.ArrayList;

public class ShopItemPackage {
/* all best, 하의 best ... 등의 아이템 묶음의 대표 제목과, 그에 속하는 각각의 상품들을 나타내는 클래스 */
    private String packageName;
    private ArrayList<ShopItem> shopItems;

    public ShopItemPackage(String packageName, ArrayList<ShopItem> shopItems) {
        this.packageName = packageName;
        this.shopItems = shopItems;
    }

    public String getPackageName() {
        return packageName;
    }

    public ArrayList<ShopItem> getShopItems() {
        return shopItems;
    }
}
