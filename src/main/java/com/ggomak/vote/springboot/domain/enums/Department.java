package com.ggomak.vote.springboot.domain.enums;

public enum Department {
    accounting("회계학과"),
    administration("행정학과"),
    computerScience("컴퓨터학부"),
    electronicEngineering("전자공학과"),
    philosophy("철학과"),
    physics("물리학과");

    private String value;
    private int size;

    Department(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    public static int size(){ return values().length; }
}
