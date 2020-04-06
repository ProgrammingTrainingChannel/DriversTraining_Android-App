package com.btitsolutions.driverstraining.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.btitsolutions.driverstraining.Models.EvaluationCategoryModel;
import com.btitsolutions.driverstraining.Models.EvaluationTypeModel;
import com.btitsolutions.driverstraining.R;
import com.btitsolutions.driverstraining.Utilities.DBHelper;

import java.util.List;

/**
 * Created by Bereket on 5/12/2017.
 */

public class EvaluationTypeAdapter extends BaseAdapter {

    private Activity context_1;

    private List<EvaluationTypeModel> evaluationTypeModels;

    public EvaluationTypeAdapter(Activity context,
                                 List<EvaluationTypeModel> evaluationTypeModels) {
        context_1 = context;
        this.evaluationTypeModels = evaluationTypeModels;
    }

    @Override
    public int getCount() {
        return evaluationTypeModels.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView lblName;

        if (convertView == null) {
            convertView = LayoutInflater.from(context_1).inflate(
                    R.layout.evaluation_category_list, null);
            //convertView = new ViewHolder();
            DBHelper dbHelper = new DBHelper(context_1);
            int questionCounter = dbHelper.getQuestionCount(Integer.parseInt(evaluationTypeModels.get(position).getCategoryCode()), Integer.parseInt(evaluationTypeModels.get(position).getCode()));

            lblName = (TextView) convertView.findViewById(R.id.lblName);

            lblName.setTag(evaluationTypeModels.get(position).getCode());
            lblName.setText(evaluationTypeModels.get(position).getName() + " (" + questionCounter + ")");
        }

        return convertView;
    }
}