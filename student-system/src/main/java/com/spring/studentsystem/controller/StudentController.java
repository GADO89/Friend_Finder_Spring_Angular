package com.spring.studentsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.studentsystem.model.Student;
import com.spring.studentsystem.service.StudentService;

//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/system")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getStudent(@RequestParam int page,@RequestParam int size){
       return  studentService.getStudents(page, size);
    }
    @GetMapping("/student")
    public Student getStudentById(@RequestParam Long id){
        return  studentService.getStudentById(id);
    }

    @PostMapping("/students")
    public Student saveStudent(@RequestBody Student student){
        return  studentService.saveStudent(student);
    }

    @PutMapping("/students")
    public Student editStudent(@RequestBody Student student, @RequestParam Long id){
        student.setId(id);
        return  studentService.editStudent(student);
    }

    @DeleteMapping("/students")
    public void deleteStudent(@RequestParam Long id){
          studentService.deleteStudent(id);
    }

    @GetMapping("/students/searchname")
    public List<Student> findStudentByFullNameContaining(@RequestParam String fullName,@RequestParam int page,@RequestParam int size){
        return studentService.findStudentByFullNameContaining(fullName,page,size);
    }

    @GetMapping("/students/length")
    public Long  getStudentsLength(){
        return studentService.getStudentsLength();

    }
    @GetMapping("/students/lengthname")
    public Long getStudentsLengthByName(@RequestParam String name){
        return   studentService.getStudentsLengthByName(name);
    }




}
