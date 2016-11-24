package com.github.leo.umengshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gosuncn.core.share.ThirdPartyHelper;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;

import java.util.Map;

/**
 * Created by leo on 2016/11/22
 * description: 获得用户信息
 */
public class UserinfoActivity extends Activity {
    private UMShareAPI mShareAPI = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_user);
        mShareAPI = UMShareAPI.get(this);
    }

   public void onClick(View view) {
        SHARE_MEDIA platform = null;
        switch (view.getId()){
            case R.id.qqinfo:
                ThirdPartyHelper.authLogin(this,mShareAPI,platform.QQ,umAuthListener);
                break;
            case R.id.wxinfo:
                ThirdPartyHelper.authLogin(this,mShareAPI,platform.WEIXIN,umAuthListener);
                break;
        }

    }

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

}
