package com.personnelsystem.event.service;

import com.personnelsystem.event.entity.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDept();

    Department getDeptById(Integer id);

    int addDepartment(Department department);

    int modifyDepartment(Department department);

    int deleteDepartment(Integer id);
}
