package com.example.demo.app.inquiry;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquiryForm implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int id;

    @NotNull(message = "タイトルを入力してください")
    @Size(min = 1, max = 20, message="タイトルは20文字以内で入力してください")
    private String name;
    
    @NotNull(message = "メールアドレスを入力してください")
    @Email(message = "メールアドレスの形式が間違っています")
    private String email;

    @NotNull(message = "お問い合わせ内容を入力してください")
    private String contents;


}