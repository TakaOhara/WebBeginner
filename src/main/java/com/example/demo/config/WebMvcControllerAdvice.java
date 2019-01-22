package com.example.demo.config;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import com.example.demo.service.EmptyListException;


/**
 * 全てのControllerで共通処理を定義
 */
@ControllerAdvice
public class WebMvcControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        // Stringの空文字をNULLに
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
    
    //アプリケーション全体で共通的に行う例外処理
	@ExceptionHandler(BadSqlGrammarException.class)
	public String handleException(BadSqlGrammarException e) {
		return "err/SQL";
	}
	
	@ExceptionHandler(EmptyListException.class)
	public String handleException(EmptyListException e) {
		return "err/EmptyList";
	}
   
}