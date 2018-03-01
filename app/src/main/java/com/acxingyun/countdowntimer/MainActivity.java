package com.acxingyun.countdowntimer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private static final String TAG = "MainActivity";
    private MyCountDownTimer.CountDownCallback countDownCallback = new MyCountDownTimer.CountDownCallback() {

        @Override
        public void startCountDown(long millisInFuture) {
            textView.setText("开始倒计时");
        }

        @Override
        public void onCountDown(long millisUntilFinished) {
            textView.setText("剩余时间" + Utils.millisToChinese(millisUntilFinished));
        }

        @Override
        public void endCountDown() {
            textView.setText("倒计时结束");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void btnClicked(View v){
        Log.i(TAG, "btnClicked...");
        MyCountDownTimer myCountDownTimer = MyCountDownTimer.getIns(50 * 1000, 1000, countDownCallback);
        myCountDownTimer.start();
    }

    public void btnLogin(View v){
        Log.i(TAG, "btnLogin clicked...");
        Toast.makeText(getApplicationContext(), "login!!!", Toast.LENGTH_SHORT).show();
    }
}
