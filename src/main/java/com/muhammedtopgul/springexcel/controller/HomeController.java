package com.muhammedtopgul.springexcel.controller;

import com.muhammedtopgul.springexcel.entity.InstructorEntity;
import com.muhammedtopgul.springexcel.entity.StudentEntity;
import com.muhammedtopgul.springexcel.repository.InstructorRepository;
import com.muhammedtopgul.springexcel.repository.StudentRepository;
import com.muhammedtopgul.springexcel.excel.ExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @author muhammed-topgul
 * @since 18.04.2022 11:48
 */

@RestController
@RequestMapping("/api/excel")
public class HomeController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private ExcelExporter<StudentEntity> studentExporter;

    @Autowired
    private ExcelExporter<InstructorEntity> instructorExporter;

    @GetMapping("/export-student")
    public void exportStudentToExcel(HttpServletResponse response, @RequestParam(required = false) String fileName) {
        List<StudentEntity> listOfStudent = studentRepository.findAll();
        studentExporter.setup(listOfStudent, "StudentList","Student Information").produce(response, fileName);
    }

    @GetMapping("/export-instructor")
    public void exportInstructorToExcel(HttpServletResponse response, @RequestParam(required = false) String fileName) {
        List<InstructorEntity> listOfInstructor = instructorRepository.findAll();
        instructorExporter.setup(listOfInstructor, "StudentList","Student Information").produce(response, fileName);
    }
}
