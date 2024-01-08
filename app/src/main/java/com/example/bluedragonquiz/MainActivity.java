package com.example.bluedragonquiz;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.accounts.AccountManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView dragonIntro, dragonevolution, contacts, removeads, settings, ranking;
    AppCompatButton btnStart;
    TextView text;

    int RC_SIGN_IN = 100;
    GoogleSignInClient googleSignInClient;


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

        //약관 설정> 개인정보수집 약관동의
        Intent intent = getIntent();
        settings = findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "약관(settings)", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });


        // 사용자 개발자에게 이메일을 통해 연락
        contacts = findViewById(R.id.contacts);
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] addresses = {"waraukuma@naver.com"}; //개발자 이메일
                String subject = "앱에 대한 문의 또는 건의사항";

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, addresses);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);

                try {
                    startActivity(Intent.createChooser(emailIntent, "이메일 보내기"));
                } catch (android.content.ActivityNotFoundException notFoundException) {
                    Toast.makeText(getApplicationContext(), "이메일 전송기능을 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //뒤로가기(onBackPressedCallback)
        this.getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
                ab.setMessage("앱을 종료하시겠습니까?");
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

        //랭킹> 구글계정 액서스권한 허용
        ranking = findViewById(R.id.ranking);
        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "순위", Toast.LENGTH_SHORT).show();
                // 구글 계정연동 권한선택 팝업
                googleSignInClient = GoogleSignIn.getClient(MainActivity.this,
                        new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build());
                Intent signInIntent = googleSignInClient.getSignInIntent();
                //startActivityForResult메서드를(팝업) RC_SIGN_IN(결과)
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    @Override //ranking 구글연동값 액티비티 처리결과
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult", "onActivityResult");
        if (requestCode == RC_SIGN_IN) {
            Log.e("RC_SIGN_IN", "RC_SIGN_IN");
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
            Log.e("handleSignInResult", "handleSignInResult");

        try {
//            GoogleSignInAccount googleSignInAccount = completedTask.getResult(ApiException.class);
            GoogleSignInAccount googleSignInAccount = completedTask.getResult();
            Log.e("로그인확인", "1");

            // 계정 연동 성공
            Toast.makeText(this, "계정 연동 성공", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, RankingActivity.class);
            startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
//            int statusCode = e.getStatusCode();
//
//            switch (statusCode) {
//                // 로그인 취소
//                case 13:
//                    Toast.makeText(this, "로그인 취소", Toast.LENGTH_SHORT).show();
//                    break;
//                // 기타 오류
//                default:
//                    Toast.makeText(this, "계정 연동 실패", Toast.LENGTH_SHORT).show();
//                    break;
//            }
        }
    }
}