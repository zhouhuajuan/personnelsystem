package com.personnelsystem.event.enums;

import lombok.Getter;

@Getter
public enum SexStatusEnums {
    FEMALE((byte)0, "女"),
    MALE((byte)1, "男");

    private final Byte code;

    private final String type;

    SexStatusEnums(Byte code, String type) {
        this.code = code;
        this.type = type;
    }

    public static SexStatusEnums getTypeByCode(Byte code){
        for(SexStatusEnums type: values()){
            if(code.equals(type.getCode())){
                return type;
            }
        }
        return null;
    }
}
