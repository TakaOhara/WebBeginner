package com.example.demo.app.inquiry;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
//			model.addAttribute("title", "error");
//			model.addAttribute("err", e.getMessage());
//			return "err";
//		}
		
		list = inquiryService.getAll();
		
		model.addAttribute("inquiryList", list);
		model.addAttribute("title", "Inquiry Index");
		
		//return "inquiry/index";
		return "inquiry/index_boot";
	}
	
	@GetMapping("/form")
	public String form(InquiryForm inquiryForm, Model model, @ModelAttribute("complete") String complete) {
		model.addAttribute("title", "Inquiry Form");
		//return "inquiry/form";
		return "inquiry/form_boot";
	}
	
	@PostMapping("/form")
	public String form(InquiryForm inquiryForm, Model model) {
		model.addAttribute("title", "InquiryForm");
		//return "inquiry/form";
		return "inquiry/form_boot";
	}
	
	@PostMapping("/test")
	public String test(
			@RequestParam String name,
			@RequestParam String email,
			@RequestParam String contents,
			Model model) {
		model.addAttribute("title", "test");
		model.addAttribute("name", name);
		model.addAttribute("email", email);
		model.addAttribute("contents", contents);
		return "inquiry/test";
	}
	
	@PostMapping("/confirm")
	public String confirm(
			@Validated @ModelAttribute InquiryForm inquiryForm,
	        BindingResult result,
	        Model model) {
		model.addAttribute("inquiryForm", inquiryForm);
		if(result.hasErrors()) {
			model.addAttribute("title", "Inquiry");
			//return "inquiry/form";
			return "inquiry/form_boot";
		}
		model.addAttribute("title", "確認ページ");
		//return "inquiry/confirm";
		return "inquiry/confirm_boot";
	}
	
	@PostMapping("/complete")
	public String complete(
			@Validated @ModelAttribute InquiryForm inquiryForm,
	        BindingResult result,
	        Model model,
	        RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			return "inquiry/form";
		}
		
		//要変更　ここでEntityへの詰め替え必要
		Inquiry inquiry = new Inquiry();
		inquiry.setName(inquiryForm.getName());
		inquiry.setEmail(inquiryForm.getEmail());
		inquiry.setContents(inquiryForm.getContents());
		inquiry.setCreated(LocalDateTime.now());
		
		inquiryService.save(inquiry);
		redirectAttributes.addFlashAttribute("complete", "Registerd!");
		return "redirect:/inquiry/form?complete";
	}
	
//コントローラごとに例外を処理する場合
//	@ExceptionHandler(EmptyListException.class)
//	public String handleException(EmptyListException e) {
//		return "/err";
//	}

}