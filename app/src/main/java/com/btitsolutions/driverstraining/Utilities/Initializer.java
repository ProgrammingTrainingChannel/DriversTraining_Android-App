package com.btitsolutions.driverstraining.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.btitsolutions.driverstraining.Models.ChoiceModel;
import com.btitsolutions.driverstraining.Models.EvaluationCategoryModel;
import com.btitsolutions.driverstraining.Models.EvaluationTypeModel;
import com.btitsolutions.driverstraining.Models.QuestionModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Bereket on 9/6/2017.
 */

public class Initializer {
    public void LoadEvaluationCategory(Context context){
        DBHelper dbHelper = new DBHelper(context);
        for (int i = 0; i < 1; i++)
        {
            EvaluationCategoryModel newCategory;
            newCategory = new EvaluationCategoryModel("1", " ሞተር ሳይክል");
            dbHelper.addEvaluationCategory(newCategory);

            newCategory = new EvaluationCategoryModel("2", " አውቶሞቢል");
            dbHelper.addEvaluationCategory(newCategory);

            newCategory = new EvaluationCategoryModel("3", " ታክሲ 1 እና 2");
            dbHelper.addEvaluationCategory(newCategory);

            newCategory = new EvaluationCategoryModel("4", " ህዝብ 1 እና 2");
            dbHelper.addEvaluationCategory(newCategory);

            newCategory = new EvaluationCategoryModel("5", " ደረቅ ጭነት 1, 2 እና 3");
            dbHelper.addEvaluationCategory(newCategory);

            newCategory = new EvaluationCategoryModel("6", " ፈሳሽ ጭነት 1 እና 2");
            dbHelper.addEvaluationCategory(newCategory);
        }
    }

    public void LoadEvaluationType(Context context, String categoryCode){
        DBHelper dbHelper = new DBHelper(context);
        for (int i = 0; i < 1; i++)
        {
            EvaluationTypeModel newType;

            if(categoryCode.equals("1"))
            {
                newType = new EvaluationTypeModel("8", categoryCode, " የሞተር ልዩነት");
                dbHelper.addEvaluationType(newType);
            }
            else if(categoryCode.equals("2"))
            {
                newType = new EvaluationTypeModel("1", categoryCode, " ስነምግባር");
                dbHelper.addEvaluationType(newType);

                newType = new EvaluationTypeModel("2", categoryCode, " ተግባቦት");
                dbHelper.addEvaluationType(newType);

                newType = new EvaluationTypeModel("3", categoryCode, " የማሽከርከር ስልት");
                dbHelper.addEvaluationType(newType);

                newType = new EvaluationTypeModel("4", categoryCode, " የአደጋ ምላሽ አሰጣጥ");
                dbHelper.addEvaluationType(newType);

                newType = new EvaluationTypeModel("5", categoryCode, " የመንገድ ስነስርአት");
                dbHelper.addEvaluationType(newType);

                newType = new EvaluationTypeModel("6", categoryCode, " የጉዞ መረጃ");
                dbHelper.addEvaluationType(newType);

                newType = new EvaluationTypeModel("7", categoryCode, " ቴክኒክ");
                dbHelper.addEvaluationType(newType);
            }
            else if ((categoryCode.equals("3")) || (categoryCode.equals("4")))
            {
                newType = new EvaluationTypeModel("9", categoryCode, " ታክሲና የህዝብ ጭነት ልዩነት");
                dbHelper.addEvaluationType(newType);
            }
            else if (categoryCode.equals("6"))
            {
                newType = new EvaluationTypeModel("10", categoryCode, " የፈሳሽ ጭነት ልዩነት");
                dbHelper.addEvaluationType(newType);
            }
            else if (categoryCode.equals("5"))
            {
                newType = new EvaluationTypeModel("11", categoryCode, " የደረቅ ጭነት ልዩነት");
                dbHelper.addEvaluationType(newType);
            }
        }

    }

