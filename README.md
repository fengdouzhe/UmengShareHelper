# UmengShareHelper
# Introduction
  这是一个封装了友盟分享和第三方登录的帮助类，像现在好多app都有分享，第三方平台登录的，目前据本人看的话，比较多的是QQ，微信分享,登录。但是要集成这个的话，有好多方法。不过都需要在官网上下载sdk，看文档，一步一步地去做，但是这个的话比较麻烦。如果是新手的话,这个就需要花费时间去看官网上文档，慢慢地去集成，就算是老司机的话，集成起来也不是很方便，为什么这样说呢?因为都要复制jar以及资源文件，配置权限，修改build.gradle文件等等，这个的话会超级麻烦。但是有了这个就不用复制jar以及资源等等，有了这个的话，就需要几步初始化然后就可以调用了。

# Getting Started
   由于需要时间审核，所以脚本引入需要等通过才可以。
##Usage
1.初始化Activity,将微信和QQ需要用到的Activity注册进来。
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
2.申请友盟APPkey，然后放到AndroidManifest.xml中。
```Java
        <meta-data
            android:name="UMENG_APPKEY"
            android:value="561cae6ae0f55abd990035bf" >
        </meta-data>
```
value需要填写去友盟申请到的key<br>

3.初始化微信和qq（ps：需要去微信平台和腾讯申请才行）
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
4.调用方法:<br>
(1)分享
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
    public static void  shareText(Activity activity, UMShareListener umShareListener,String title,String text,String  targetUrl)
```
●分享图片：
```Java
 /**
     * 分享图片
     * @param activity  activity
     * @param umShareListener   回调接口
     * @param imgUrl   图片地址
     * @param targetUrl 目标地址
     */
    public static void  shareImgage(Activity activity, UMShareListener umShareListener,UMImage umImage,String  targetUrl)
```
●分析视频：
```Java
   /**
     * 分享视频
     * @param activity  activity
     * @param umShareListener   回调接口
     * @param videoUrl        视频地址
     * @param targetUrl 目标地址
     */
    public static void  shareVideo(Activity activity, UMShareListener umShareListener, UMVideo umVideo,String  targetUrl)
```
●分析音乐：
```Java
   /**
     * 分享音乐
     * @param activity  activity
     * @param umShareListener   回调接口
     *
     * @param targetUrl 目标地址
     */
    public static void  shareMusic(Activity activity, UMShareListener umShareListener,UMusic uMusic,String  targetUrl)
```
(2)第三方登录
```Java
   /**
     * 第三方平台登录
     * @param platform  平台 例如QQ,微信等等
     * @return
     */
    public static void  authLogin(Context context, UMShareAPI mShareAPI,SHARE_MEDIA platform,UMAuthListener umAuthListener)
``` 
#Screenshots

