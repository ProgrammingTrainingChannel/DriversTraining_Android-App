package com.btitsolutions.driverstraining;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.btitsolutions.driverstraining.Adapters.QuestionTypeAdapter;
import com.btitsolutions.driverstraining.Models.QuestionTypeModel;

import java.util.ArrayList;
import java.util.List;

public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        ArrayList<String> questionTypeModels = new ArrayList<>();
        SharedPreferences GeneralSettings = getSharedPreferences("GeneralSettings", MODE_PRIVATE);
        String Behaviour_QuestionLoaded = GeneralSettings.getString("Behaviour_QuestionLoaded", "False");
        String Communication_QuestionLoaded = GeneralSettings.getString("Communication_QuestionLoaded", "False");
        String DrivingSilit_QuestionLoaded = GeneralSettings.getString("DrivingSilit_QuestionLoaded", "False");
        String Emergency_QuestionLoaded = GeneralSettings.getString("Emergency_QuestionLoaded", "False");
        String Law_QuestionLoaded = GeneralSettings.getString("Law_QuestionLoaded", "False");
        String YeguzoMerega_QuestionLoaded = GeneralSettings.getString("YeguzoMerega_QuestionLoaded", "False");
        String Technic_QuestionLoaded = GeneralSettings.getString("Technic_QuestionLoaded", "False");
        String DryCargo_QuestionLoaded = GeneralSettings.getString("DryCargo_QuestionLoaded", "False");
        String LiquidCargo_QuestionLoaded = GeneralSettings.getString("LiquidCargo_QuestionLoaded", "False");
        String LuggagePassenger_1_QuestionLoaded = GeneralSettings.getString("LuggagePassenger_1_QuestionLoaded", "False");
        String LuggagePassenger_2_QuestionLoaded = GeneralSettings.getString("LuggagePassenger_2_QuestionLoaded", "False");
        String MotorCycle_QuestionLoaded = GeneralSettings.getString("MotorCycle_QuestionLoaded", "False");

        if(Behaviour_QuestionLoaded.equals("False")){
            questionTypeModels.add("የስነምግባር ጥያቄዎች");
        }
        if(Communication_QuestionLoaded.equals("False")){
            questionTypeModels.add("የተግባቦት ጥያቄዎች");
        }
        if(DrivingSilit_QuestionLoaded.equals("False")){
            questionTypeModels.add("የማሽከርከር ስልት ጥያቄዎች");
        }
        if(Emergency_QuestionLoaded.equals("False")){
            questionTypeModels.add("የአደጋ ምላሽ አሰጣጥ ጥያቄዎች");
        }
        if(Law_QuestionLoaded.equals("False")){
            questionTypeModels.add("የመንገድ ስነስርአት ጥያቄዎች");
        }
        if(YeguzoMerega_QuestionLoaded.equals("False")){
            questionTypeModels.add("የጉዞ መረጃ ጥያቄዎች");
        }
        if(Technic_QuestionLoaded.equals("False")){
            questionTypeModels.add("የቴክኒክ ጥያቄዎች");
        }
        if(DryCargo_QuestionLoaded.equals("False")){
            questionTypeModels.add("የደረቅ ጭነት ልዩነት ጥያቄዎች");
        }
        if(LiquidCargo_QuestionLoaded.equals("False")){
            questionTypeModels.add("የፈሳሽ ጭነት ልዩነት ጥያቄዎች");
        }
        if(LuggagePassenger_1_QuestionLoaded.equals("False")){
            questionTypeModels.add("የታክሲ ልዩነት ጥያቄዎች");
        }
        if(LuggagePassenger_2_QuestionLoaded.equals("False")){
            questionTypeModels.add("የህዝብ ጭነት ልዩነት ጥያቄዎች");
        }
        if(MotorCycle_QuestionLoaded.equals("False")){
            questionTypeModels.add("የሞተር ልዩነት ጥያቄዎች");
        }

        QuestionTypeAdapter callContactAdapter = new QuestionTypeAdapter(this, questionTypeModels);
        ListView lstViewQuestionTypes = (ListView)findViewById(R.id.lstViewQuestionTypes);
        lstViewQuestionTypes.setAdapter(callContactAdapter);
    }
}
