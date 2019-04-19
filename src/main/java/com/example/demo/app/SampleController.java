package com.example.demo.app;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 画像を表示する方法
 * 
 */
@Controller
@RequestMapping("/")
public class SampleController {
	
	private final JdbcTemplate jdbcTemplate;

	private final ResourceLoader resourceLoader;

	@Autowired
	public SampleController(JdbcTemplate jdbcTemplate,
							ResourceLoader resourceLoader) {
		this.jdbcTemplate = jdbcTemplate;
		this.resourceLoader = resourceLoader;
	}
	
	@GetMapping
	public String test(Model model) throws IOException {
		
		//System.out.println(repository.findById(10001L));
		
		//System.out.println(repository.findById2(10001L));
		
		String sql = "SELECT id, name, email, contents, created FROM inquiry WHERE id = 1";

		// 1. resourcesにあるdog.jpgを取得
		Resource res = resourceLoader.getResource("classpath:animal/dog.jpg");

		// アプリケーション外のファイルを参照する場合は下記のようにします。
		//Resource res = new FileSystemResource("c:/temp/dog.jpg");

		// 2. Fileからbyte配列へ変換する
		byte[] image = FileCopyUtils.copyToByteArray(res.getFile());

		// DBから取得する場合はBLOBをbyte配列で取得できますので、2へ合流できます

		// byte配列をBASE64文字列に変換
		String encodedImage = Base64.getEncoder().encodeToString(image);

		// BASE64文字列を返却
		model.addAttribute("image", encodedImage);

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
