package com.cn.videoview01;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;
import java.io.File;

public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_CODE=9527;
    private VideoView mVideoView;
    private Button mPlayButton;
    private Button mPauseButton;
    private Button mReplayButton;
    private ClickListenerImpl mClickListenerImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mVideoView = (VideoView) findViewById(R.id.videoView);
        mClickListenerImpl=new ClickListenerImpl();
        mPlayButton= (Button) findViewById(R.id.play);
        mPlayButton.setOnClickListener(mClickListenerImpl);
        mPauseButton= (Button) findViewById(R.id.pause);
        mPauseButton.setOnClickListener(mClickListenerImpl);
        mReplayButton= (Button) findViewById(R.id.replay);
        mReplayButton.setOnClickListener(mClickListenerImpl);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{ Manifest.permission. WRITE_EXTERNAL_STORAGE }, REQUEST_CODE);
        } else {
            initVideoPath();
        }
    }

    private void initVideoPath() {
        File file = new File(Environment.getExternalStorageDirectory(), "nice.mp4");
        mVideoView.setVideoPath(file.getPath());
    }

    private class ClickListenerImpl implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.play:
                    if (!mVideoView.isPlaying()) {
                        mVideoView.start();
                    }
                    break;
                case R.id.pause:
                    if (mVideoView.isPlaying()) {
                        mVideoView.pause();
                    }
                    break;
                case R.id.replay:
                    if (mVideoView.isPlaying()) {
                        mVideoView.resume();
                    }
                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initVideoPath();
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
        if (mVideoView != null) {
            mVideoView.suspend();
        }
        super.onDestroy();
    }

}
