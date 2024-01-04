package com.example.bluedragonquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;


public class SettingActivity extends AppCompatActivity {

    CheckBox checkbox1, checkbox2;
    AppCompatButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Intent intent = getIntent();

        checkbox1 = findViewById(R.id.checkbox1);
        checkbox2 = findViewById(R.id.checkbox2);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkbox1.isChecked() && checkbox2.isChecked()){
                    Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SettingActivity.this, "체크박스를 모두 선택해 주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}