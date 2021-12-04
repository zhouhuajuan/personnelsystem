package com.personnelsystem.event.dto.result;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class AllEmployeeResult {

    private Integer id;

    private String name;

    private String gender;

    private Date birthday;

    private String edu;

    private String deptName;

    private String photo;

    private String phoneNumber;

    private String address;

    private String idCard;
}
