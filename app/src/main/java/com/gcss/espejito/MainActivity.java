package com.gcss.espejito;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final MediaPlayer clapMP = MediaPlayer.create(this, R.raw.clapping);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Me programaron para decir: ¡TÚ! :D",
                        Toast.LENGTH_LONG).show();
                clapMP.start();
                front(); // Inicia la cámara frontal.

            }
        });
    }

    private void front() {
        Intent cam_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cam_intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
        startActivityForResult(cam_intent, 100);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Selección de items del menú.
        switch (item.getItemId()) {
            case R.id.action_about:
                acercaDe();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void acercaDe() {
        Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/gcss90"));
        startActivity(browse);
    }
}
