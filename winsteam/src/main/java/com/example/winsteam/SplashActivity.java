package com.example.winsteam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		

		
		new Handler().postDelayed(new Runnable(){  

		public void run() {  
			Intent i = new Intent(SplashActivity.this, MainActivity.class);   

			startActivity(i);
			finish();
			}  
		}, 3000);
	}

}
