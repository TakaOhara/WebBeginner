package com.example.demo.app.inquiry;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Inquiry;
import com.example.demo.service.InquiryServiceImpl;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {
	
	@Autowired
	InquiryServiceImpl inquiryService;
	
	@GetMapping
	public String index(Model model) {
		List<Inquiry> list = null;
//		try {
//			list = inquiryService.getAll();
//		} catch(RuntimeException e) {
//			model.addAttribute("title", "エラー");
//			model.addAttribute("err", e.getMessage());
//			return "err";
//		}
		
		list = inquiryService.getAll();
		
		model.addAttribute("inquiryList", list);
		model.addAttribute("title", "お問い合わせ一覧");
		
		return "inquiry/index";
	}
	
	@GetMapping("/form")
	public String form(InquiryForm inquiryForm, Model model) {
		model.addAttribute("title", "お問い合わせフォーム");
		return "inquiry/form";
	}
	
	
	@PostMapping("/test")
	public String test(
			@RequestParam String name,
			@RequestParam String email,
			@RequestParam String contents,
			Model model) {
		model.addAttribute("title", "テスト");
		model.addAttribute("name", name);
		model.addAttribute("email", email);
		model.addAttribute("contents", contents);
		return "inquiry/test";
	}
	
	@PostMapping("/confirm")
	public String confirm(
			@Valid @ModelAttribute InquiryForm inquiryForm,
	        BindingResult result,
	        Model model) {
		model.addAttribute("title", "確認ページ");
		model.addAttribute("inquiryForm", inquiryForm);
		if(result.hasErrors()) {
			return "inquiry/form";
		}
		return "inquiry/confirm";
	}
	
	@PostMapping("/complete")
	public String complete(
			@Valid @ModelAttribute InquiryForm inquiryForm,
	        BindingResult result,
	        Model model) {
		
		if(result.hasErrors()) {
			return "inquiry/form";
		}
		
		inquiryService.save(inquiryForm);
		return "redirect:/inquiry/form?complete";
	}
	
	//コントローラごとに例外を処理する場合
//	@ExceptionHandler(EmptyListException.class)
//	public String handleException(EmptyListException e) {
//		return "/err";
//	}

}