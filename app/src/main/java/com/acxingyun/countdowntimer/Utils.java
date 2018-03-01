package com.acxingyun.countdowntimer;

/**
 * Created by Xing.Yun on 2018/3/1.
 */

public class Utils {

    /**
     * 毫秒转换中文时间
     * @param ms
     * @return
     */
    public static String millisToChinese(long ms) {

        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;

        StringBuilder stringBuilder = new StringBuilder();

        if (day >= 1){
            stringBuilder.append(day).append("天").append(hour).append("小时").append(minute).append("分钟").append(second).append("秒");
        }else if (hour >= 1){
            stringBuilder.append(hour).append("小时").append(minute).append("分钟").append(second).append("秒");
        }else if (minute >= 1){
            stringBuilder.append(minute).append("分钟").append(second).append("秒");
        }else if (second >= 1){
            stringBuilder.append(second).append("秒");
        }

        return stringBuilder.toString();
    }
}
