package com.example.demo.app.survey;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyForm implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int id;

    @NotNull(message = "年齢を入力してください")
    @Min(0)
    @Max(150)
    private int age;
    
    @Min(1)
    @Max(5)
    private int satisfaction;

    private String comment;


}