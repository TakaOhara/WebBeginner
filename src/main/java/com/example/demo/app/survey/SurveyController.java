package com.example.demo.app.survey;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Survey;
import com.example.demo.service.SurveyServiceImpl;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	
	@Autowired
	SurveyServiceImpl surveyService;
	
	@GetMapping
	public String index(Model model) {
		
		List<Survey> list = surveyService.getAll();
		double average = surveyService.getSatisfactionAvg();
		
		model.addAttribute("surveyList", list);
		model.addAttribute("average", average);
		model.addAttribute("title", "Servey Index");
		
		return "survey/index";
	}
	
	@GetMapping("/form")
	public String form(SurveyForm surveyForm, Model model) {
		model.addAttribute("title", "Servey Form");
		return "survey/form";
	}
	
	
	@PostMapping("/confirm")
	public String confirm(
			@Valid @ModelAttribute SurveyForm surveyForm,
	        BindingResult result,
	        Model model) {
		model.addAttribute("title", "Confirm Page");
		model.addAttribute("surveyForm", surveyForm);
		if(result.hasErrors()) {
			return "survey/form";
		}
		return "survey/confirm";
	}
	
	@PostMapping("/complete")
	public String complete(
			@Valid @ModelAttribute SurveyForm surveyForm,
	        BindingResult result,
	        Model model) {
		
		if(result.hasErrors()) {
			return "survey/form";
		}
		
		Survey survey = new Survey();
		survey.setAge(surveyForm.getAge());
		survey.setSatisfaction(surveyForm.getSatisfaction());
		survey.setComment(surveyForm.getComment());
		survey.setCreated(LocalDateTime.now());
		
		surveyService.save(survey);
		return "redirect:/survey/form?complete";
	}
	
}