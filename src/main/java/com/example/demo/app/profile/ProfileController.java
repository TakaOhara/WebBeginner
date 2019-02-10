package com.example.demo.app.profile;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Inquiry;
import com.example.demo.service.InquiryNotFoundException;
import com.example.demo.service.InquiryServiceImpl;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	
	private final InquiryServiceImpl inquiryService;
	
	@Autowired
	public ProfileController(InquiryServiceImpl inquiryService){
		this.inquiryService = inquiryService;
	}
	
	@GetMapping
	public String display(Model model) {
		//profile_idに紐づけてプロフィール（画像含む）を表示
		return null;
	}
	
	@GetMapping("/form")
	public String form(profileForm inquiryForm, Model model) {//InquiryFormはhtmlのエラー出力部分と関わっている
		//フォームを表示
		return null;
	}
	
	
	@PostMapping("/complete")
	public String complete(
			@Validated @ModelAttribute profileForm inquiryForm,
	        BindingResult result,
	        Model model,
	        RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			return "profile/form";
		}
		
		//profileを登録
		
		redirectAttributes.addFlashAttribute("complete", "Completed!");
		return "redirect:/profile";
	}
	

}