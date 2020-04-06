package com.btitsolutions.driverstraining.Models;

/**
 * Created by Bereket on 5/11/2017.
 */

public class QuestionTypeModel {
    private String code;
    private String name;

    public QuestionTypeModel()
    {
    }

    public QuestionTypeModel(String code, String name)
    {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
