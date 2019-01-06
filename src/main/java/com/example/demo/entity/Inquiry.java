package com.example.demo.entity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Inquiry {
	private int id;
	private String name;
	private String email;
	private String contents;
	private LocalDateTime created;
}