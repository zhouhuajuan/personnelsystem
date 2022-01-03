package com.personnelsystem.event.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.personnelsystem.event.entity.Department;
import com.personnelsystem.event.entity.Employee;
import com.personnelsystem.event.mapper.DepartmentMapper;
import com.personnelsystem.event.mapper.EmployeeMapper;
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

    @Autowired
    private EmployeeMapper employeeMapper;

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
        //这里修改只能修改部门的名字
        return departmentMapper.updateById(department);
    }

    @Override
    public int deleteDepartment(Integer id) {
        //注意这里一个细节，如果这个部门被删除，那么属于这个部门的员工应该将部门设置为空
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("dept_id",id);
        List<Employee> employees = employeeMapper.selectList(wrapper);
        if (employees.size() != 0){
            for (Employee employee : employees) {
                //dept_id=1：空
                employee.setDeptId(1);
                employeeMapper.updateById(employee);
            }
        }
        return departmentMapper.deleteById(id);
    }
}
