package com.btitsolutions.driverstraining.Models;

/**
 * Created by Bereket on 5/11/2017.
 */

public class EvaluationTypeModel {
    private String code;
    private String categoryCode;
    private String name;

    public EvaluationTypeModel()
    {
    }

    public EvaluationTypeModel(String code, String categoryCode, String name)
    {
        this.code = code;
        this.categoryCode = categoryCode;
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

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
}
