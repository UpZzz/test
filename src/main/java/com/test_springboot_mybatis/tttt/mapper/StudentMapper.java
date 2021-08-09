package com.test_springboot_mybatis.tttt.mapper;

import com.test_springboot_mybatis.tttt.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> selectAllStudent();
}