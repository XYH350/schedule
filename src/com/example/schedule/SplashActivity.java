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
    alphaAnimation.setDuration(2000);//���ö�������ʱ��1000���루1�룩
    layoutSplash.startAnimation(alphaAnimation);
    //���ö�������
    alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
    	@Override
    	public void onAnimationStart(Animation animation) {
    	}
    	//��������
    	@Override
    	public void onAnimationEnd(Animation animation) {
    		//ҳ�����ת
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

