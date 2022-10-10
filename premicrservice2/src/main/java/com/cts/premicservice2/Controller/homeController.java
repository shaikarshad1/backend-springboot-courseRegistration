package com.cts.premicservice2.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.premicservice2.Premicrservice2Application;
import com.cts.premicservice2.Model.Course;
import com.cts.premicservice2.Model.JwtResponse;
import com.cts.premicservice2.Repository.courseRepo;
import com.cts.premicservice2.feign.AuthFeign;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin("*")
public class homeController {
	private static final Logger log=LoggerFactory.getLogger(Premicrservice2Application.class);
	
	@Autowired
	private courseRepo repo;
	
	@Autowired
	private AuthFeign fen;
	
	public Boolean isValid(String token) throws Exception {
		JwtResponse reponse=fen.verifyToken(token);
		if(reponse.isValid()) {
			log.info("Token Verified {}",token);
			return true;
		}else {
			
			throw new Exception("Invalid");
		}
	}
	
	@ApiOperation(value = "Get All Coureses", notes = "Returns All Coureses")
	@ApiResponses(value={
	@ApiResponse(code = 200, message = "Successfully retrieved"),
	@ApiResponse(code=404,message="No Coureses Details Found")
	})
	@GetMapping("/courses")
	public ResponseEntity<List<Course>> allPlayer(@RequestHeader("Authorization") String token) throws Exception{
		if(!isValid(token)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
	}
	
	@ApiOperation(value = "Add Course Details", notes = "Required Parameters are- `Course ID`:`ID of the course which student want to register`, `Course Name`:`Name of the Course which student want to register`, `Duration`:`Duration of the Course`,"
			+ "`Fee`:`Cost of the course which student want to register`")
	@ApiResponses(value={
	@ApiResponse(code = 200, message = "Successfully Added details of Player"),
	@ApiResponse(code=404,message="Error in adding details to DataBase")
	})
	@PostMapping("/addCourse")
	public ResponseEntity<Course> addplayer(@RequestHeader("Authorization") String token,@RequestBody Course p) throws Exception {
		if(!isValid(token)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		repo.save(p);
		return ResponseEntity.status(HttpStatus.OK).body(p);
	}
	

}
