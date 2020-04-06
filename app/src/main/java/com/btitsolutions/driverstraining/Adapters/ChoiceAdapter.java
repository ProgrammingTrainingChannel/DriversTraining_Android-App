package com.btitsolutions.driverstraining.Adapters;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.btitsolutions.driverstraining.Models.ChoiceModel;
import com.btitsolutions.driverstraining.Models.EvaluationTypeModel;
import com.btitsolutions.driverstraining.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Bereket on 5/12/2017.
 */

public class ChoiceAdapter extends BaseAdapter {

    private Activity context_1;

    private List<ChoiceModel> choiceModels;

    public ChoiceAdapter(Activity context,
                         List<ChoiceModel> choiceModels) {
        context_1 = context;
        this.choiceModels = choiceModels;
    }

    @Override
    public int getCount() {
        return choiceModels.size();
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
        TextView lblChoiceText;
        ImageView imageViewChoice;

        if (convertView == null) {
            convertView = LayoutInflater.from(context_1).inflate(
                    R.layout.choice_list, null);
            //convertView = new ViewHolder();
            lblChoiceText = (TextView) convertView.findViewById(R.id.lblChoiceText);
            imageViewChoice = (ImageView) convertView.findViewById(R.id.imageViewChoice);

            lblChoiceText.setTag(choiceModels.get(position).getIsAnswer());
            String text = choiceModels.get(position).getChoiceText();

            String lastThreeChars = text.substring(text.length()-3);

            if((lastThreeChars.trim().contains("- 1")) || (lastThreeChars.trim().contains("-1"))){
                lblChoiceText.setText(text.replace(lastThreeChars, ""));
            }
            else{
                lblChoiceText.setText(text);
            }

            if(lblChoiceText.getText().toString().contains("(")){
                String filename = lblChoiceText.getText().subSequence(lblChoiceText.getText().toString().indexOf("(")+1, lblChoiceText.getText().toString().indexOf(")")).toString();
                try {
                    imageViewChoice.setImageBitmap(getBitmapFromAssets(filename + ".jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                imageViewChoice.setImageBitmap(null);
            }
        }

        return convertView;
    }

    public Bitmap getBitmapFromAssets(String fileName) throws IOException {
        try{
            AssetManager assetManager = context_1.getAssets();
            InputStream istr = assetManager.open(fileName.trim());

            return BitmapFactory.decodeStream(istr);
        }
        catch(Exception ex){
            return null;
        }
    }

}