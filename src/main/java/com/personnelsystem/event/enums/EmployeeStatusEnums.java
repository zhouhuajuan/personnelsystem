package com.personnelsystem.event.enums;

import lombok.Getter;

@Getter
public enum EmployeeStatusEnums {
    NOT_DELETE((byte)0, "未删除"),
    DELETE((byte)1, "删除");

    private final Byte code;

    private final String type;

    EmployeeStatusEnums(Byte code, String type) {
        this.code = code;
        this.type = type;
    }

    public static EmployeeStatusEnums getTypeByCode(Byte code){
        for(EmployeeStatusEnums type: values()){
            if(code.equals(type.getCode())){
                return type;
            }
        }
        return null;
    }
}
