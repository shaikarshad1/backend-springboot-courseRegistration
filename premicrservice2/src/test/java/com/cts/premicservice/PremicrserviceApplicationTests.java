package com.cts.premicservice;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.premicservice2.Premicrservice2Application;
import com.cts.premicservice2.Controller.homeController;
import com.cts.premicservice2.Model.Course;
import com.cts.premicservice2.Model.JwtResponse;
import com.cts.premicservice2.Repository.courseRepo;
import com.cts.premicservice2.exceptionHandling.ErrorResponsePojo;


class PremicrserviceApplicationTests {

	@Mock
	homeController plc;
	
	@Autowired
	courseRepo repo;

	/*@Test
	public void testParticipationStatus() {
		List<Course> lpar=new ArrayList<>();
		lpar.add(new Course(10,"Full Stack Java","6 Months" ,"12000"));
		
		lpar.add(new Course(11,"Full Stack DotNet","6 Months" ,"10000"));
		
		Mockito.when(repo.findById(10)).thenReturn(lpar);
		List<Course> lpp= repo.findById(10);
		assertEquals(lpar,lpp);
		}*/
	
	
	
	@Test
	public void test1() {
		Course c=new Course(10,"Full Stack Java","6 Months" ,"12000");
		assertEquals(10,c.getCourseId());
		assertEquals("Full Stack Java",c.getCourseName());
		assertEquals("6 Months",c.getDuration());
		assertEquals("12000",c.getFee());
	}
	@Test
	public void test2() {
		Course c=new Course();
		c.setCourseId(11);
		c.setCourseName("Full Stack DotNet");
		c.setDuration("6 Months");
		c.setFee("10000");
		assertEquals(11,c.getCourseId());
		assertEquals("Full Stack DotNet",c.getCourseName());
		assertEquals("6 Months",c.getDuration());
		assertEquals("10000",c.getFee());
		
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
		Premicrservice2Application.main(new String[] {});
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
	
	/*@Test
	public void test7() {
		Course c=new Course(10,"Full Stack Java","6 Months" ,"12000");
		Mockito.when(plc.allPlayer("token")).thenReturn(ResponseEntity.status(HttpStatus.OK).body(c));
		
	}*/

}
