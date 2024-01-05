package com.example.bluedragonquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class EvolutionActivity extends AppCompatActivity {
    VideoView evolution1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evolution);

       evolution1 = findViewById(R.id.evolution1);

       Intent intent = getIntent();

        // 동영상 재생
        MediaController controller = new MediaController(this);
        evolution1.setMediaController(controller);
        evolution1.setOnCompletionListener(completionListener);
        evolution1.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.evolution1));
        evolution1.start();
    }

    MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
//            startMainActivity();
        }
    };
}