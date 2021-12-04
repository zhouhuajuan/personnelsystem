package com.personnelsystem.event.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.personnelsystem.event.entity.Department;
import com.personnelsystem.event.mapper.DepartmentMapper;
import com.personnelsystem.event.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: personnelsystem1
 * @description:
 * @author: 周华娟
 * @create: 2021-11-24 13:31
 **/
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAllDept() {
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        return departmentMapper.selectList(wrapper);
    }

    @Override
    public Department getDeptById(Integer id) {
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return departmentMapper.selectOne(wrapper);
    }

    @Override
    public int addDepartment(Department department) {
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        wrapper.eq("dept_name",department.getDeptName());
        Department department1 = departmentMapper.selectOne(wrapper);
        if (!ObjectUtil.isEmpty(department1)){
            //已存在则添加失败
            return 0;
        }else {
            return departmentMapper.insert(department);
        }
    }

    @Override
    public int modifyDepartment(Department department) {
//        QueryWrapper<Department> wrapper = new QueryWrapper<>();
//        wrapper.eq("id",department.getId());
//        Department department1 = departmentMapper.selectOne(wrapper);
//        if (ObjectUtil.isEmpty(department1)){
//            throw new DepartmentNotExistException();
//        }
        //这里修改只能修改部门的名字
        return departmentMapper.updateById(department);
    }

    @Override
    public int deleteDepartment(Integer id) {
//        QueryWrapper<Department> wrapper = new QueryWrapper<>();
//        wrapper.eq("id",id);
//        Department department = departmentMapper.selectOne(wrapper);
//        if (ObjectUtil.isEmpty(department)){
//            throw new DepartmentNotExistException();
//        }
        return departmentMapper.deleteById(id);
    }
}
