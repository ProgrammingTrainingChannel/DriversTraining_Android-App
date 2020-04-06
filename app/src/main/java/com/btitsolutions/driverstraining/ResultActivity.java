package com.btitsolutions.driverstraining;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.btitsolutions.driverstraining.Adapters.EvaluationTypeAdapter;
import com.btitsolutions.driverstraining.Adapters.QuestionAdapter;
import com.btitsolutions.driverstraining.Models.EvaluationTypeModel;
import com.btitsolutions.driverstraining.Utilities.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    ListView lstQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle bundle = getIntent().getExtras();
        String TotalQuestions = bundle.getString("TotalQuestions");
        String CorrectQuestions = bundle.getString("CorrectQuestions");
        String IncorrectQuestions = bundle.getString("IncorrectQuestions");
        ArrayList<String> IncorrectQuestionsArray = bundle.getStringArrayList("IncorrectQuestionsArray");

        TextView lblTotalQuestions = (TextView)findViewById(R.id.lblTotalQuestions);
        TextView lblCorrectQuestions = (TextView)findViewById(R.id.lblCorrectQuestions);
        TextView lblIncorrectQuestions = (TextView)findViewById(R.id.lblIncorrectQuestions);

        lblTotalQuestions.setText(TotalQuestions);
        lblCorrectQuestions.setText(CorrectQuestions);
        lblIncorrectQuestions.setText(IncorrectQuestions);

        DBHelper dbHelper = new DBHelper(this);
        QuestionAdapter questionAdapter = new QuestionAdapter(this, IncorrectQuestionsArray);
        lstQuestion = (ListView)findViewById(R.id.lstQuestion);
        lstQuestion.setAdapter(questionAdapter);
    }
}
