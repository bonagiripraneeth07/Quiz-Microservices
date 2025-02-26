package com.quizapp.quiz_service.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {
private Integer id;

    public Integer getId() {
        return id;
    }

    public Response(Integer id, String response) {
        this.id = id;
        this.response = response;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    private String response;
}
