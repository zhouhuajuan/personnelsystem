package com.personnelsystem.event.controller;

import com.personnelsystem.event.dto.request.TablesDTO;
import com.personnelsystem.event.service.DepartmentService;
import com.personnelsystem.event.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: personnelsystem1
 * @description: 打印报表controller
 * @author: 周华娟
 * @create: 2021-12-03 13:52
 **/
@Controller
public class TableController {

    @Autowired
    private TableService tableService;

    @GetMapping("/tab")
    public String toTablePage(Model model){
        List<TablesDTO> tables = new ArrayList<>();
        tables.add(new TablesDTO(0,"员工"));
        tables.add(new TablesDTO(1,"部门"));
        model.addAttribute("tables",tables);
        return "tab/table";
    }

    @PostMapping("/table")
    public void getTable(Integer id, HttpServletResponse response) throws IOException {
        //打印报表
        tableService.downloadTable(id,response);
    }
}
