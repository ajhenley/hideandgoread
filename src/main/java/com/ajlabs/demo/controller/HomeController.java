package com.ajlabs.demo.controller;

import com.ajlabs.demo.model.Classroom;
import com.ajlabs.demo.model.Student;
import com.ajlabs.demo.repository.ClassroomRepository;
import com.ajlabs.demo.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    ClassroomRepository classroomRepository;


    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model){
        model.addAttribute("student", new Student());
        return "register";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String processRegistrationPage(
            @Valid @ModelAttribute("student") Student student,
            @RequestParam("numstu") int numberOfStudents,
            @RequestParam("course") String course,
            @RequestParam("stunames") String stunames,
            BindingResult result,
            Model model){

        model.addAttribute("student", student);

        if (result.hasErrors()) {
            return "register";
        } else {
            userService.createClassroom(student, course, stunames, numberOfStudents);
            model.addAttribute("message", "Teacher Account Successfully Created");
        }
        return "index";
    }
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(
            Model model,
            @RequestParam(value = "login-courseid", defaultValue = "-999") Long courseid,
            @RequestParam(value = "login-password", defaultValue = "didn't load form") String passcode){

        if (courseid == -999){
            return "studentlogin";
        }

        Classroom classroom = classroomRepository.findById(courseid).get();
        String pasaporte = classroom.getPasscode();
        if (pasaporte.equals(passcode)){
            model.addAttribute("course", classroom);
            model.addAttribute("teacher", userService.findById(classroom.getTeacherid()));
            model.addAttribute("students", userService.findStudentsByClassroomid(courseid));
        }

        return "studentloginsteptwo";
    }


    @RequestMapping("/prelogin")
    public String userLogin(){
        return "studentlogin";
    }

    @RequestMapping("/studentstepone")
    public String studentStepOne(
            Model model,
        @RequestParam(value = "login-courseid") Long courseid,
        @RequestParam(value = "login-password") String passcode){

        Classroom classroom = classroomRepository.findById(courseid).get();

        if(classroom == null){
            return "redirect:/userlogin";
        } else {
            String pasaporte = classroom.getPasscode();
            if (pasaporte.equals(passcode)){
                model.addAttribute("course", classroom);
                model.addAttribute("teacher", userService.findById(classroom.getTeacherid()));
                model.addAttribute("students", userService.findStudentsByClassroomid(courseid));
                return "studentloginsteptwo";
            }
        }
        return "redirect:/userlogin";
    }

    @RequestMapping("/user")
    public String student(){
        return "student";
    }

    @RequestMapping("/admin")
    public String teacher(){
        return "teacher";
    }



}
