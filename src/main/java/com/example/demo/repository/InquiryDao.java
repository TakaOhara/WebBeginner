package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Inquiry;

@Repository
public interface InquiryDao {
	int insertInquiry(Inquiry inquiry);
	
	List<Inquiry> getAll();
}