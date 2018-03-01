package com.acxingyun.countdowntimer;

import android.os.CountDownTimer;
import android.util.Log;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Xing.Yun on 2018/2/28.
 */

public class MyCountDownTimer extends CountDownTimer {

    private static final String CLASS_TAG = "MyCountDownTimer";
    private static Lock mLock = new ReentrantLock();
    private CountDownCallback mCountDownCallback;
    private static MyCountDownTimer ins;
    public static MyCountDownTimer getIns(long millisInFuture, long countDownInterval, CountDownCallback countDownCallback){
        if (mLock.tryLock()){
            try{
                if (ins == null){
                    ins = new MyCountDownTimer(millisInFuture, countDownInterval, countDownCallback);
                }
            }catch (Exception e){
                Log.e(CLASS_TAG, "exception:" + e);
            }finally {
                mLock.unlock();
                Log.i(CLASS_TAG, "mLock.unlock()");
            }
        }
        Log.i(CLASS_TAG, "ins:" + ins);
        return ins;
    }

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    private MyCountDownTimer(long millisInFuture, long countDownInterval, CountDownCallback countDownCallback) {
        super(millisInFuture, countDownInterval);
        mCountDownCallback = countDownCallback;
        mCountDownCallback.startCountDown(millisInFuture);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mCountDownCallback.onCountDown(millisUntilFinished);
    }

    @Override
    public void onFinish() {
        mCountDownCallback.endCountDown();
    }

    interface CountDownCallback {
        void startCountDown(long millisInFuture);
        void onCountDown(long millisUntilFinished);
        void endCountDown();
    }
}
