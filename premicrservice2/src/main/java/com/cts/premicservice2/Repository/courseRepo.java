package com.cts.premicservice2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.premicservice2.Model.Course;


public interface courseRepo extends JpaRepository<Course,Integer>{

}
