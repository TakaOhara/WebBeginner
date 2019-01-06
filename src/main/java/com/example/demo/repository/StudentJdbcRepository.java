package com.example.demo.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Student;

@Repository
public class StudentJdbcRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public Student findById(long id) {
		String sql = "SELECT id, name, passport_number FROM Student WHERE id = ?";
		Map<String, Object> result =  jdbcTemplate.queryForMap(sql,  new Object[] { id });
		Student student = new Student();
		student.setId(Long.parseLong(String.valueOf(result.get("id"))));
		student.setName((String) result.get("name"));
		student.setPassportNumber((String)result.get("passport_number"));
		return student;
	}
	
	public Student findById2(long id) {
		return jdbcTemplate.queryForObject("select * from student where id=?", new Object[] { id },
				new BeanPropertyRowMapper<Student>(Student.class));
	}
	
	public int deleteById(long id) {
		return jdbcTemplate.update("delete from student where id=?", new Object[] { id });
	}

	public int insert(Student student) {
		return jdbcTemplate.update("insert into student (id, name, passport_number) " + "values(?,  ?, ?)",
				new Object[] { student.getId(), student.getName(), student.getPassportNumber() });
	}

	public int update(Student student) {
		return jdbcTemplate.update("update student " + " set name = ?, passport_number = ? " + " where id = ?",
				new Object[] { student.getName(), student.getPassportNumber(), student.getId() });
	}
}