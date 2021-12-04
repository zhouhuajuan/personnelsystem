package com.personnelsystem.event.enums;

import lombok.Getter;

@Getter
public enum EducationStatusEnums {
    PRIMARY_SCHOOL((byte)1, "小学"),
    JUNIOR_HIGH_SCHOOL((byte)2, "初中"),
    HIGH_SCHOOL((byte)3, "高中"),
    JUNIOR_COLLEGE((byte)4, "专科"),
    UNDERGRADUATE_COLLEGE((byte)5, "本科"),
    MASTER((byte)6, "硕士"),
    DOCTOR((byte)7, "博士");

    private final Byte code;

    private final String type;

    EducationStatusEnums(Byte code, String type) {
        this.code = code;
        this.type = type;
    }

    public static EducationStatusEnums getTypeByCode(Byte code){
        for(EducationStatusEnums type: values()){
            if(code.equals(type.getCode())){
                return type;
            }
        }
        return null;
    }
}
