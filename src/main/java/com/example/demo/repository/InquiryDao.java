package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Inquiry;

public interface InquiryDao {
	
	int insertInquiry(Inquiry inquiry);
	
	int updateInquiry(Inquiry inquiry);
	
	List<Inquiry> getAll();
}