package com.example.demo.app.inquiry;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InquiryForm{

    @Size(min = 1, max = 20, message="Please input 20 characters or less")
    private String name;
    
    @NotNull
    @Email(message = "Invalid E-mail Format")//ドメイン部の「+」を通してしまうのでDeprecated
    private String email;

    @NotNull
    private String contents;


}