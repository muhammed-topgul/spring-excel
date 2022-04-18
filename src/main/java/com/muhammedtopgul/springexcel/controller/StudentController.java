package com.muhammedtopgul.springexcel.controller;

import com.muhammedtopgul.springexcel.entity.StudentEntity;
import com.muhammedtopgul.springexcel.repository.StudentRepository;
import com.muhammedtopgul.springexcel.helper.ExcelExporter;
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
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/export")
    public void exportToExcel(HttpServletResponse response, @RequestParam(required = false) String fileName) {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=%s.xlsx", (fileName != null) ? fileName : ("Unknown_" + new Date()));

        response.setHeader(headerKey, headerValue);
        List<StudentEntity> listOfStudent = studentRepository.findAll();
        ExcelExporter excelExporter = new ExcelExporter(listOfStudent);
        excelExporter.exportExcel(response);
    }
}
