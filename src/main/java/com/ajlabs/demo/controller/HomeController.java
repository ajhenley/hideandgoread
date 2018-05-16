package com.ajlabs.demo.controller;

import com.ajlabs.demo.model.Student;
import com.ajlabs.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model){
        model.addAttribute("student", new Student());
        return "registration";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String processRegistrationPage(
            @Valid @ModelAttribute("student") Student student,
            @RequestParam("numstu") String numberOfStudents,
            @RequestParam("course") String course,
            BindingResult result,
            Model model){

        model.addAttribute("student", student);

        if (result.hasErrors()) {
            return "registration";
        } else {
            userService.saveTeacher(student);
            model.addAttribute("message", "Teacher Account Successfully Created");
        }
        return "index";
    }
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/secure")
    public String admin(){
        return "secure";
    }
}
