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
     * 根据条件查询员工信息---不完整--传一个string(条件)，数据，返回一个list
     * @return Employee
     */
    List<GetEmployeesResult> selectEmployee(String condition);

    /**
     * 获取所有员工信息
     * @return List<Employee>
     */
    List<GetEmployeesResult> getEmployees(Byte flag);

    List<AllEmployeeResult> getAllEmployee();

    Employee getEmployee(Integer id);

    AllEmployeeResult getAllEmployee(Integer id);

    /**
     *
     * @param id 根据员工id撤回删除
     * @return int
     */
    int cancelDelete(int id);
}
