package com.personnelsystem.event.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Department {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String deptName;
}