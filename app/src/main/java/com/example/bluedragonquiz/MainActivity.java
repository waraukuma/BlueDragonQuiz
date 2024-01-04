package com.example.bluedragonquiz;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.accounts.AccountManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class MainActivity extends AppCompatActivity {

 ImageView dragonIntro, dragonevolution, contacts, removeads, settings, ranking;
 AppCompatButton btnStart;
 TextView text;

int RC_SIGN_IN = 100;
private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
               
        text = findViewById(R.id.text);


        dragonIntro = findViewById(R.id.dragonIntro);
        Animation ani = AnimationUtils.loadAnimation(MainActivity.this, R.anim.move2);
        dragonIntro.startAnimation(ani);
        
        //게임시작버튼----> 게임화면으로 이동
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


        
        //진화 아이콘(evolution)
        dragonevolution = findViewById(R.id.dragonevolution);
        dragonevolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "진화(evolution)", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, EvolutionActivity.class);
                startActivity(intent);
            }
        });

        //설정> 개인정보수집 약관동의
        Intent intent = getIntent();
        settings = findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "설정(settings)", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });


        //랭킹> 구글계정 액서스권한 허용
        ranking = findViewById(R.id.ranking);
        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "순위", Toast.LENGTH_SHORT).show();
                // Configure Google Sign-In // 이메일계정
                GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).requestEmail().build();
                mGoogleSignInClient = GoogleSignIn.getClient(MainActivity.this, googleSignInOptions);
                ranking.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signIn();
                    }
                });

                }
        });


        //뒤로가기(onBackPressedCallback)
        this.getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
                ab.setMessage("끝내고 싶어요?");
                ab.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                ab.setNegativeButton("아니오", null);
                ab.show();
            }
        });
    }

    private void signIn() {
        Intent signInIntent  = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // Google Sign-In was successful, authenticate with Firebase
            GoogleSignIn.getSignedInAccountFromIntent(data)
                    .addOnSuccessListener(account -> {
                        // Signed in successfully, show authenticated UI
                        GoogleSignInAccount googleAccount = account;
                        // TODO: Perform actions after signing in
                    })
                    .addOnFailureListener(e -> {
                        // Failed to sign in
                        // TODO: Handle failure
                    });

        }
    }
}