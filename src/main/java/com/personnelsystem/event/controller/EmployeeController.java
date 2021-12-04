package com.personnelsystem.event.controller;

import cn.hutool.core.io.FileUtil;
import com.personnelsystem.event.dto.result.AllEmployeeResult;
import com.personnelsystem.event.dto.result.GetEmployeesResult;
import com.personnelsystem.event.entity.Department;
import com.personnelsystem.event.entity.Education;
import com.personnelsystem.event.entity.Employee;
import com.personnelsystem.event.enums.EmployeeStatusEnums;
import com.personnelsystem.event.service.DepartmentService;
import com.personnelsystem.event.service.EducationService;
import com.personnelsystem.event.service.EmployeeService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @program: personnelsystem1
 * @description: 员工控制层
 * @author: 周华娟
 * @create: 2021-11-24 13:27
 **/
@Controller
public class EmployeeController {

    @Value("${upload.img.path}")
    private String uploadPathImg;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EducationService educationService;

    @GetMapping("/emp")
    public String list(Model model){
        List<GetEmployeesResult> employees = employeeService.getEmployees(EmployeeStatusEnums.NOT_DELETE.getCode());
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    @GetMapping("/emp/add")
    public String toAddPage(Model model){
        //获取所有学历信息
        List<Education> allEdu = educationService.getAllEdu();
        //获取所有部门信息
        List<Department> allDept = departmentService.getAllDept();
        model.addAttribute("educations",allEdu)
                .addAttribute("departments",allDept);
        return "emp/add";
    }

    @PostMapping("/addEmp")
    public String addEmp(@RequestParam(value = "file") MultipartFile file,
                         Employee employee, HttpSession session) throws IOException {
        if (file != null){
            String originalName = file.getOriginalFilename();
            String extension = originalName.substring(originalName.lastIndexOf("."));

            if (!extension.equals(".png") && !extension.equals(".jpg")){
                session.setAttribute("msg","文件格式不对");
                return "redirect:/emp/add";
            }

            String fileName =  originalName+(new Date());
            //加密后的图片名
            String md5FileName = new Md5Hash(fileName).toHex();

            String filePath = uploadPathImg+md5FileName+extension;
            FileUtil.writeBytes(file.getBytes(), filePath);
            employee.setPhoto(filePath);
        }

        //添加员工
        System.out.println("------>"+employee);
        int i = employeeService.addEmployee(employee);
        if (i==0){
            //员工已存在
            session.setAttribute("msg","员工已存在！");
            return "redirect:/emp/add";
        }else {
            return "redirect:/emp";
        }
    }

//    @PostMapping(value = "/fileUpload")
//    public String fileUpload(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request) {
//        if (file.isEmpty()) {
//            System.out.println("文件为空空");
//        }
//        String fileName = file.getOriginalFilename();  // 文件名
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
//        String filePath = "D://temp-rainy//"; // 上传后的路径
//        fileName = UUID.randomUUID() + suffixName; // 新文件名
//        File dest = new File(filePath + fileName);
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdirs();
//        }
//        try {
//            file.transferTo(dest);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String filename = "/temp-rainy/" + fileName;
//        model.addAttribute("filename", filename);
//        return "file";
//    }

    @GetMapping("/emp/update/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id, Model model){
        //获取所有该员工信息
        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("emp",employee);

        //获取所有学历信息
        List<Education> allEdu = educationService.getAllEdu();
        model.addAttribute("educations",allEdu);

        //获取所有部门信息
        List<Department> allDepartments = departmentService.getAllDept();
        model.addAttribute("departments",allDepartments);
        return "emp/update";
    }

    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee){
        //修改员工
        employeeService.modifyEmployee(employee);
        return "redirect:/emp";
    }

//    @PostMapping("/updateEmp")
//    public String updateEmp(@RequestParam(value = "file") MultipartFile file,Employee employee
//            ,HttpSession session) throws IOException {
//
//        System.out.println("-------------------------------->"+file);
//        String originalName = file.getOriginalFilename();
//        if (originalName != null || !originalName.equals("")){
//            System.out.println("------------------------->"+originalName);
//            String extension = originalName.substring(originalName.lastIndexOf("."));
//
//            if (!extension.equals(".png") && !extension.equals(".jpg")){
//                session.setAttribute("msgUpt","文件格式不对");
//                return "redirect:/emp/add";
//            }
//
//            String fileName =  originalName+(new Date());
//            //加密后的图片名
//            String md5FileName = new Md5Hash(fileName).toHex();
//            String filePath = uploadPathImg+md5FileName+extension;
//            FileUtil.writeBytes(file.getBytes(), filePath);
//            employee.setPhoto(filePath);
//        }
//
//        //修改员工
//        employeeService.modifyEmployee(employee);
//        return "redirect:/emp";
//    }

    @GetMapping("/emp/delete/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeService.deleteEmployee(id);
        return "redirect:/emp";
    }

    @GetMapping("/rec")
    public String listRec(Model model){
        List<GetEmployeesResult> employees = employeeService.getEmployees(EmployeeStatusEnums.DELETE.getCode());
        model.addAttribute("emps",employees);
        return "emp/listRec";
    }

    @GetMapping("/cancel/{id}")
    public String cancelDel(@PathVariable("id") Integer id){
        employeeService.cancelDelete(id);
        return "redirect:/rec";
    }

    @PostMapping("/search")
    public String search(String content,Model model){
        System.out.println("--------------->>>>"+content);
        //模糊查询名字和地址，其他的没有实现
        List<GetEmployeesResult> list = employeeService.selectEmployee(content);
        model.addAttribute("result",list);
        return "emp/listRes";
    }

    @GetMapping("/check/{id}")
    public String getEmployee(@PathVariable("id") Integer id,Model model){
        AllEmployeeResult employee = employeeService.getAllEmployee(id);
        model.addAttribute("employee",employee);
        return "common/check";
    }

}
