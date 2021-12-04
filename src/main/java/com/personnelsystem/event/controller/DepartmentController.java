package com.personnelsystem.event.controller;

import com.personnelsystem.event.entity.Department;
import com.personnelsystem.event.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: personnelsystem1
 * @description: 部门控制层
 * @author: 周华娟
 * @create: 2021-11-24 13:27
 **/

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/dept")
    public String list(Model model){
        List<Department> allDept = departmentService.getAllDept();
        model.addAttribute("departments",allDept);
        return "dept/list1";
    }

    @GetMapping("/dept/add")
    public String toAddPage(){
        return "dept/addDept";
    }

    @PostMapping("/addDept")
    public String addDept(Department department, HttpSession session){
        //添加部门
        System.out.println("------>"+department);
        int i = departmentService.addDepartment(department);
        if (i==0){
            //部门已存在
            session.setAttribute("msg_dept","部门已存在！");
            return "redirect:/dept/add";
        }else {
            return "redirect:/dept";
        }
    }

    @GetMapping("/dept/update/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id, Model model){
        //获取部门信息
        Department deptById = departmentService.getDeptById(id);
        model.addAttribute("dept",deptById);
        return "dept/updateDept";
    }

    @PostMapping("/updateDept")
    public String updateDept(Department department){
        //修改部门
        departmentService.modifyDepartment(department);
        return "redirect:/dept";
    }

    @GetMapping("/dept/delete/{id}")
    public String deleteDept(@PathVariable("id") Integer id){
        departmentService.deleteDepartment(id);
        return "redirect:/dept";
    }
}
