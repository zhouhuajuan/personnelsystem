package com.personnelsystem.event.service.impl;

import com.personnelsystem.event.dto.result.AllEmployeeResult;
import com.personnelsystem.event.dto.result.GetEmployeesResult;
import com.personnelsystem.event.entity.Department;
import com.personnelsystem.event.enums.EmployeeStatusEnums;
import com.personnelsystem.event.service.DepartmentService;
import com.personnelsystem.event.service.EmployeeService;
import com.personnelsystem.event.service.TableService;
import com.personnelsystem.event.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @program: personnelsystem1
 * @description:
 * @author: 周华娟
 * @create: 2021-12-03 15:06
 **/

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public void downloadTable(Integer id, HttpServletResponse response) throws IOException {
        StringBuilder result = new StringBuilder();
        if (id == 0){
            //员工报表打印
            List<AllEmployeeResult> allEmployee = employeeService.getAllEmployee();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

            for (AllEmployeeResult res: allEmployee) {
                String transBirth = simpleDateFormat.format(res.getBirthday());
                String s = "编号为："+res.getId()+"，姓名为："+res.getName()+"，性别："+res.getGender()
                        +"，生日为："+transBirth+"，学历为："+res.getEdu()+"，部门为："+res.getDeptName()
                        +"，电话为："+res.getPhoneNumber()+"，地址为："+res.getAddress()
                        +"，身份证为："+res.getIdCard();
                result.append(s).append("\r\n");
            }
        }else if (id == 1){
            //部门报表打印
            List<Department> allDept = departmentService.getAllDept();
            for (Department dept: allDept) {
                String d = "编号为："+dept.getId()+"，部门名为："+dept.getDeptName();
                result.append(d).append("\r\n");
            }
        }

        FileUtil.downloadFile(response,String.valueOf(result));
    }
}
