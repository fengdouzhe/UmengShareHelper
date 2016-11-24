package com.gosuncn.core.share;

import android.app.Activity;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMusic;



/**
 * Created by leo on 2016/11/22
 * description: 封装了友盟的分享方法
 */
public class ShareHelper {

    /**
     * 分享内容
     * @param activity  activity
     * @param umShareListener   回调接口
     * @param title   标题
     * @param text    内容
     * @param targetUrl 目标地址
     */
    public static void  shareText(Activity activity, UMShareListener umShareListener,
                                  String title,String text,String  targetUrl){
        new ShareAction(activity).setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .withTitle(title)
                .withText(text)
                .withTargetUrl(targetUrl)
                .setCallback(umShareListener)
                .open();
    }

    /**
     * 分享图片
     * @param activity  activity
     * @param umShareListener   回调接口
     * @param imgUrl   图片地址
     * @param targetUrl 目标地址
     */
    public static void  shareImgage(Activity activity, UMShareListener umShareListener,UMImage umImage,String  targetUrl){
        new ShareAction(activity).setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .withMedia(umImage)
                .withTargetUrl(targetUrl)
                .setCallback(umShareListener)
                .open();
    }

    /**
     * 分享视频
     * @param activity  activity
     * @param umShareListener   回调接口
     * @param videoUrl        视频地址
     * @param targetUrl 目标地址
     */
    public static void  shareVideo(Activity activity, UMShareListener umShareListener,
                                  UMVideo umVideo,String  targetUrl){
        new ShareAction(activity).setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .withMedia(umVideo)
                .withTargetUrl(targetUrl)
                .setCallback(umShareListener)
                .open();
    }

    /**
     * 分享音乐
     * @param activity  activity
     * @param umShareListener   回调接口
     *
     * @param targetUrl 目标地址
     */
    public static void  shareMusic(Activity activity, UMShareListener umShareListener,
                                  UMusic uMusic,String  targetUrl){
        new ShareAction(activity).setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .withMedia(uMusic)
                .withTargetUrl(targetUrl)
                .setCallback(umShareListener)
                .open();
    }
}
