package com.example.schedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
		LinearLayout layoutSplash=(LinearLayout) findViewById(R.id.splash);
    AlphaAnimation alphaAnimation=new AlphaAnimation(0.8f,1.0f);
    alphaAnimation.setDuration(2000);//设置动画播放时长1000毫秒（1秒）
    layoutSplash.startAnimation(alphaAnimation);
    //设置动画监听
    alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
    	@Override
    	public void onAnimationStart(Animation animation) {
    	}
    	//动画结束
    	@Override
    	public void onAnimationEnd(Animation animation) {
    		//页面的跳转
    		Intent intent=new Intent(SplashActivity.this,MainActivity.class);
    		startActivity(intent);
    		finish();
    	}
    	@Override
    	public void onAnimationRepeat(Animation animation) {
    	}
    });
	}
}

