package com.btitsolutions.driverstraining;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.btitsolutions.driverstraining.Adapters.EvaluationCategoryAdapter;
import com.btitsolutions.driverstraining.Models.EvaluationCategoryModel;
import com.btitsolutions.driverstraining.Utilities.DBHelper;
import com.btitsolutions.driverstraining.Utilities.Initializer;

import java.util.List;

public class EvaluationCategoryActivity extends AppCompatActivity {

    ListView lstEvaluationCategory;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_category);

        Initializer initializer = new Initializer();
        initializer.LoadEvaluationCategory(this);
        context = this;

        DBHelper dbHelper = new DBHelper(this);
        final List<EvaluationCategoryModel> evaluationCategoryModels = dbHelper.getAllEvaluationCategories();
        EvaluationCategoryAdapter evaluationCategoryAdapter = new EvaluationCategoryAdapter(this, evaluationCategoryModels);
        lstEvaluationCategory = (ListView)findViewById(R.id.lstEvaluationCategory);
        lstEvaluationCategory.setAdapter(evaluationCategoryAdapter);

        lstEvaluationCategory.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(android.widget.AdapterView<?> parent, View view, int position, long id)
               {
                   Intent intent = new Intent(context, EvaluationTypeActivity.class);
                   Bundle bundle = new Bundle();
                   bundle.putString("CategoryCode", evaluationCategoryModels.get(position).getCode());
                   intent.putExtras(bundle);

                   startActivity(intent);
               }
           }
        );
    }
}
