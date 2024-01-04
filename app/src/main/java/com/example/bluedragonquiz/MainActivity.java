package com.example.bluedragonquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

 ImageView dragonIntro, dragonevolution, contacts, removeads, settings, ranking;
 AppCompatButton btnStart;
 TextView text;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);


        dragonIntro = findViewById(R.id.dragonIntro);
        Animation ani = AnimationUtils.loadAnimation(MainActivity.this, R.anim.move2);
        dragonIntro.startAnimation(ani);

        btnStart = findViewById(R.id.btnStart);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), StageActivity.class);
                startActivity(intent);
//                final  int StageCount;
//                intent.putExtra("StageCount", StageCount);
            }
        });


        // 진화 아이콘(evolution)
        dragonevolution = findViewById(R.id.dragonevolution);
        dragonevolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                컨텍스트를 인자로 전달해야 합니다. 여기서는 액티비티의 컨텍스트를 사용합니다.
//                Toast.makeText(getApplicationContext(), "안녕하세요, 여기에 메시지를 입력하세요!", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "진화(evolution)", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, EvolutionActivity.class);
                startActivity(intent);
            }
        });
    }
}