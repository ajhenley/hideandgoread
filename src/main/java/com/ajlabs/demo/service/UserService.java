package com.ajlabs.demo.service;

import com.ajlabs.demo.model.Student;
import com.ajlabs.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    public UserService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }

    public Student findByEmail(String email)
    {
        return studentRepository.findByEmail(email);
    }

    public Long countByEmail(String email){
        return studentRepository.countByEmail(email);
    }

    public Student findByUsername(String username){
        return studentRepository.findByUsername(username);
    }

    public void saveTeacher(Student student)
    {
        student.setUsertype("teacher");
        studentRepository.save(student);
    }

    public void saveStudent(Student student)
    {
        student.setUsertype("student");
        studentRepository.save(student);
    }
}
