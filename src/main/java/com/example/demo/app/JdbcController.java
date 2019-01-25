package com.example.demo.app;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Inquiry;

@Controller
@RequestMapping("/")
public class JdbcController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping
	public String test(Model model) {
		
		//System.out.println(repository.findById(10001L));
		
		//System.out.println(repository.findById2(10001L));
		
		String sql = "SELECT id, name, email, contents, created FROM inquiry WHERE id = 1";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		model.addAttribute("name", map.get("name"));
		model.addAttribute("email", map.get("email"));
		
//		for(Map<String, Object> result : resultList) {
//			Inquiry inquiry = new Inquiry();
//			inquiry.setId((int)result.get("id"));
//			inquiry.setName((String)result.get("name"));
//			inquiry.setEmail((String)result.get("email"));
//			inquiry.setContents((String)result.get("contents"));
//			inquiry.setCreated(((Timestamp) result.get("created")).toLocalDateTime());
//		}
		return "Newfile";
	}

}