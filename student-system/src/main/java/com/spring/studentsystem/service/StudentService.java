package com.spring.studentsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.studentsystem.dao.StudentRepository;
import com.spring.studentsystem.model.Student;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return   studentRepository.findAll(pageable).getContent();}
    public Student getStudentById(Long id) {return   studentRepository.findById(id).get();}

    public Student saveStudent(Student student) {return studentRepository.save(student);}
    public Student editStudent(Student student) {return studentRepository.save(student);}
    public void deleteStudent(Long id) {studentRepository.deleteById(id);}
    public List<Student> findStudentByFullNameContaining(String fullName, int page,int size) {
        Pageable pageable= PageRequest.of(page,size);
        return studentRepository.findStudentByFullNameContaining(fullName, pageable);
    }

    public Long  getStudentsLength(){
        return studentRepository.getStudentsLength();
    }

    public Long getStudentsLengthByName(String name){
      return   studentRepository.getStudentsLengthByName(name);
    }


}
