package com.example.demo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.StudentJdbcRepository;

@Controller
@RequestMapping("/")
public class JdbcController {
	@Autowired
	StudentJdbcRepository repository;
	
	@GetMapping
	public String test() {
		
		System.out.println(repository.findById(10001L));
		
		System.out.println(repository.findById2(10001L));
		return "Newfile";
	}

}