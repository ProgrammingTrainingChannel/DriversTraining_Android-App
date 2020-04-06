package com.btitsolutions.driverstraining.Models;

/**
 * Created by Bereket on 5/11/2017.
 */

public class EvaluationCategoryModel {
    private String code;
    private String name;

    public EvaluationCategoryModel()
    {
    }

    public EvaluationCategoryModel(String code, String name)
    {
        this.code = code;
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
