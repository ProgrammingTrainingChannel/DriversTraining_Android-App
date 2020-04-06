package com.btitsolutions.driverstraining;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.btitsolutions.driverstraining.Adapters.EvaluationCategoryAdapter;
import com.btitsolutions.driverstraining.Adapters.EvaluationTypeAdapter;
import com.btitsolutions.driverstraining.Models.EvaluationCategoryModel;
import com.btitsolutions.driverstraining.Models.EvaluationTypeModel;
import com.btitsolutions.driverstraining.Utilities.DBHelper;
import com.btitsolutions.driverstraining.Utilities.Initializer;

import java.util.List;

import static com.btitsolutions.driverstraining.R.id.lstEvaluationCategory;

public class EvaluationTypeActivity extends AppCompatActivity {

    ListView lstEvaluationType;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_type);

        context = this;

        Bundle bundle = getIntent().getExtras();
        final String CategoryCode = bundle.getString("CategoryCode");

        Initializer initializer = new Initializer();
        initializer.LoadEvaluationType(this, CategoryCode);

        DBHelper dbHelper = new DBHelper(this);
        final List<EvaluationTypeModel> evaluationTypeModels = dbHelper.getAllEvaluationTypes(CategoryCode);
        EvaluationTypeAdapter evaluationTypeAdapter = new EvaluationTypeAdapter(this, evaluationTypeModels);
        lstEvaluationType = (ListView)findViewById(R.id.lstEvaluationType);
        lstEvaluationType.setAdapter(evaluationTypeAdapter);

        lstEvaluationType.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
                 @Override
                 public void onItemClick(android.widget.AdapterView<?> parent, View view, final int position, long id)
                 {
                     final String TypeID = evaluationTypeModels.get(position).getCode();
                     ChooseEvaluationTypeDialog(evaluationTypeModels.get(position).getCategoryCode(), TypeID);
                 }
             }
        );
    }

    private void LoadQuestionForFirstTime(String questionType){
        Initializer initializer = new Initializer();

        if(questionType.equals("Behaviour")){
            SharedPreferences GeneralSettings = getSharedPreferences("GeneralSettings", MODE_PRIVATE);
            String Behaviour_QuestionLoaded = GeneralSettings.getString("Behaviour_QuestionLoaded", "False");
            if(Behaviour_QuestionLoaded.equals("False")){
                SharedPreferences.Editor editor;
                editor = GeneralSettings.edit();
                editor.putString("Behaviour_QuestionLoaded", "True");
                editor.apply();

                initializer.SaveQuestionChoiceFromFile(this, "behavior.txt", 2, 1, "አውቶሞቢል", "ስነምግባር");
            }
        }
        else if(questionType.equals("Communication")){
            SharedPreferences GeneralSettings = getSharedPreferences("GeneralSettings", MODE_PRIVATE);
            String Communication_QuestionLoaded = GeneralSettings.getString("Communication_QuestionLoaded", "False");
            if(Communication_QuestionLoaded.equals("False")){
                SharedPreferences.Editor editor;
                editor = GeneralSettings.edit();
                editor.putString("Communication_QuestionLoaded", "True");
                editor.apply();

                initializer.SaveQuestionChoiceFromFile(this, "communication.txt", 2, 2, "አውቶሞቢል", "ተግባቦት");
            }
        }
        else if(questionType.equals("DrivingSilit")){
            SharedPreferences GeneralSettings = getSharedPreferences("GeneralSettings", MODE_PRIVATE);
            String DrivingSilit_QuestionLoaded = GeneralSettings.getString("DrivingSilit_QuestionLoaded", "False");
            if(DrivingSilit_QuestionLoaded.equals("False")){
                SharedPreferences.Editor editor;
                editor = GeneralSettings.edit();
                editor.putString("DrivingSilit_QuestionLoaded", "True");
                editor.apply();

                initializer.SaveQuestionChoiceFromFile(this, "driving_silit.txt", 2, 3, "አውቶሞቢል", "የማሽከርከር ስልት");
            }
        }
        else if(questionType.equals("Emergency")){
            SharedPreferences GeneralSettings = getSharedPreferences("GeneralSettings", MODE_PRIVATE);
            String Emergency_QuestionLoaded = GeneralSettings.getString("Emergency_QuestionLoaded", "False");
            if(Emergency_QuestionLoaded.equals("False")){
                SharedPreferences.Editor editor;
                editor = GeneralSettings.edit();
                editor.putString("Emergency_QuestionLoaded", "True");
                editor.apply();

                initializer.SaveQuestionChoiceFromFile(this, "emergency.txt", 2, 4, "አውቶሞቢል", "የአደጋ ምላሽ አሰጣጥ");
            }
        }
        else if(questionType.equals("Law")){
            SharedPreferences GeneralSettings = getSharedPreferences("GeneralSettings", MODE_PRIVATE);
            String Law_QuestionLoaded = GeneralSettings.getString("Law_QuestionLoaded", "False");
            if(Law_QuestionLoaded.equals("False")){
                SharedPreferences.Editor editor;
                editor = GeneralSettings.edit();
                editor.putString("Law_QuestionLoaded", "True");
                editor.apply();

                initializer.SaveQuestionChoiceFromFile(this, "law.txt", 2, 5, "አውቶሞቢል", "የመንገድ ስነስርአት");
            }
        }
        else if(questionType.equals("YeguzoMerega")){
            SharedPreferences GeneralSettings = getSharedPreferences("GeneralSettings", MODE_PRIVATE);
            String YeguzoMerega_QuestionLoaded = GeneralSettings.getString("YeguzoMerega_QuestionLoaded", "False");
            if(YeguzoMerega_QuestionLoaded.equals("False")){
                SharedPreferences.Editor editor;
                editor = GeneralSettings.edit();
                editor.putString("YeguzoMerega_QuestionLoaded", "True");
                editor.apply();

                initializer.SaveQuestionChoiceFromFile(this, "yeguzo_merega.txt", 2, 6, "አውቶሞቢል", "የጉዞ መረጃ");
            }
        }
        else if(questionType.equals("Technic")){
            SharedPreferences GeneralSettings = getSharedPreferences("GeneralSettings", MODE_PRIVATE);
            String Technic_QuestionLoaded = GeneralSettings.getString("Technic_QuestionLoaded", "False");
            if(Technic_QuestionLoaded.equals("False")){
                SharedPreferences.Editor editor;
                editor = GeneralSettings.edit();
                editor.putString("Technic_QuestionLoaded", "True");
                editor.apply();

                initializer.SaveQuestionChoiceFromFile(this, "technic.txt", 2, 7, "አውቶሞቢል", "ቴክኒክ");
            }
        }
        else if(questionType.equals("DryCargo")){
            SharedPreferences GeneralSettings = getSharedPreferences("GeneralSettings", MODE_PRIVATE);
            String DryCargo_QuestionLoaded = GeneralSettings.getString("DryCargo_QuestionLoaded", "False");
            if(DryCargo_QuestionLoaded.equals("False")){
                SharedPreferences.Editor editor;
                editor = GeneralSettings.edit();
                editor.putString("DryCargo_QuestionLoaded", "True");
                editor.apply();

                initializer.SaveQuestionChoiceFromFile(this, "dry_cargo.txt", 5, 11, "ደረቅ ጭነት 1, 2 እና 3", "የደረቅ ጭነት ልዩነት");
            }
        }
        else if(questionType.equals("LiquidCargo")){
            SharedPreferences GeneralSettings = getSharedPreferences("GeneralSettings", MODE_PRIVATE);
            String LiquidCargo_QuestionLoaded = GeneralSettings.getString("LiquidCargo_QuestionLoaded", "False");
            if(LiquidCargo_QuestionLoaded.equals("False")){
                SharedPreferences.Editor editor;
                editor = GeneralSettings.edit();
                editor.putString("LiquidCargo_QuestionLoaded", "True");
                editor.apply();

                initializer.SaveQuestionChoiceFromFile(this, "liquid_cargo.txt", 6, 10, "ፈሳሽ ጭነት 1 እና 2", "የፈሳሽ ጭነት ልዩነት");
            }
        }
        else if(questionType.equals("LuggagePassenger_1")){
            SharedPreferences GeneralSettings = getSharedPreferences("GeneralSettings", MODE_PRIVATE);
            String LuggagePassenger_1_QuestionLoaded = GeneralSettings.getString("LuggagePassenger_1_QuestionLoaded", "False");
            if(LuggagePassenger_1_QuestionLoaded.equals("False")){
                SharedPreferences.Editor editor;
                editor = GeneralSettings.edit();
                editor.putString("LuggagePassenger_1_QuestionLoaded", "True");
                editor.apply();

                initializer.SaveQuestionChoiceFromFile(this, "luggage_passenger.txt", 3, 9, "ታክሲ 1 እና 2", "ታክሲና የህዝብ ጭነት ልዩነት");
            }
        }
        else if(questionType.equals("LuggagePassenger_2")){
            SharedPreferences GeneralSettings = getSharedPreferences("GeneralSettings", MODE_PRIVATE);
            String LuggagePassenger_2_QuestionLoaded = GeneralSettings.getString("LuggagePassenger_2_QuestionLoaded", "False");
            if(LuggagePassenger_2_QuestionLoaded.equals("False")){
                SharedPreferences.Editor editor;
                editor = GeneralSettings.edit();
                editor.putString("LuggagePassenger_2_QuestionLoaded", "True");
                editor.apply();

                initializer.SaveQuestionChoiceFromFile(this, "luggage_passenger.txt", 3, 8, "ህዝብ 1 እና 2", "ታክሲና የህዝብ ጭነት ልዩነት");
            }
        }
        else if(questionType.equals("MotorCycle")){
            SharedPreferences GeneralSettings = getSharedPreferences("GeneralSettings", MODE_PRIVATE);
            String MotorCycle_QuestionLoaded = GeneralSettings.getString("MotorCycle_QuestionLoaded", "False");
            if(MotorCycle_QuestionLoaded.equals("False")){
                SharedPreferences.Editor editor;
                editor = GeneralSettings.edit();
                editor.putString("MotorCycle_QuestionLoaded", "True");
                editor.apply();

                initializer.SaveQuestionChoiceFromFile(this, "motor_cycle.txt", 1, 8, "ሞተር ሳይክል", "የሞተር ልዩነት");
            }
        }
    }

    public void ChooseEvaluationTypeDialog(final String categoryID, final String typeID) {
        final Dialog dialog = new Dialog(context); // Context, this, etc.
        dialog.setContentView(R.layout.evaluation_type_template);
        dialog.setCancelable(false);
        dialog.show();

        final EditText txtQuestionNumber = (EditText)dialog.findViewById(R.id.txtQuestionNumber);

        final Button btnTraining = (Button) dialog.findViewById(R.id.btnTraining);
        final Button btnExam = (Button) dialog.findViewById(R.id.btnExam);

        btnTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView imgLoading = (ImageView)dialog.findViewById(R.id.imgLoading);
                imgLoading.setVisibility(View.VISIBLE);

                txtQuestionNumber.setEnabled(false);
                btnTraining.setEnabled(false);
                btnExam.setEnabled(false);

                //Load questions for the first time
                Thread questionLoadThread = new Thread(){
                    public void run(){
                        try{
                            if(Integer.parseInt(typeID) == 1){
                                LoadQuestionForFirstTime("Behaviour");
                            }
                            else if(Integer.parseInt(typeID) == 2){
                                LoadQuestionForFirstTime("Communication");
                            }
                            else if(Integer.parseInt(typeID) == 3){
                                LoadQuestionForFirstTime("DrivingSilit");
                            }
                            else if(Integer.parseInt(typeID) == 4){
                                LoadQuestionForFirstTime("Emergency");
                            }
                            else if(Integer.parseInt(typeID) == 5){
                                LoadQuestionForFirstTime("Law");
                            }
                            else if(Integer.parseInt(typeID) == 6){
                                LoadQuestionForFirstTime("YeguzoMerega");
                            }
                            else if(Integer.parseInt(typeID) == 7){
                                LoadQuestionForFirstTime("Technic");
                            }

                            else if(Integer.parseInt(typeID) == 8){
                                LoadQuestionForFirstTime("MotorCycle");
                            }
                            else if(Integer.parseInt(typeID) == 9){
                                LoadQuestionForFirstTime("LuggagePassenger_1");
                                LoadQuestionForFirstTime("LuggagePassenger_2");
                            }
                            else if(Integer.parseInt(typeID) == 10){
                                LoadQuestionForFirstTime("LiquidCargo");
                            }
                            else if(Integer.parseInt(typeID) == 11){
                                LoadQuestionForFirstTime("DryCargo");
                            }
                        }
                        catch(Exception e){
                            e.printStackTrace();
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        finally{
                            Intent intent=new Intent(context, EvaluationActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("EvaluationType", "Training");
                            bundle.putString("CategoryID", categoryID);
                            bundle.putString("TypeID", typeID);
                            bundle.putString("QuestionNumber", txtQuestionNumber.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);

                            dialog.dismiss();
                        }
                    }
                };
                questionLoadThread.start();
            }
        });

        btnExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView imgLoading = (ImageView)dialog.findViewById(R.id.imgLoading);
                imgLoading.setVisibility(View.VISIBLE);

                txtQuestionNumber.setEnabled(false);
                btnTraining.setEnabled(false);
                btnExam.setEnabled(false);

                //Load questions for the first time
                Thread questionLoadThread = new Thread(){
                    public void run(){
                        try{
                            if(Integer.parseInt(typeID) == 1){
                                LoadQuestionForFirstTime("Behaviour");
                            }
                            else if(Integer.parseInt(typeID) == 2){
                                LoadQuestionForFirstTime("Communication");
                            }
                            else if(Integer.parseInt(typeID) == 3){
                                LoadQuestionForFirstTime("DrivingSilit");
                            }
                            else if(Integer.parseInt(typeID) == 4){
                                LoadQuestionForFirstTime("Emergency");
                            }
                            else if(Integer.parseInt(typeID) == 5){
                                LoadQuestionForFirstTime("Law");
                            }
                            else if(Integer.parseInt(typeID) == 6){
                                LoadQuestionForFirstTime("YeguzoMerega");
                            }
                            else if(Integer.parseInt(typeID) == 7){
                                LoadQuestionForFirstTime("Technic");
                            }

                            else if(Integer.parseInt(typeID) == 8){
                                LoadQuestionForFirstTime("MotorCycle");
                            }
                            else if(Integer.parseInt(typeID) == 9){
                                LoadQuestionForFirstTime("LuggagePassenger_1");
                                LoadQuestionForFirstTime("LuggagePassenger_2");
                            }
                            else if(Integer.parseInt(typeID) == 10){
                                LoadQuestionForFirstTime("LiquidCargo");
                            }
                            else if(Integer.parseInt(typeID) == 11){
                                LoadQuestionForFirstTime("DryCargo");
                            }
                        }
                        catch(Exception e){
                            e.printStackTrace();
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        finally{
                            Intent intent=new Intent(context, EvaluationActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("EvaluationType", "Exam");
                            bundle.putString("CategoryID", categoryID);
                            bundle.putString("TypeID", typeID);
                            bundle.putString("QuestionNumber", txtQuestionNumber.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);

                            dialog.dismiss();
                        }
                    }
                };
                questionLoadThread.start();
            }
        });
    }

}
