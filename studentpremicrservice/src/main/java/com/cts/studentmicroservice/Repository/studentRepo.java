package com.cts.studentmicroservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.studentmicroservice.Models.Student;

public interface studentRepo extends JpaRepository<Student,Integer> {

}
