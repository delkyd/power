package com.hzgzh.balance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import cn.domob.android.ads.RTSplashAd;
import cn.domob.android.ads.RTSplashAdListener;
import cn.domob.android.ads.SplashAd;
import cn.domob.android.ads.SplashAd.SplashMode;
import cn.domob.android.ads.SplashAdListener;

public class SplashActivity extends Activity {
    static String PUBLISHER_ID = "56OJzR54uN69Zc0aZU";
    static String SplashPPID = "16TLmKmlApKBsNUO_USD3dIz";

    SplashAd splashAd;
    RTSplashAd rtSplashAd;
    //	 缓存开屏广告:true   实时开屏广告:false
//	Cache splash ad:true   Real-time splash ad:false
    private boolean isSplash = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去掉Activity上面的状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 去掉标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);

        /**
         *
         * DomobSplashMode.DomobSplashModeFullScreen 请求开屏广告的尺寸为全屏
         * DomobSplashMode.DomobSplashModeSmallEmbed 请求开屏广告的尺寸不是全屏，根据设备分辨率计算出合适的小屏尺寸
         * DomobSplashMode.DomobSplashModeBigEmbed 请求开屏广告的尺寸不是全屏，更具设备分辨率计算出合适的相对SmallMode的尺寸
         *
         */
        if (isSplash) {
//			 缓存开屏广告
//			Cache splash ad
            splashAd = new SplashAd(this, PUBLISHER_ID, SplashPPID,
                    SplashMode.SplashModeFullScreen);
//		    setSplashTopMargin is available when you choose non-full-screen splash mode.
//			splashAd.setSplashTopMargin(200);
            splashAd.setSplashAdListener(new SplashAdListener() {
                @Override
                public void onSplashPresent() {
                    Log.i("DomobSDKDemo", "onSplashStart");
                }

                @Override
                public void onSplashDismiss() {
                    Log.i("DomobSDKDemo", "onSplashClosed");
//					 开屏回调被关闭时，立即进行界面跳转，从开屏界面到主界面。
//				    When splash ad is closed, jump to the next(main) Activity immediately.
                    jump();
//					如果应用没有单独的闪屏Activity，需要调用closeSplash方法去关闭开屏广告
//					If you do not carry a separate advertising activity, you need to call closeRTSplash way to close the splash ad
//					splashAd.closeSplash();
                }

                @Override
                public void onSplashLoadFailed() {
                    Log.i("DomobSDKDemo", "onSplashLoadFailed");
                }
            });

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (splashAd.isSplashAdReady()) {
                        splashAd.splash(SplashActivity.this, SplashActivity.this.findViewById(R.id.splash_holder));
                    } else {
                        Toast.makeText(SplashActivity.this, "Splash ad is NOT ready.", Toast.LENGTH_SHORT).show();
                        jump();
                    }
                }
            }, 1);
        } else {
//			 实时开屏广告
//			Real-time splash ad
            rtSplashAd = new RTSplashAd(this, PUBLISHER_ID, SplashPPID,
                    SplashMode.SplashModeFullScreen);
//		    setRTSplashTopMargin is available when you choose non-full-screen splash mode.
//			rtSplashAd.setRTSplashTopMargin(200);
            rtSplashAd.setRTSplashAdListener(new RTSplashAdListener() {
                @Override
                public void onRTSplashDismiss() {
                    Log.i("DomobSDKDemo", "onRTSplashClosed");
//					 开屏回调被关闭时，立即进行界面跳转，从开屏界面到主界面。
//					When rtSplash ad is closed, jump to the next(main) Activity immediately.
                    jump();
//					如果应用没有单独的闪屏Activity，需要调用closeRTSplash方法去关闭开屏广告
//					If you do not carry a separate advertising activity, you need to call closeRTSplash way to close the splash ad

//					rtSplashAd.closeRTSplash();
                }

                @Override
                public void onRTSplashLoadFailed() {
                    Log.i("DomobSDKDemo", "onRTSplashLoadFailed");
                }

                @Override
                public void onRTSplashPresent() {
                    Log.i("DomobSDKDemo", "onRTSplashStart");
                }

            });

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    rtSplashAd.splash(SplashActivity.this, SplashActivity.this.findViewById(R.id.splash_holder));
                }
            }, 1);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("DomobSDKDemo", "Splash onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("DomobSDKDemo", "Splash onDestroy");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//		 Back key disabled
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void jump() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}

