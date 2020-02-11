package com.example.heronation;

import com.airbnb.lottie.animation.content.Content;

import java.util.List;

public class ShopItemInfo {
    private List<ItemContent> content = null;
    private Integer totalPages;
    private Integer totalElements;
    private Integer number;
    private Integer size;

    public List<ItemContent> getContent() {
        return content;
    }

    public void setContent(List<ItemContent> content) {
        this.content = content;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
