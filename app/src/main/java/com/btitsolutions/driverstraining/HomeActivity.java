package com.btitsolutions.driverstraining;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnEvaluation, btnSetting, btnAbout;
    TextView lblShowHelpVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lblShowHelpVideo = (TextView) findViewById(R.id.lblShowHelpVideo);
        lblShowHelpVideo.setOnClickListener(this);

        btnEvaluation = (Button) findViewById(R.id.btnEvaluation);
        btnSetting = (Button) findViewById(R.id.btnSetting);
        btnAbout = (Button) findViewById(R.id.btnAbout);

        btnEvaluation.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
        btnAbout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btnEvaluation.getId())
        {
            Intent intent=new Intent(this, EvaluationCategoryActivity.class);
            startActivity(intent);
        }
        else if (view.getId() == btnSetting.getId())
        {
            Intent intent=new Intent(this, SettingActivity.class);
            startActivity(intent);
        }
        else if (view.getId() == btnAbout.getId())
        {
            Intent intent=new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
        else if (view.getId() == lblShowHelpVideo.getId())
        {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCQzza0-D8IF_WbMj2WQSrFQ?sub_confirmation=1"));
            startActivity(browserIntent);
        }
    }
}
