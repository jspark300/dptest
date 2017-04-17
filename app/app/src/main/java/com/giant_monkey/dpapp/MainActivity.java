package com.giant_monkey.dpapp;

import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private List myList;
    VideoView videoView;

    TextView textView;
    int index = 0;


    private Handler mHandler;
    private Runnable mRunnable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_main);

       videoView = (VideoView) findViewById(R.id.videoView);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("hi");

        myList = new ArrayList();

        myList.add("1.mp4");
        myList.add("2.mp4");
        myList.add("3.mp4");
        myList.add("4.mp4");

        mRunnable = new Runnable() {
            @Override
            public void run() {
                play();
            }
        };
        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 1000);

        //play();
//
//        mTask = new TimerTask() {
//            @Override
//            public void run() {
//                textcount();
//            }
//        };
//
//        mTimer = new Timer();
//
//        mTimer.schedule(mTask, 1000);



       // play();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){

            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.seekTo(0);
                videoView.start();
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //videoView.sto
                //play();
            }
        });

    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }
//
//    @Override
//    protected void onPause() {
//        mHandler.removeCallbacks(mRunnable);
//        super.onPause();
//    }
//
//    @Override
//    protected void onResume() {
//        mHandler.removeCallbacks(mRunnable);
//        super.onResume();
//    }


    void play() {
        Date rightNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
        String dateString = formatter.format(rightNow);
        textView.setText(dateString);


        videoView.setVideoPath(Environment.getExternalStorageDirectory() + File.separator + "DCIM/" + myList.get(index).toString());
        index++;
        if(index == 4)
            index = 0;

        mHandler.postDelayed(mRunnable, 10000);




    }
}
