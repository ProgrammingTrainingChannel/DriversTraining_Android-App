package com.btitsolutions.driverstraining.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.btitsolutions.driverstraining.Models.ChoiceModel;
import com.btitsolutions.driverstraining.Models.QuestionModel;
import com.btitsolutions.driverstraining.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bereket on 5/12/2017.
 */

public class QuestionAdapter extends BaseAdapter {

    private Activity context_1;

    private ArrayList<String> questionModels;

    public QuestionAdapter(Activity context,
                           ArrayList<String> questionModels) {
        context_1 = context;
        this.questionModels = questionModels;
    }

    @Override
    public int getCount() {
        return questionModels.size();
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
        TextView lblQuestionText;

        if (convertView == null) {
            convertView = LayoutInflater.from(context_1).inflate(
                    R.layout.question_list, null);
            //convertView = new ViewHolder();
            lblQuestionText = (TextView) convertView.findViewById(R.id.lblQuestionText);

            lblQuestionText.setText(questionModels.get(position));
        }

        return convertView;
    }
}