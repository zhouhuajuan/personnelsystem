package com.personnelsystem.event.service;

import com.personnelsystem.event.dto.result.AllEmployeeResult;
import com.personnelsystem.event.dto.result.GetEmployeesResult;
import com.personnelsystem.event.entity.Employee;

import java.util.List;

public interface EmployeeService {

    /**
     * 添加员工
     * @param employee 员工
     * @return int
     */
    int addEmployee(Employee employee);

    /**
     * 根据员工id删除员工
     * @param id 员工id
     * @return int
     */
    int deleteEmployee(int id);

    /**
     * 根据员工id修改员工信息
     * @param employee 员工
     * @return int
     */
    int modifyEmployee(Employee employee);

    /**
     * 根据条件查询员工信息---只能模糊查询姓名和地址
     * @return GetEmployeesResult局部的员工信息
     */
    List<GetEmployeesResult> selectEmployee(String condition);

    /**
     * 获取所有员工的局部信息--作为list表的展示--因为不展示员工的所有信息
     * @return List<GetEmployeesResult> 获取局部的员工信息
     */
    List<GetEmployeesResult> getEmployees(Byte flag);

    /**
     * 获取所有员工的所有信息
     * @return List<AllEmployeeResult>
     */
    List<AllEmployeeResult> getAllEmployee();

    /**
     * 根据id拿到员工全部信息--未转化
     * @param id id
     * @return Employee
     */
    Employee getEmployee(Integer id);

    /**
     * 根据id拿到员工所有信息--转化为String
     * @param id id
     * @return AllEmployeeResult
     */
    AllEmployeeResult getAllEmployee(Integer id);

    /**
     *
     * @param id 根据员工id撤回删除
     * @return int
     */
    int cancelDelete(int id);
}
