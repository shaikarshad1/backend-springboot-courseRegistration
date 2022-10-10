package com.cts.studentmicroservice.Controller;


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

import com.cts.studentmicroservice.StudentMicrosericeApplication;
import com.cts.studentmicroservice.Models.JwtResponse;
import com.cts.studentmicroservice.Models.Student;
import com.cts.studentmicroservice.Repository.studentRepo;
import com.cts.studentmicroservice.feign.AuthFeign;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin("*")
public class homeController {
	
	private static final Logger log=LoggerFactory.getLogger(StudentMicrosericeApplication.class);
	
	@Autowired
	private studentRepo repo;
	
	
	
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
	@ApiOperation(value = "Adding details of Student", notes = "Inserting Student Details into Database")
	@ApiResponses(value={
	@ApiResponse(code = 200, message = "Student Details Successfully added"),
	@ApiResponse(code=403,message="Forbidden")
	})
	@PostMapping("/addstudent")
	public ResponseEntity<Student> addStudent(@RequestBody Student stud, @RequestHeader("Authorization")String token) throws Exception {
		if(!isValid(token))
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(repo.save(stud));
	}
	@ApiOperation(value = "Get All Students Details", notes = "Returns All Student details in database")
	@ApiResponses(value={
	@ApiResponse(code = 200, message = "Successfully retrieved the details of students"),
	@ApiResponse(code=404,message="No Student Details Found")
	})
	@GetMapping("/students")
	public ResponseEntity<List<Student>> allStudents(@RequestHeader("Authorization")String token) throws Exception{
		if(!isValid(token))
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
	}
	@ApiOperation(value = "Get Student By it's Id", notes = "Returns Student Details with given Id")
	@ApiResponses(value={
	@ApiResponse(code = 200, message = "Successfully retrieved"),
	@ApiResponse(code=404,message="No Student Details Found")
	})
	@GetMapping("/student/{id}")
	public ResponseEntity<Student> sportByname(@PathVariable int id,@RequestHeader("Authorization") String token) throws Exception {
		
		if(!isValid(token))
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(repo.findById(id).orElse(null));
	}
	
	@ApiOperation(value = "Delete Event By Given Student ID", notes = "Deletes Student Details with given ID")
	@ApiResponses(value={
	@ApiResponse(code = 200, message = "Successfully Deleted"),
	@ApiResponse(code=404,message="Error in deleteing Student details")
	})
	@DeleteMapping("/student/{id}")
	public ResponseEntity<String> deleteEve(@PathVariable int id,@RequestHeader("Authorization") String token) throws Exception {
		if(!isValid(token))
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		Student eee=repo.findById(id).orElse(null);
		repo.delete(eee);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Student details with id "+eee+" deleted successfully");
		
	}

}
