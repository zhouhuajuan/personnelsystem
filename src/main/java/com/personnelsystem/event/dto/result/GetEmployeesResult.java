package com.personnelsystem.event.dto.result;

import lombok.Data;

import java.util.Date;

@Data
public class GetEmployeesResult {

    private Integer id;

    private String name;

    private Byte sex;

    private Date birthday;

    private String deptName;

    private String phoneNumber;
}
