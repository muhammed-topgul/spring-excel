package com.muhammedtopgul.springexcel;

import com.muhammedtopgul.springexcel.entity.StudentEntity;
import com.muhammedtopgul.springexcel.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringExcelApplication implements CommandLineRunner {
    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringExcelApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        StudentEntity studentEntity1 = new StudentEntity(1L, "Muhammed", "My Address Line 1, My Address Line 2", "Ankara", "10001");
        StudentEntity studentEntity2 = new StudentEntity(2L, "Jack", "His Address Line 1, His Address Line 2", "London", "20002");
        studentRepository.save(studentEntity1);
        studentRepository.save(studentEntity2);
    }
}
