package com.btitsolutions.driverstraining.Models;

/**
 * Created by Bereket on 5/11/2017.
 */

public class ChoiceModel {
    public int ID;
    public int QuestionID;
    public String ChoiceText;
    public boolean IsAnswer;

    public ChoiceModel()
    {
    }

    public ChoiceModel(int ID, int QuestionID, String ChoiceText, boolean IsAnswer)
    {
        this.ID = ID;
        this.QuestionID = QuestionID;
        this.ChoiceText = ChoiceText;
        this.IsAnswer = IsAnswer;
    }

    public int getID() {
        return ID;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public String getChoiceText() {
        return ChoiceText;
    }

    public boolean getIsAnswer() {
        return IsAnswer;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setAnswer(boolean answer) {
        IsAnswer = answer;
    }

    public void setChoiceText(String choiceText) {
        ChoiceText = choiceText;
    }

    public void setQuestionID(int questionID) {
        QuestionID = questionID;
    }
}
