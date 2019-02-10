package com.example.demo.app.profile;



import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class profileForm{

	@NotNull
    @Size(min = 1, max = 20, message="Please input 20 characters or less")
    private String name;

}