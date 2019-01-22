package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.inquiry.InquiryForm;
import com.example.demo.entity.Inquiry;
import com.example.demo.repository.InquiryDao;

@Service
public class InquiryServiceImpl implements InquiryService{

	@Autowired
	InquiryDao dao;
	
	@Override
	public int save(InquiryForm inquiryForm) {
		Inquiry inquiry = new Inquiry();
		inquiry.setName(inquiryForm.getName());
		inquiry.setEmail(inquiryForm.getEmail());
		inquiry.setContents(inquiryForm.getContents());
		inquiry.setCreated(LocalDateTime.now());
		return dao.insertInquiry(inquiry);
	}

	@Override
	public List<Inquiry> getAll() {
		if(dao.getAll().isEmpty()) {
			throw new EmptyListException("SQL error");
		}
		return dao.getAll();
	}
}