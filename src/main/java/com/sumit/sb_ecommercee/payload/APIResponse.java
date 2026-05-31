package com.sumit.sb_ecommercee.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
//@NoArgsConstructor
public class APIResponse {
    public String message;
    public String status;

    public APIResponse(String message, boolean b) {
        this.message = message;
        this.status = status;
    }

    public APIResponse(String message) {
        this.message = message;
    }

    public  APIResponse(){

    }
}
