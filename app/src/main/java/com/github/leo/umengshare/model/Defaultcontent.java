package com.github.leo.umengshare.model;

import android.os.Environment;

/**
 * Created by leo on 2016/11/21
 * description: 默认分享数据
 */
public class Defaultcontent {
    public static String url = "http://t.cn/RVsZt1Z";
    public static String text = "测试内容";
    public static String title = "测试标题";
    public static String imageurl = "http://dev.umeng.com/images/tab2_1.png";
    public static String videourl = "http://video.sina.com.cn/p/sports/cba/v/2013-10-22/144463050817.html";
    public static String musicurl = "http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3";
    public static final String SDCARD_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();
}
