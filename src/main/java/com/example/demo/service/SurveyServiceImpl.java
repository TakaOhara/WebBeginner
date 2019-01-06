package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.survey.SurveyForm;
import com.example.demo.entity.Survey;
import com.example.demo.repository.SurveyDaoImpl;

@Service
public class SurveyServiceImpl implements SurveyService{

	@Autowired
	SurveyDaoImpl dao;
	
	@Override
	public int save(SurveyForm surveyForm) {
		Survey survey = new Survey();
		survey.setAge(surveyForm.getAge());
		survey.setSatisfaction(surveyForm.getSatisfaction());
		survey.setComment(surveyForm.getComment());
		survey.setCreated(LocalDateTime.now());
		return dao.insertSurvey(survey);
	}

	@Override
	public List<Survey> getAll() {
		if(dao.getAll().isEmpty()) {
			throw new EmptyListException("SQL error");
		}
		return dao.getAll();
	}

	@Override
	public double getSatisfactionAvg() {
		double count = dao.getCount();
		double sum = dao.getSum();
		return sum / count;
	}
}