package com.btitsolutions.driverstraining.Models;

import java.util.List;

/**
 * Created by Bereket on 5/11/2017.
 */

public class QuestionModel {
    public int ID;
    public int CategoryID;
    public int TypeID;
    public String CategoryName;
    public String TypeName;
    public String QuestionText;

    public List<ChoiceModel> Choices;

    public QuestionModel()
    {
    }

    public QuestionModel(int ID, int CategoryID, int TypeID, String CategoryName, String TypeName, String QuestionText)
    {
        this.ID = ID;
        this.CategoryID = CategoryID;
        this.TypeID = TypeID;
        this.CategoryName = CategoryName;
        this.TypeName = TypeName;
        this.QuestionText = QuestionText;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public int getID() {
        return ID;
    }

    public int getTypeID() {
        return TypeID;
    }

    public List<ChoiceModel> getChoices() {
        return Choices;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public String getQuestionText() {
        return QuestionText;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setCategoryID(int categoryID) {
        this.CategoryID = categoryID;
    }

    public void setCategoryName(String categoryName) {
        this.CategoryName = categoryName;
    }

    public void setChoices(List<ChoiceModel> choices) {
        this.Choices = choices;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setQuestionText(String questionText) {
        this.QuestionText = questionText;
    }

    public void setTypeID(int typeID) {
        this.TypeID = typeID;
    }

    public void setTypeName(String typeName) {
        this.TypeName = typeName;
    }
}
