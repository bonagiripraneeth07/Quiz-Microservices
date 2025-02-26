package com.quizapp.quiz_service.model;

import lombok.Data;

@Data
public class QuizDto {
    public String getCaegoryName() {
        return categoryName;
    }

    public void setCategoryName(String caegoryName) {
        this.categoryName = caegoryName;
    }

    public Integer getNumQuestions() {
        return numQuestions;
    }

    public void setNumQuestions(Integer numQuestions) {
        this.numQuestions = numQuestions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String tile) {
        this.title = tile;
    }

    String categoryName;
    Integer numQuestions;
    String title;
}
