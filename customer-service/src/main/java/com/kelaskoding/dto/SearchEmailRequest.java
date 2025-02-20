package com.kelaskoding.dto;

public class SearchEmailRequest {

    private String email;

    public SearchEmailRequest() {
    }

    public SearchEmailRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
