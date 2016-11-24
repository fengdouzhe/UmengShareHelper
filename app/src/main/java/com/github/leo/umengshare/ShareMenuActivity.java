package com.github.leo.umengshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.leo.umengshare.model.Defaultcontent;
import com.gosuncn.core.share.ShareHelper;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.utils.Log;

import static com.github.leo.umengshare.model.Defaultcontent.musicurl;
import static com.github.leo.umengshare.model.Defaultcontent.videourl;


/**
 * Created by leo on 2016/11/21
 * description: 分享菜单
 */
public class ShareMenuActivity extends Activity {

    private Button shareContext;
    private Button shareMusic;
    private Button shareVideo;
    private Button shareImage;

    private final String TARGET_URL = "http://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_sharemenu);
        shareContext =(Button)findViewById(R.id.share_context);
        shareMusic =(Button)findViewById(R.id.share_music);
        shareVideo =(Button)findViewById(R.id.share_video);
        shareImage =(Button)findViewById(R.id.share_image);
        shareContext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 ShareHelper.shareText(ShareMenuActivity.this,umShareListener,
                         Defaultcontent.title,Defaultcontent.text,TARGET_URL);
            }
        });
        shareMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UMusic music = new UMusic(musicurl);
                music.setTitle("This is music title");//音乐的标题
                music.setThumb(new UMImage(ShareMenuActivity.this,"http://www.umeng.com/images/pic/social/chart_1.png"));//音乐的缩略图
                music.setDescription("my description");//音乐的描述
                ShareHelper.shareMusic(ShareMenuActivity.this,umShareListener,
                        music,TARGET_URL);
            }
        });
        shareVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UMVideo video = new UMVideo(videourl);
                video.setTitle("This is music title");//视频的标题
                video.setThumb(new UMImage(ShareMenuActivity.this,"http://www.umeng.com/images/pic/social/chart_1.png"));//视频的缩略图
                video.setDescription("my description");//视频的描述

                ShareHelper.shareVideo(ShareMenuActivity.this,umShareListener,video,TARGET_URL);
            }
        });
        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShareHelper.shareImgage(ShareMenuActivity.this,umShareListener,new UMImage(ShareMenuActivity.this,"http://www.umeng.com/images/pic/social/chart_1.png"),TARGET_URL);
            }
        });
    }



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
}
