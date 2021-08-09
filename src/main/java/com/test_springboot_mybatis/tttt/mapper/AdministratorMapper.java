package com.test_springboot_mybatis.tttt.mapper;

import com.test_springboot_mybatis.tttt.entity.Administrator;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.List;

public interface AdministratorMapper {
    List<Administrator> findByPage(int offset, int pageSize);

    int count();

    int deleteByPrimaryKey(Integer id);

    int insert(Administrator record);

    int insertSelective(Administrator record);

    Administrator selectByPrimaryKey(Integer id);

    List<Administrator> findAll();

    Administrator selectByUsername(String username);

    int updateByPrimaryKeySelective(Administrator record);

    int updateByPrimaryKey(Administrator record);
}