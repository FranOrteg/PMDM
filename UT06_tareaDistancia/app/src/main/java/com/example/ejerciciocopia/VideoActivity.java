package com.example.ejerciciocopia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {

    private VideoView vwReproductor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        vwReproductor=(VideoView)findViewById(R.id.vwReproductor);


        // Obtener el ID del recurso del video desde el Intent
        Intent intent = getIntent();
        int videoResId = intent.getIntExtra("urlVideo", -1);

        // Cargar el recurso del video
        if (videoResId != -1) {
            String videoPath = "file:///android_res/raw/" + getResources().getResourceEntryName(videoResId) + ".mp4";
            //webView.loadUrl(videoPath);  // Cargar el video en WebView


            String rutaVideo = "android.resource://com.example.ejerciciocopia/" + videoResId;



            Uri uri = Uri.parse(rutaVideo);

            vwReproductor.setVideoURI(uri);
            MediaController mediaController = new MediaController(this);

            mediaController.setAnchorView(vwReproductor);

            vwReproductor.setMediaController(mediaController);
            vwReproductor.start();
        }
    }
}
