package com.engobytes.addressor.utils;

public enum SufixEnum {
    NOTHING(""),
    SPACEBAR(" "),
    COMA(", ");

    String sign;

    SufixEnum(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

}
