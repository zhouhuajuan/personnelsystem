package com.personnelsystem.event.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.personnelsystem.event.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(Employee record);
//
//    Employee selectByPrimaryKey(Integer id);
//
//    List<Employee> selectAll();
//
//    int updateByPrimaryKey(Employee record);
}