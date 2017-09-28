package com.cn.mediaplayer01;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Button mPlayButton;
    private Button mPauseButton;
    private Button mStopButton;
    private MediaPlayer mMediaPlayer;
    private final static int REQUEST_CODE=9527;
    private ClickListenerImpl mClickListenerImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mMediaPlayer = new MediaPlayer();
        mClickListenerImpl=new ClickListenerImpl();
        mPlayButton = (Button) findViewById(R.id.play);
        mPauseButton = (Button) findViewById(R.id.pause);
        mStopButton= (Button) findViewById(R.id.stop);
        mPlayButton.setOnClickListener(mClickListenerImpl);
        mPauseButton.setOnClickListener(mClickListenerImpl);
        mStopButton.setOnClickListener(mClickListenerImpl);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{ Manifest.permission. WRITE_EXTERNAL_STORAGE }, REQUEST_CODE);
        } else {
            initMediaPlayer();
        }
    }

    private void initMediaPlayer() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "test.mp3");
            mMediaPlayer.setDataSource(file.getPath());
            mMediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private class ClickListenerImpl implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.play:
                    if (!mMediaPlayer.isPlaying()) {
                        mMediaPlayer.start();
                    }
                    break;
                case R.id.pause:
                    if (mMediaPlayer.isPlaying()) {
                        mMediaPlayer.pause();
                    }
                    break;
                case R.id.stop:
                    if (mMediaPlayer.isPlaying()) {
                        mMediaPlayer.reset();
                        initMediaPlayer();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initMediaPlayer();
                } else {
                    Toast.makeText(this, "您未授权", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    protected void onDestroy() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
        super.onDestroy();
    }

}
