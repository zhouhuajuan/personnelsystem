package com.personnelsystem.event.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.personnelsystem.event.dto.result.AllEmployeeResult;
import com.personnelsystem.event.dto.result.GetEmployeesResult;
import com.personnelsystem.event.entity.Department;
import com.personnelsystem.event.entity.Employee;
import com.personnelsystem.event.enums.EmployeeStatusEnums;
import com.personnelsystem.event.mapper.DepartmentMapper;
import com.personnelsystem.event.mapper.EmployeeMapper;
import com.personnelsystem.event.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: personnelsystem1
 * @description:
 * @author: 周华娟
 * @create: 2021-11-23 10:01
 **/
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public int addEmployee(Employee employee) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("id_card",employee.getIdCard());
        Employee employee1 = employeeMapper.selectOne(wrapper);
        if (employee1 != null){
            return 0;
        }
        return employeeMapper.insert(employee);
    }

    @Override
    public int deleteEmployee(int id) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        Employee employee = employeeMapper.selectOne(wrapper);
        employee.setDelFlag(EmployeeStatusEnums.DELETE.getCode());
        return employeeMapper.updateById(employee);
    }

    @Override
    public int modifyEmployee(Employee employee) {
        return employeeMapper.updateById(employee);
    }

    @Override
    public List<GetEmployeesResult> selectEmployee(String condition) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.like("name",condition).or().like("address",condition);
        List<Employee> employees = employeeMapper.selectList(wrapper);
        if (employees.size()==0){
            return null;
        }

        List<GetEmployeesResult> list = new ArrayList<>();
        for (Employee employee: employees) {
            QueryWrapper<Department> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("id",employee.getDeptId());
            Department department = departmentMapper.selectOne(wrapper1);

            GetEmployeesResult result = new GetEmployeesResult();
            BeanUtils.copyProperties(employee,result);
            result.setDeptName(department.getDeptName());
            list.add(result);
        }
        return list;
    }

    @Override
    public List<GetEmployeesResult> getEmployees(Byte flag) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag", flag);
        List<Employee> employees = employeeMapper.selectList(wrapper);
        List<GetEmployeesResult> list = new ArrayList<>();
        if (employees.size()==0){
            GetEmployeesResult result = new GetEmployeesResult();
            list.add(result);
            return list;
        }

        for (Employee employee: employees) {
            QueryWrapper<Department> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("id",employee.getDeptId());
            Department department = departmentMapper.selectOne(wrapper1);

            GetEmployeesResult result = new GetEmployeesResult();
            BeanUtils.copyProperties(employee,result);
            result.setDeptName(department.getDeptName());
            list.add(result);
        }
        return list;
    }

    @Override
    public List<AllEmployeeResult> getAllEmployee() {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag", EmployeeStatusEnums.NOT_DELETE.getCode());
        List<Employee> employees = employeeMapper.selectList(wrapper);
        List<AllEmployeeResult> list = new ArrayList<>();
//        if (employees.size()==0){
//            AllEmployeeResult result = new AllEmployeeResult();
//            list.add(result);
//            return list;
//        }

        for (Employee employee: employees) {
            QueryWrapper<Department> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("id",employee.getDeptId());
            Department department = departmentMapper.selectOne(wrapper1);

            AllEmployeeResult result = new AllEmployeeResult();
            result.setDeptName(department.getDeptName());
            result.setGender(employee.getSex()==0?"女":"男");
            Integer edu = employee.getEducation();
            if (edu==1){
                result.setEdu("小学");
            }else if (edu==2){
                result.setEdu("初中");
            }else if (edu==3){
                result.setEdu("高中");
            }else if (edu==4){
                result.setEdu("专科");
            }else if (edu==5){
                result.setEdu("本科");
            }else if (edu==6){
                result.setEdu("硕士");
            }else if (edu==7){
                result.setEdu("博士");
            }

            BeanUtils.copyProperties(employee,result);
            list.add(result);
        }
        return list;
    }

    @Override
    public Employee getEmployee(Integer id) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return employeeMapper.selectOne(wrapper);
    }

    @Override
    public AllEmployeeResult getAllEmployee(Integer id) {
        //flag 0--未删除，员工列表查看 1--已删除，撤销列表查看
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        Employee employee = employeeMapper.selectOne(wrapper);

        QueryWrapper<Department> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("id",employee.getDeptId());
        Department department = departmentMapper.selectOne(wrapper1);

        AllEmployeeResult result = new AllEmployeeResult();
        result.setDeptName(department.getDeptName());
        result.setGender(employee.getSex()==0?"女":"男");

        Integer edu = employee.getEducation();
        if (edu==1){
            result.setEdu("小学");
        }else if (edu==2){
            result.setEdu("初中");
        }else if (edu==3){
            result.setEdu("高中");
        }else if (edu==4){
            result.setEdu("专科");
        }else if (edu==5){
            result.setEdu("本科");
        }else if (edu==6){
            result.setEdu("硕士");
        }else if (edu==7){
            result.setEdu("博士");
        }

        BeanUtils.copyProperties(employee,result);
        return result;
    }

    @Override
    public int cancelDelete(int id) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        Employee employee = employeeMapper.selectOne(wrapper);
        employee.setDelFlag(EmployeeStatusEnums.NOT_DELETE.getCode());
        return employeeMapper.updateById(employee);
    }
}
