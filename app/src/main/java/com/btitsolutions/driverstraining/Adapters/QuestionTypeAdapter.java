package com.btitsolutions.driverstraining.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.btitsolutions.driverstraining.HomeActivity;
import com.btitsolutions.driverstraining.R;
import com.btitsolutions.driverstraining.SplashActivity;
import com.btitsolutions.driverstraining.StartupActivity;
import com.btitsolutions.driverstraining.Utilities.Initializer;

import java.util.ArrayList;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Bereket on 5/12/2017.
 */

public class QuestionTypeAdapter extends BaseAdapter {

    private Activity context_1;

    private ArrayList<String> questionTypeModels;

    public QuestionTypeAdapter(Activity context,
                               ArrayList<String> questionTypeModels) {
        context_1 = context;
        this.questionTypeModels = questionTypeModels;
    }

    @Override
    public int getCount() {
        return questionTypeModels.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView lblName;
        Button btnLoad;

        if (convertView == null) {
            convertView = LayoutInflater.from(context_1).inflate(
                    R.layout.question_type_list, null);
            //convertView = new ViewHolder();
            lblName = (TextView) convertView.findViewById(R.id.lblName);
            lblName.setText(questionTypeModels.get(position));

            btnLoad = (Button)convertView.findViewById(R.id.btnLoad);
            btnLoad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Initializer initializer = new Initializer();
                    if(position == 0){
                        SharedPreferences GeneralSettings = context_1.getSharedPreferences("GeneralSettings", MODE_PRIVATE);
                        String Behaviour_QuestionLoaded = GeneralSettings.getString("Behaviour_QuestionLoaded", "False");
                        if(Behaviour_QuestionLoaded.equals("False")){
                            initializer.SaveQuestionChoiceFromFile(context_1, "behavior.txt", 2, 1, "አውቶሞቢል", "ስነምግባር");

                            SharedPreferences.Editor editor;
                            editor = GeneralSettings.edit();
                            editor.putString("Behaviour_QuestionLoaded", "True");
                            editor.apply();
                        }
                    }
                    else if(position == 1){
                        SharedPreferences GeneralSettings = context_1.getSharedPreferences("GeneralSettings", MODE_PRIVATE);
                        String Communication_QuestionLoaded = GeneralSettings.getString("Communication_QuestionLoaded", "False");
                        if(Communication_QuestionLoaded.equals("False")){
                            initializer.SaveQuestionChoiceFromFile(context_1, "communication.txt", 2, 2, "አውቶሞቢል", "ተግባቦት");

                            SharedPreferences.Editor editor;
                            editor = GeneralSettings.edit();
                            editor.putString("Communication_QuestionLoaded", "True");
                            editor.apply();
                        }
                    }
                    else if(position == 2){
                        SharedPreferences GeneralSettings = context_1.getSharedPreferences("GeneralSettings", MODE_PRIVATE);
                        String DrivingSilit_QuestionLoaded = GeneralSettings.getString("DrivingSilit_QuestionLoaded", "False");
                        if(DrivingSilit_QuestionLoaded.equals("False")){
                            initializer.SaveQuestionChoiceFromFile(context_1, "driving_silit.txt", 2, 3, "አውቶሞቢል", "የማሽከርከር ስልት");

                            SharedPreferences.Editor editor;
                            editor = GeneralSettings.edit();
                            editor.putString("DrivingSilit_QuestionLoaded", "True");
                            editor.apply();
                        }
                    }
                    else if(position == 3){
                        SharedPreferences GeneralSettings = context_1.getSharedPreferences("GeneralSettings", MODE_PRIVATE);
                        String Emergency_QuestionLoaded = GeneralSettings.getString("Emergency_QuestionLoaded", "False");
                        if(Emergency_QuestionLoaded.equals("False")){
                            initializer.SaveQuestionChoiceFromFile(context_1, "emergency.txt", 2, 4, "አውቶሞቢል", "የአደጋ ምላሽ አሰጣጥ");

                            SharedPreferences.Editor editor;
                            editor = GeneralSettings.edit();
                            editor.putString("Emergency_QuestionLoaded", "True");
                            editor.apply();
                        }
                    }
                    else if(position == 4){
                        SharedPreferences GeneralSettings = context_1.getSharedPreferences("GeneralSettings", MODE_PRIVATE);
                        String Law_QuestionLoaded = GeneralSettings.getString("Law_QuestionLoaded", "False");
                        if(Law_QuestionLoaded.equals("False")){
                            initializer.SaveQuestionChoiceFromFile(context_1, "law.txt", 2, 5, "አውቶሞቢል", "የመንገድ ስነስርአት");

                            SharedPreferences.Editor editor;
                            editor = GeneralSettings.edit();
                            editor.putString("Law_QuestionLoaded", "True");
                            editor.apply();
                        }
                    }
                    else if(position == 5){
                        SharedPreferences GeneralSettings = context_1.getSharedPreferences("GeneralSettings", MODE_PRIVATE);
                        String YeguzoMerega_QuestionLoaded = GeneralSettings.getString("YeguzoMerega_QuestionLoaded", "False");
                        if(YeguzoMerega_QuestionLoaded.equals("False")){
                            initializer.SaveQuestionChoiceFromFile(context_1, "yeguzo_merega.txt", 2, 6, "አውቶሞቢል", "የጉዞ መረጃ");

                            SharedPreferences.Editor editor;
                            editor = GeneralSettings.edit();
                            editor.putString("YeguzoMerega_QuestionLoaded", "True");
                            editor.apply();
                        }
                    }
                    else if(position == 6){
                        SharedPreferences GeneralSettings = context_1.getSharedPreferences("GeneralSettings", MODE_PRIVATE);
                        String Technic_QuestionLoaded = GeneralSettings.getString("Technic_QuestionLoaded", "False");
                        if(Technic_QuestionLoaded.equals("False")){
                            initializer.SaveQuestionChoiceFromFile(context_1, "technic.txt", 2, 7, "አውቶሞቢል", "ቴክኒክ");

                            SharedPreferences.Editor editor;
                            editor = GeneralSettings.edit();
                            editor.putString("Technic_QuestionLoaded", "True");
                            editor.apply();
                        }
                    }
                    else if(position == 7){
                        SharedPreferences GeneralSettings = context_1.getSharedPreferences("GeneralSettings", MODE_PRIVATE);
                        String DryCargo_QuestionLoaded = GeneralSettings.getString("DryCargo_QuestionLoaded", "False");
                        if(DryCargo_QuestionLoaded.equals("False")){
                            initializer.SaveQuestionChoiceFromFile(context_1, "dry_cargo.txt", 5, 11, "ደረቅ ጭነት 1, 2 እና 3", "የደረቅ ጭነት ልዩነት");

                            SharedPreferences.Editor editor;
                            editor = GeneralSettings.edit();
                            editor.putString("DryCargo_QuestionLoaded", "True");
                            editor.apply();
                        }
                    }
                    else if(position == 8){
                        SharedPreferences GeneralSettings = context_1.getSharedPreferences("GeneralSettings", MODE_PRIVATE);
                        String LiquidCargo_QuestionLoaded = GeneralSettings.getString("LiquidCargo_QuestionLoaded", "False");
                        if(LiquidCargo_QuestionLoaded.equals("False")){
                            initializer.SaveQuestionChoiceFromFile(context_1, "liquid_cargo.txt", 6, 10, "ፈሳሽ ጭነት 1 እና 2", "የፈሳሽ ጭነት ልዩነት");

                            SharedPreferences.Editor editor;
                            editor = GeneralSettings.edit();
                            editor.putString("LiquidCargo_QuestionLoaded", "True");
                            editor.apply();
                        }
                    }
                    else if(position == 9){
                        SharedPreferences GeneralSettings = context_1.getSharedPreferences("GeneralSettings", MODE_PRIVATE);
                        String LuggagePassenger_1_QuestionLoaded = GeneralSettings.getString("LuggagePassenger_1_QuestionLoaded", "False");
                        if(LuggagePassenger_1_QuestionLoaded.equals("False")){
                            initializer.SaveQuestionChoiceFromFile(context_1, "luggage_passenger.txt", 3, 9, "ታክሲ 1 እና 2", "ታክሲና የህዝብ ጭነት ልዩነት");

                            SharedPreferences.Editor editor;
                            editor = GeneralSettings.edit();
                            editor.putString("LuggagePassenger_1_QuestionLoaded", "True");
                            editor.apply();
                        }
                    }
                    else if(position == 10){
                        SharedPreferences GeneralSettings = context_1.getSharedPreferences("GeneralSettings", MODE_PRIVATE);
                        String LuggagePassenger_2_QuestionLoaded = GeneralSettings.getString("LuggagePassenger_2_QuestionLoaded", "False");
                        if(LuggagePassenger_2_QuestionLoaded.equals("False")){
                            initializer.SaveQuestionChoiceFromFile(context_1, "luggage_passenger.txt", 3, 8, "ህዝብ 1 እና 2", "ታክሲና የህዝብ ጭነት ልዩነት");

                            SharedPreferences.Editor editor;
                            editor = GeneralSettings.edit();
                            editor.putString("LuggagePassenger_2_QuestionLoaded", "True");
                            editor.apply();
                        }
                    }
                    else if(position == 11){
                        SharedPreferences GeneralSettings = context_1.getSharedPreferences("GeneralSettings", MODE_PRIVATE);
                        String MotorCycle_QuestionLoaded = GeneralSettings.getString("MotorCycle_QuestionLoaded", "False");
                        if(MotorCycle_QuestionLoaded.equals("False")){
                            initializer.SaveQuestionChoiceFromFile(context_1, "motor_cycle.txt", 1, 8, "ሞተር ሳይክል", "የሞተር ልዩነት");

                            SharedPreferences.Editor editor;
                            editor = GeneralSettings.edit();
                            editor.putString("MotorCycle_QuestionLoaded", "True");
                            editor.apply();
                        }
                    }
                }
            });

        }

        return convertView;
    }

    public void SetupProperLanguage(Context context) {
        SharedPreferences GeneralSettings = context_1.getSharedPreferences("GeneralSettings", MODE_PRIVATE);
        String languageSetting = GeneralSettings.getString("Language", "English");
        if(languageSetting.equals("Amharic"))
        {
            String language_code = "am";
            Resources res = context_1.getApplicationContext().getResources();
            // Change locale settings in the app.
            DisplayMetrics dm = res.getDisplayMetrics();
            android.content.res.Configuration conf = res.getConfiguration();
            conf.setLocale(new Locale(language_code.toLowerCase())); // API 17+ only.
            // Use conf.locale = new Locale(...) if targeting lower versions
            res.updateConfiguration(conf, dm);
        }
        else
        {
            String language_code = "en-US";
            Resources res = context_1.getApplicationContext().getResources();
            // Change locale settings in the app.
            DisplayMetrics dm = res.getDisplayMetrics();
            android.content.res.Configuration conf = res.getConfiguration();
            conf.setLocale(new Locale(language_code.toLowerCase())); // API 17+ only.
            // Use conf.locale = new Locale(...) if targeting lower versions
            res.updateConfiguration(conf, dm);
        }
    }
}