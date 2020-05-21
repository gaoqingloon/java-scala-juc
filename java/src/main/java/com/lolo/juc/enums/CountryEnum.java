package com.lolo.juc.enums;

import java.util.Objects;

public enum CountryEnum {

    ONE(1, "韩"), TWO(2, "魏"), THREE(3, "赵"), FOUR(4, "齐"), FIVE(5, "楚"), SIX(6, "燕");
    // mysql
    // ID	col1 col2 col3 col4
    // ONE(k,v1,v2,v3,v4)

    private Integer retCode;
    private String retMessage;

    private CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }

    // for 遍历枚举的每个元素
    public static CountryEnum forEachCountryEnums(Integer index) {
        for (CountryEnum element : values()) {
            // element.getRetCode() == index
            if (Objects.equals(element.getRetCode(), index)) {
                return element;
            }
        }
        return null;
    }
}