    private String ReadFromFile(Context context, String filename) {
        BufferedReader reader = null;
        StringBuilder returnString = new StringBuilder();

        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(filename), "UTF-16LE"));

            String mLine;
            while ((mLine = reader.readLine()) != null) {
                returnString.append(mLine);
            }

            return returnString.toString();
        }
        catch (IOException e) {
            return returnString.toString();
        }
        finally {
            if (reader != null) {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    //log the exception
                }
            }
        }
    }

    public void SaveQuestionChoiceFromFile(Context context, String filename, int categoryID, int typeID, String categoryName, String typeName) {
        try {
            String questionWithChoices = ReadFromFile(context, filename);
            String[] AllQuestionWithChoices = questionWithChoices.split("@@");

            DBHelper dbHelper = new DBHelper(context);
            for (String AllQuestionWithChoice : AllQuestionWithChoices) {
                String[] SingleQuestionWithChoices = AllQuestionWithChoice.split("#");

                if (!SingleQuestionWithChoices[0].trim().equals("")) {
                    int currentQuestionID = dbHelper.getQuestionCount() + 1;

                    QuestionModel questionModel = new QuestionModel();
                    questionModel.setID(currentQuestionID);
                    questionModel.setCategoryID(categoryID);
                    questionModel.setTypeID(typeID);
                    questionModel.setCategoryName(categoryName);
                    questionModel.setTypeName(typeName);
                    questionModel.setQuestionText(SingleQuestionWithChoices[0].trim());

                    dbHelper.addQuestion(questionModel);

                    for (int j = 1; j < SingleQuestionWithChoices.length; j++) {
                        ChoiceModel choiceModel = new ChoiceModel();
                        choiceModel.setID(dbHelper.getChoiceCount() + 1);
                        choiceModel.setQuestionID(currentQuestionID);
                        choiceModel.setChoiceText(SingleQuestionWithChoices[j].trim());

                        if (SingleQuestionWithChoices[j].contains("1")) {
                            choiceModel.setAnswer(true);
                        } else {
                            choiceModel.setAnswer(false);
                        }

                        dbHelper.addChoice(choiceModel);
                    }
                }
            }
        }
        catch(Exception ex){
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void LoadQuestionWithChoice(Context context) {
        SharedPreferences GeneralSettings = context.getSharedPreferences("GeneralSettings", MODE_PRIVATE);

        try{
            String QuestionLoaded = GeneralSettings.getString("QuestionLoaded", "False");

            if(QuestionLoaded.equals("False")){
                SaveQuestionChoiceFromFile(context, "behavior.txt", 2, 1, "አውቶሞቢል", "ስነምግባር");
                SaveQuestionChoiceFromFile(context, "communication.txt", 2, 2, "አውቶሞቢል", "ተግባቦት");
                SaveQuestionChoiceFromFile(context, "driving_silit.txt", 2, 3, "አውቶሞቢል", "የማሽከርከር ስልት");
                SaveQuestionChoiceFromFile(context, "emergency.txt", 2, 4, "አውቶሞቢል", "የአደጋ ምላሽ አሰጣጥ");
                SaveQuestionChoiceFromFile(context, "law.txt", 2, 5, "አውቶሞቢል", "የመንገድ ስነስርአት");
                SaveQuestionChoiceFromFile(context, "yeguzo_merega.txt", 2, 6, "አውቶሞቢል", "የጉዞ መረጃ");
                SaveQuestionChoiceFromFile(context, "technic.txt", 2, 7, "አውቶሞቢል", "ቴክኒክ");
                SaveQuestionChoiceFromFile(context, "dry_cargo.txt", 5, 11, "ደረቅ ጭነት 1, 2 እና 3", "የደረቅ ጭነት ልዩነት");
                SaveQuestionChoiceFromFile(context, "liquid_cargo.txt", 6, 10, "ፈሳሽ ጭነት 1 እና 2", "የፈሳሽ ጭነት ልዩነት");
                SaveQuestionChoiceFromFile(context, "luggage_passenger.txt", 3, 8, "ህዝብ 1 እና 2", "ታክሲና የህዝብ ጭነት ልዩነት");
                SaveQuestionChoiceFromFile(context, "luggage_passenger.txt", 3, 9, "ታክሲ 1 እና 2", "ታክሲና የህዝብ ጭነት ልዩነት");
                SaveQuestionChoiceFromFile(context, "motor_cycle.txt", 1, 8, "ሞተር ሳይክል", "የሞተር ልዩነት");

                SharedPreferences.Editor editor;
                editor = GeneralSettings.edit();
                editor.putString("QuestionLoaded", "True");
                editor.apply();
            }
        }
        catch(Exception ex){
            SharedPreferences.Editor editor;

            //set currently selected subject to preferences for further use
            editor = GeneralSettings.edit();
            editor.putString("QuestionLoaded", "False");
            editor.apply();
        }
    }
}
