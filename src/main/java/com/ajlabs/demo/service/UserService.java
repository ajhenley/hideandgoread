package com.ajlabs.demo.service;

import com.ajlabs.demo.encryption.RsaEncryptor;
import com.ajlabs.demo.encryption.RsaMessenger;
import com.ajlabs.demo.model.Classroom;
import com.ajlabs.demo.model.Student;
import com.ajlabs.demo.repository.ClassroomRepository;
import com.ajlabs.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    public UserService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }

    public Student findById(Long id){
        return studentRepository.findById(id).get();
    }

    public Iterable<Student> findStudentsByClassroomid(Long id){
        Iterable<Student> students = studentRepository.findAllByClassidAndUsertype(id, "student");
        return students;
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
        RsaEncryptor encryptionHandler;
        RsaMessenger rsaMessenger = new RsaMessenger("1");
        PublicKey publicKey = rsaMessenger.getPublicKey();
        PrivateKey privateKey = rsaMessenger.getPrivateKey();
        student.setUsertype("teacher");
        student.setHeadshot("headshot01.png");
        student.setUsername(student.getEmail());
        student.setPrivateKey(privateKey);
        student.setPublicKey(publicKey);
        studentRepository.save(student);
    }

    public void setTeacherClassroom(Student student, Long classid){
        student.setClassid(classid);
        studentRepository.save(student);
    }

    public void createClassroom(Student student, String coursename, String studentNames, int numStu)
    {
        Student stu = new Student();

        saveTeacher(student);
        Classroom classroom = new Classroom();
        classroom.setTeacherid(student.getId());
        classroom.setTeacherName(student.getName());
        classroom.setPasscode(student.getPassword());
        classroom.setName(coursename);
        classroom.setActive(true);
        classroomRepository.save(classroom);

        setTeacherClassroom(student, classroom.getId());

        String[] stringArray = studentNames.split(",");
        if (stringArray.length == numStu)
        {
            for (String name : stringArray)
            {
                RsaEncryptor encryptionHandler;
                RsaMessenger rsaMessenger = new RsaMessenger("1");
                PublicKey publicKey = rsaMessenger.getPublicKey();
                PrivateKey privateKey = rsaMessenger.getPrivateKey();
                name = name.trim();
                stu = new Student();
                stu.setUsertype("student");
                stu.setHeadshot("headshot01.png");
                stu.setName(name);
                stu.setPassword(student.getPassword());
                stu.setPublicKey(publicKey);
                stu.setPrivateKey(privateKey);
                stu.setUsertype("student");
                Long stuid = stu.getId();
                Long classid = classroom.getId();
                stu.setUsername(classid.toString() + "_" + stuid.toString());
                stu.setEmail("");
                stu.setClassid(classroom.getId());
                studentRepository.save(stu);
            }
        } else {
            for (int i = 1; i <= numStu; i++ )
            {
                RsaEncryptor encryptionHandler;
                RsaMessenger rsaMessenger = new RsaMessenger("1");
                PublicKey publicKey = rsaMessenger.getPublicKey();
                PrivateKey privateKey = rsaMessenger.getPrivateKey();
                String with2digits = String.format("%02d", i);
                String name = "student" + with2digits;
                stu = new Student();
                stu.setUsertype("student");
                stu.setHeadshot("headshot01.png");
                stu.setName(name);
                stu.setPassword(student.getPassword());
                stu.setPublicKey(publicKey);
                stu.setPrivateKey(privateKey);
                stu.setUsertype("student");
                stu.setEmail("");
                stu.setClassid(classroom.getId());
                studentRepository.save(stu);
                Long stuid = stu.getId();
                Long classid = classroom.getId();
                stu.setUsername(classid.toString() + "_" + stuid.toString());
                studentRepository.save(stu);
            }
        }
    }

    public void saveStudent(Student student)
    {
        student.setUsertype("student");
        studentRepository.save(student);
    }
}
