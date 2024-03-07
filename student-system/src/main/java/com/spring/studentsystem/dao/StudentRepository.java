package com.spring.studentsystem.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.studentsystem.model.Student;
//@RepositoryRestResource
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


  //  public List<Student> getStudentByFullNameContaining(String fullName);

    public List<Student> findStudentByFullNameContaining(String fullName, Pageable pageable);

    @Query("select count (id) from student ")
    public Long  getStudentsLength();

    @Query("select count (id) from student where fullName LIKE %?1%")
    public Long  getStudentsLengthByName(String name);



}
