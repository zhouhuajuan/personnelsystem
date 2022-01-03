package com.personnelsystem.event.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Employee {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Byte sex;

    private Date birthday;

    private Integer eduId;

    private Integer deptId;

    private String photo;

    private String phoneNumber;

    private String address;

    private String idCard;

    private Byte delFlag;
}