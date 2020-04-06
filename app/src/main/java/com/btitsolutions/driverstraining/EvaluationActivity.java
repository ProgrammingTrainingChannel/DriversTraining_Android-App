package com.btitsolutions.driverstraining;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.btitsolutions.driverstraining.Adapters.ChoiceAdapter;
import com.btitsolutions.driverstraining.Adapters.EvaluationTypeAdapter;
import com.btitsolutions.driverstraining.Models.ChoiceModel;
import com.btitsolutions.driverstraining.Models.EvaluationTypeModel;
import com.btitsolutions.driverstraining.Models.QuestionModel;
import com.btitsolutions.driverstraining.Utilities.DBHelper;
import com.btitsolutions.driverstraining.Utilities.Initializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class EvaluationActivity extends AppCompatActivity implements View.OnClickListener{

    String EvaluationType, CategoryID, TypeID, btnOriginalText;
    TextView lblQuestionText;
    TextView lblAnswer;
    ListView listViewChoices;

    Button btnNextQuestion;
    List<QuestionModel> questionModels;
    int currentQuestionIndex = 0;
    int correctQuestionsCounter = 0;
    int inCorrectQuestionsCounter = 0;
    boolean isAnswered = false;

    ArrayList<String> IncorrectQuestionsArray = new ArrayList<String>();

    ImageView imageViewQuestion;
    Context context;
    //private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);
        context = this;

        try{
            btnNextQuestion = (Button)findViewById(R.id.btnNextQuestion);
            btnNextQuestion.setOnClickListener(this);
            btnOriginalText = btnNextQuestion.getText().toString();

            Bundle bundle = getIntent().getExtras();
            EvaluationType = bundle.getString("EvaluationType");
            CategoryID = bundle.getString("CategoryID");
            TypeID = bundle.getString("TypeID");
            int QuestionNumber = Integer.parseInt(bundle.getString("QuestionNumber"));

            imageViewQuestion = (ImageView)findViewById(R.id.imageViewQuestion);
            lblQuestionText = (TextView) findViewById(R.id.lblQuestionText);
            lblAnswer = (TextView)findViewById(R.id.lblAnswer);

            DBHelper dbHelper = new DBHelper(this);
            questionModels = dbHelper.getQuestions(QuestionNumber, Integer.parseInt(CategoryID), Integer.parseInt(TypeID));
            btnNextQuestion.setText(btnOriginalText + " (" + (currentQuestionIndex + 1) + " of " + questionModels.size() + ")");
            lblQuestionText.setText(questionModels.get(currentQuestionIndex).getQuestionText());

            if(lblQuestionText.getText().toString().contains("(")){
                String filename = lblQuestionText.getText().subSequence(lblQuestionText.getText().toString().indexOf("(")+1, lblQuestionText.getText().toString().indexOf(")")).toString();
                imageViewQuestion.setImageBitmap(getBitmapFromAssets(filename + ".jpg"));
            }
            else{
                imageViewQuestion.setImageBitmap(null);
            }

            List<ChoiceModel> choiceModels = dbHelper.getChoices(questionModels.get(currentQuestionIndex).getID());

            final ChoiceAdapter choiceAdapter = new ChoiceAdapter(this, choiceModels);
            listViewChoices = (ListView)findViewById(R.id.listViewChoices);
            listViewChoices.setAdapter(choiceAdapter);

            listViewChoices.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
                     @Override
                     public void onItemClick(android.widget.AdapterView<?> parent, View view, int position, long id)
                     {
                         if(isAnswered == false){
                             isAnswered = true;

                             TextView lblChoiceText = (TextView)view.findViewById(R.id.lblChoiceText);
                             if(lblChoiceText.getTag().toString().equals("true")){
                                 if(EvaluationType.equals("Training")) {
                                     lblAnswer.setText("በትክክል ተመልሷል");
                                 }
                                 correctQuestionsCounter++;
                             }
                             else{
                                 if(EvaluationType.equals("Training")) {
                                     DBHelper dbHelper = new DBHelper(context);
                                     List<ChoiceModel> localChoiceModels = dbHelper.getChoices(questionModels.get(currentQuestionIndex).getID());
                                     for (int i=0; i < localChoiceModels.size(); i++){
                                         if (localChoiceModels.get(i).getIsAnswer()){
                                             String questionWithAnswer = questionModels.get(currentQuestionIndex).getQuestionText() + ":: መልሱ: " + localChoiceModels.get(i).getChoiceText();
                                             IncorrectQuestionsArray.add(questionWithAnswer);
                                             break;
                                         }
                                     }
                                     lblAnswer.setText("በትክክል አልተመለሰም");
                                 }
                                 inCorrectQuestionsCounter++;
                             }
                         }
                     }
                 }
            );
        }
        catch(Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public Bitmap getBitmapFromAssets(String fileName) throws IOException {
        try{
            AssetManager assetManager = getAssets();
            InputStream istr = assetManager.open(fileName.trim());

            return BitmapFactory.decodeStream(istr);
        }
        catch(Exception ex){
            return null;
        }
    }

    @Override
    public void onClick(View view) {
        if(isAnswered){
            isAnswered = false;
            if(currentQuestionIndex < questionModels.size() - 1){
                DBHelper dbHelper = new DBHelper(this);
                currentQuestionIndex++;

                lblQuestionText.setText(questionModels.get(currentQuestionIndex).getQuestionText());
                if(lblQuestionText.getText().toString().contains("(")){
                    String filename = lblQuestionText.getText().subSequence(lblQuestionText.getText().toString().indexOf("(")+1, lblQuestionText.getText().toString().indexOf(")")).toString();
                    try {
                        imageViewQuestion.setImageBitmap(getBitmapFromAssets(filename + ".jpg"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    imageViewQuestion.setImageBitmap(null);
                }

                btnNextQuestion.setText(btnOriginalText + " (" + (currentQuestionIndex + 1) + " ከ " + questionModels.size() + ")");

                List<ChoiceModel> choiceModels = new ArrayList<>();
                choiceModels = dbHelper.getChoices(questionModels.get(currentQuestionIndex).getID());

                ChoiceAdapter choiceAdapter = new ChoiceAdapter(this, choiceModels);
                listViewChoices.setAdapter(choiceAdapter);

                lblAnswer.setText("");
            }
            else{
                Intent intent = new Intent(context, ResultActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("TotalQuestions", String.valueOf(currentQuestionIndex + 1));
                bundle.putString("CorrectQuestions", String.valueOf(correctQuestionsCounter));
                bundle.putString("IncorrectQuestions", String.valueOf(inCorrectQuestionsCounter));
                bundle.putStringArrayList("IncorrectQuestionsArray", IncorrectQuestionsArray);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        }
        else{
            Toast.makeText(context, "እባክዎ በቅድሚያ የዚህን ጥያቄ መልስ ይምረጡ", Toast.LENGTH_LONG).show();
        }
    }
}
