package com.example.mohamed.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public int Seconds = 0;
    boolean Running;
    boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState !=null)
        {
            Seconds =savedInstanceState.getInt("Seconds");
            Running=savedInstanceState.getBoolean("Running");
        }
        runTimer();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Seconds" ,Seconds);
        outState.putBoolean("Running" ,Running);
    }
    @Override
protected void onStart()
    {
        super.onStart();
        if(wasRunning){
            Running=true;
        }
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        wasRunning = Running;
        Running=false;

        }


    public void onClickStart(View view) {

            Running=true;

    }

    public void onClickStop(View view) {

        Running=false;
    }

    public void onClickReset(View view) {
        Running=false;
        Seconds=0;
    }

    private void runTimer() {
        final TextView textView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int Hours = Seconds/3600;
                int Minutes = (Seconds%3600)/60;
                int Secs = Seconds%60;
                String Time = String.format(Locale.getDefault(),"%d:%02d:%02d",Hours,Minutes,Secs);
                textView.setText(Time);
                if(Running)
                {
                    Seconds++;
                }
              handler.postDelayed(this ,1000);
            }
        });
    }
}
