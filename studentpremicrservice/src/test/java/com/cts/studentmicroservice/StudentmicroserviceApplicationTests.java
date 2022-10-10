package com.cts.studentmicroservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.studentmicroservice.Controller.homeController;
import com.cts.studentmicroservice.Models.JwtResponse;
import com.cts.studentmicroservice.Models.Student;
import com.cts.studentmicroservice.Repository.studentRepo;
import com.cts.studentmicroservice.exceptionHandling.ErrorResponsePojo;
@SpringBootTest
public class StudentmicroserviceApplicationTests {
	@Autowired
	private  homeController hmc;

	@Autowired
	private  studentRepo repo;
	
	
		
	
	@Test
	public void test1() {
		Student ss=new Student(1,"arshad",22,"arshad@gmail.com","b.tech",123456789,"java");
		assertEquals(1,ss.getStudentId());
		assertEquals("arshad",ss.getStudentName());
		assertEquals(22,ss.getAge());
		assertEquals("arshad@gmail.com",ss.getStudentEmail());
	}
	@Test
	public void test2() {
		Student ss=new Student();
		ss.setStudentId(2);
		ss.setStudentName("suresh");
		ss.setAge(22);
		ss.setStudentEmail("suresh@gmail.com");
		
		ss.setQualification("b.tech");
		ss.setPhoneNo(123456789);
		ss.setCourse("core java");
		assertEquals("b.tech",ss.getQualification());
		assertEquals("core java",ss.getCourse());
		assertEquals(123456789,ss.getPhoneNo());
		
		
	}
	
	@Test
	public void test3() {
		JwtResponse j=new JwtResponse("abcuser","abc",true);
		assertEquals("abcuser",j.getUserid());
		assertEquals("abc",j.getUsername());
		assertTrue(j.isValid());
		
	}
	
	@Test
	public void test4() {
		JwtResponse j=new JwtResponse();
		j.setUserid("def");
		j.setUsername("defuser");
		j.setValid(true);
		assertEquals("def",j.getUserid());
		assertEquals("defuser",j.getUsername());
		assertTrue(j.isValid());
	}
	@Test
	public void test() {
		StudentMicrosericeApplication.main(new String[] {});
	}
	@Test
	public void test5() {
		ErrorResponsePojo j=new ErrorResponsePojo();
		j.setMessage("warningmsg");
		j.setTitle("warningtitle");
		assertEquals("warningmsg",j.getMessage());
		assertEquals("warningtitle",j.getTitle());
	}
	
	@Test
	public void test6() {
		ErrorResponsePojo j=new ErrorResponsePojo("abc","def");
		
		assertEquals("abc",j.getMessage());
		assertEquals("def",j.getTitle());
	}
	
		
		@Test
		public void test9() {
			JwtResponse j=new JwtResponse("root","root",true);
			
			j.setUserid("admin");
			assertEquals("admin",j.getUserid());
		}
		
		@Test
		public void test10() {
			JwtResponse j=new JwtResponse("root","root",true);
			
			j.setUsername("admin");
			assertEquals("admin",j.getUsername());
		}
		@Test
		public void test11() {
			JwtResponse j=new JwtResponse();
			
			j.setValid(false);
			assertEquals(false,j.isValid());
		}
		
		
		
}
