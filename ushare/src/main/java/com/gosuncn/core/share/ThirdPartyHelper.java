package com.gosuncn.core.share;

import android.app.Activity;
import android.content.Context;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by leo on 2016/11/22
 * description: 封装了友盟的第三方平台登录方法
 */
public class ThirdPartyHelper {

    /**
     * 第三方平台登录
     * @param platform  平台 例如QQ,微信等等
     * @return
     */
    public static void  authLogin(Context context, UMShareAPI mShareAPI,SHARE_MEDIA platform,UMAuthListener umAuthListener){
        mShareAPI.getPlatformInfo((Activity) context, platform, umAuthListener);
    }

}
