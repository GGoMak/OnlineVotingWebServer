package com.ggomak.vote.springboot.domain.enums;

public enum Department {
    computerScience("컴퓨터학부"),
    philosophy("철학과"),
    administration("행정학과"),
    accounting("회계학과"),
    electronicEngineering("전자공학과"),
    physics("물리학과");

    private String value;

    Department(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
