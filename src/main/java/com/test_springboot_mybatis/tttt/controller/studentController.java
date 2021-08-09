package com.test_springboot_mybatis.tttt.controller;

import com.test_springboot_mybatis.tttt.entity.Student;
import com.test_springboot_mybatis.tttt.mapper.StudentMapper;
import com.test_springboot_mybatis.tttt.utils.result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "desc of class")
@Controller
@RequestMapping("/student")
public class studentController {

    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("selectById")
    public result getById(int id){
        return result.success(studentMapper.selectByPrimaryKey(id));
    }

    @RequestMapping("testThymeleaf")
    public void testThymeleaf(){

    }
}
