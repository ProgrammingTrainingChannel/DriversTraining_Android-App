package com.btitsolutions.driverstraining;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtUniqueCode, txtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUniqueCode = (EditText)findViewById(R.id.txtUniqueCode);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        SharedPreferences GeneralSettings = getSharedPreferences("GeneralSettings", MODE_PRIVATE);
        String SecurityGUID = GeneralSettings.getString("SecurityGUID", "-");
        String EncryptedSecurityCode = GeneralSettings.getString("EncryptedSecurityCode", "-");

        if(SecurityGUID.equals("-")){
            SecurityGUID = UUID.randomUUID().toString();

            SharedPreferences.Editor editor;
            editor = GeneralSettings.edit();
            editor.putString("SecurityGUID", SecurityGUID);

            editor.apply();
        }

        txtUniqueCode.setText(SecurityGUID);
        txtPassword.setText(EncryptedSecurityCode);
    }

    public String GenerateMD5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void onClick(View view) {
        SharedPreferences GeneralSettings = getSharedPreferences("GeneralSettings", MODE_PRIVATE);
        String SecurityGUID = GeneralSettings.getString("SecurityGUID", "-");
        String EncryptedSecurityCode = GeneralSettings.getString("EncryptedSecurityCode", "-");
        SharedPreferences.Editor editor;
        editor = GeneralSettings.edit();

        if(EncryptedSecurityCode.equals("-")){
            if(txtPassword.getText().toString().equals("Me@ProgrammingCamp")){
                editor.putString("EncryptedSecurityCode", GenerateMD5(SecurityGUID + "-" + "Me@ProgrammingCamp"));
                editor.apply();

                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Password is not correct, Please call 0911 84 20 52", Toast.LENGTH_LONG).show();
            }
        }
        else{
            if(EncryptedSecurityCode.equals(txtPassword.getText().toString())){
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Password is not correct, Please call 0911 84 20 52", Toast.LENGTH_LONG).show();
            }
        }
    }
}
