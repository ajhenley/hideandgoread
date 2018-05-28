package com.ajlabs.demo.service;

import com.ajlabs.demo.model.Emessage;
import com.ajlabs.demo.model.Student;
import com.ajlabs.demo.repository.ClassroomRepository;
import com.ajlabs.demo.repository.EmessageRepository;
import com.ajlabs.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EmessageRepository emessageRepository;

    @Autowired
    UserService userService;

    @Override
    public void run(String... strings) throws Exception {
        Student teacher = new Student();
        teacher.setName("Sutton Foster");
        teacher.setEmail("s@f.com");
        teacher.setPassword("123456");

        userService.createClassroom(teacher, "Intro to Cybersecurity", "", 5);

        Student myteacher = studentRepository.findByUsername(teacher.getEmail());
        Student mystudent = studentRepository.findByUsername("2_4");
        Emessage unencryptedMessage = new Emessage( myteacher.getId(), myteacher.getName(), mystudent.getId(), mystudent.getName(), "Test Message", "Body of Test Message", false, false, new Date());
        emessageRepository.save(unencryptedMessage);
    }
}
