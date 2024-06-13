package com.example.digital_clothing_shop.Requests;

import java.util.List;

public class CreateOrderRequest {

    private List<Integer> clothingIds;

    public CreateOrderRequest(List<Integer> clothingIds) {
        this.clothingIds = clothingIds;
    }

    public List<Integer> getClothingIds() {
        return clothingIds;
    }

    public void setClothingIds(List<Integer> clothingIds) {
        this.clothingIds = clothingIds;
    }
}
