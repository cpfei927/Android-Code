package com.cpfei.toastanimdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void showToast(View v) {

//		AnimToast.makeTextAnim(this, "测试自定义动画Toast", 8000,R.style.anim_view).show();
	
		
		CToast.makeText(this, "测试", 500).show();
		

	}


}
