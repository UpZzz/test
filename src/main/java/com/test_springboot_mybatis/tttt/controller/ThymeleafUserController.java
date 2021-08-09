package com.test_springboot_mybatis.tttt.controller;

import com.test_springboot_mybatis.tttt.entity.Student;
import com.test_springboot_mybatis.tttt.mapper.StudentMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class ThymeleafUserController {
    private final Logger log = LoggerFactory.getLogger(ThymeleafUserController.class);

    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/userlist")
    public String userList(Model model){
        //打印日志
        log.info("ThymeleafUserController userList info log start");
        model.addAttribute("contents",studentMapper.selectAllStudent());
        return "/user/userlist";
    }

    @GetMapping("/form")
    public String form(Model model){
        log.info("ThymeleafUserController form info log start");
        model.addAttribute("user" , new Student(1,"小崔","女"));
        return "user/form";
    }

    @GetMapping("{id}")
    public String userview(@PathVariable("id") int id , Model model){
        Student student = studentMapper.selectByPrimaryKey(id);
        model.addAttribute("user",student);
        return "user/userview";
    }

    @GetMapping(value = "edit/{id}")
    public String editForm(@PathVariable("id") int id , Model model){
        log.info("ThymeleafUserController editForm info log start");
        Student user = studentMapper.selectByPrimaryKey(id);
        model.addAttribute("user" , user);
        return "user/form";
    }

    @GetMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") int id){
        studentMapper.deleteByPrimaryKey(id);
        return "common/success";
    }
}
