package com.example.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@EnableWebMvc この表記はspring boot2以降では不要 cssなどが読みこめなくなる
@Configuration
//@ComponentScan
public class WebMvcConfig implements WebMvcConfigurer{

}