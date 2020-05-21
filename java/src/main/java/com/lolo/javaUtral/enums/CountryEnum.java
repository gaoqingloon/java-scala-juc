package com.lolo.javaUtral.enums;

import lombok.Getter;

public enum CountryEnum {

    ONE(1, "韩"), TWO(2, "魏"), THREE(3, "赵"), FOUR(4, "齐"), FIVE(5, "楚"), SIX(6, "燕");

    @Getter private Integer retCode;
    @Getter private String retMessage;

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum forEachCountryEnum(int index) {
        CountryEnum[] myArray = CountryEnum.values();
        for (CountryEnum ele : myArray) {
            if (index == ele.getRetCode()) {
                return ele;
            }
        }
        return null;
    }
}
