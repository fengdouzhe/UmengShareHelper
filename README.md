# UmengShareHelper
# Introduction
  这是一个封装了友盟分享和第三方登录的帮助类，像现在好多app都有分享，第三方平台登录的，目前据本人看的话，比较多的是QQ，微信分享,登录。但是要集成这个的话，有好多方法。不过都需要在官网上下载sdk，看文档，一步一步地去做，但是这个的话比较麻烦。如果是新手的话,这个就需要花费时间去看官网上文档，慢慢地去集成，就算是老司机的话，集成起来也不是很方便，为什么这样说呢?因为都要复制jar以及资源文件，配置权限，修改build.gradle文件等等，这个的话会超级麻烦。但是有了这个就不用复制jar以及资源等等，有了这个的话，就需要几步初始化然后就可以调用了。

# Getting Started
   由于需要时间审核，所以脚本引入需要等通过才可以。
##Usage
###1.初始化Activity,将微信和QQ需要用到的Activity注册进来。
```Java
       <!--微信-->
        <activity
            android:name="com.gosuncn.core.share.wxapi.WXEntryActivity"
            android:configChanges="keyboardHidden|orientation|screenSize"
            android:exported="true"
            android:screenOrientation="portrait"
            android:theme="@android:style/Theme.Translucent.NoTitleBar" />

        <!--QQ-->
        <activity
            android:name="com.tencent.tauth.AuthActivity"
            android:launchMode="singleTask"
            android:noHistory="true" >
            <intent-filter>
                <action android:name="android.intent.action.VIEW" />

                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />

                <data android:scheme="tencent100424468" />
            </intent-filter>
        </activity>
        <activity
            android:name="com.tencent.connect.common.AssistActivity"
            android:screenOrientation="portrait"
            android:theme="@android:style/Theme.Translucent.NoTitleBar"
            android:configChanges="orientation|keyboardHidden|screenSize"/>


        <!--分享编辑页-->
        <activity
            android:name="com.umeng.socialize.editorpage.ShareActivity"
            android:theme="@style/Theme.UMDefault"
            android:excludeFromRecents="true"
            />
```
###2.申请友盟APPkey，然后放到AndroidManifest.xml中。
```Java
        <meta-data
            android:name="UMENG_APPKEY"
            android:value="561cae6ae0f55abd990035bf" >
        </meta-data>
```
value需要填写去友盟申请到的key<br>

###3.初始化微信和qq（ps：需要去微信平台和腾讯申请才行）
```Java
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
    }

    {
        <!--微信-->
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        <!--QQ-->
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

    }
}
```
###4.调用方法:
(1)分享<br>
在需要用到的Activity加上以下方法：
```Java
   private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);
            if(platform.name().equals("WEIXIN_FAVORITE")){
                Toast.makeText(ShareMenuActivity.this,platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(ShareMenuActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ShareMenuActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if(t!=null){
                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ShareMenuActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** attention to this below ,must add this**/
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        Log.d("result","onActivityResult");
    }
```
●分享内容：
```Java
   /**
     * 分享内容
     * @param activity  activity
     * @param umShareListener   回调接口
     * @param title   标题
     * @param text    内容
     * @param targetUrl 目标地址
     */
      ShareHelper.shareText(Activity activity,umShareListener,title,text,targetUrl);
```
●分享图片：
```Java
   /**
     * 分享图片
     * @param activity  activity
     * @param umShareListener   回调接口
     * @param umImage   图片对象
     * @param targetUrl 目标地址
     */
     
     UMImage umImage = new UMImage(ShareMenuActivity.this,"http://www.umeng.com/images/pic/social/chart_1.png");
     ShareHelper.shareImgage(Activity activity,umShareListener,umImage,targetUrl);
```
●分析视频：
```Java
   /**
     * 分享视频
     * @param activity  activity
     * @param umShareListener   回调接口
     * @param video        视频对象
     * @param targetUrl 目标地址
     */
    UMVideo video = new UMVideo(videourl);//视频地址
    video.setTitle("This is music title");//视频的标题
    video.setThumb(new UMImage(ShareMenuActivity.this,"http://www.umeng.com/images/pic/social/chart_1.png"));//视频的缩略图
    video.setDescription("my description");//视频的描述

    ShareHelper.shareVideo(Activity activity,umShareListener,video,targetUrl);
```
●分析音乐：
```Java
   /**
     * 分享音乐
     * @param activity  activity
     * @param umShareListener   回调接口
     * @param music 音乐对象
     * @param targetUrl 目标地址
     */
     
    UMusic music = new UMusic(musicurl);
    music.setTitle("This is music title");//音乐的标题
    music.setThumb(new UMImage(ShareMenuActivity.this,"http://www.umeng.com/images/pic/social/chart_1.png"));//音乐的缩略图
    music.setDescription("my description");//音乐的描述
    ShareHelper.shareMusic(Activity activity,umShareListener,music,targetUrl);
```
(2)第三方登录
 在需要用到的Activity加上以下方法
```Java
  private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            for (String key : data.keySet()) {
                Log.e("xxxxxx key = "+key+"    value= "+data.get(key));
            }
            if (data!=null){

                Toast.makeText(getApplicationContext(), data.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "get fail"+t.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getApplicationContext(), "get cancel", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);
    }
``` 
```Java
   /**
     * 第三方平台登录
     * @param activity  
     * @param UMShareAPI
     * @param umAuthListener
     * @return
     */
     ThirdPartyHelper.authLogin(this,UMShareAPI.get(this),platform.QQ,umAuthListener);
     ThirdPartyHelper.authLogin(this,UMShareAPI.get(this),platform.WEIXIN,umAuthListener);

``` 
#Screenshots
![](https://github.com/fengdouzhe/UmengShareHelper/blob/master/images/1.png)
![](https://github.com/fengdouzhe/UmengShareHelper/blob/master/images/2.png)
![](https://github.com/fengdouzhe/UmengShareHelper/blob/master/images/3.png)
![](https://github.com/fengdouzhe/UmengShareHelper/blob/master/images/4.png)
![](https://github.com/fengdouzhe/UmengShareHelper/blob/master/images/5.png)
![](https://github.com/fengdouzhe/UmengShareHelper/blob/master/images/6.png)

#Bugs Report and Help
If you find any bug when using project, please report [here](https://github.com/fengdouzhe/UmengShareHelper/issues/new). Thanks for helping us building a better one.

#Abount Author
作者是一个喜爱编程的人，本人QQ是：1040114490。欢迎大家来加我交流。

#License
```Java
   Copyright (C) fengdouzhe, The Framework Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
``` 
