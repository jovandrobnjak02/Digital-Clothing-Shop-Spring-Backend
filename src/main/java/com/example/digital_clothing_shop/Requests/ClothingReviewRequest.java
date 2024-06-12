package com.example.digital_clothing_shop.Requests;

public class ClothingReviewRequest {
    private String comment;
    private int grade;
    private int clothingId;

    public ClothingReviewRequest() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getClothingId() {
        return clothingId;
    }

    public void setClothingId(int clothingId) {
        this.clothingId = clothingId;
    }
}
