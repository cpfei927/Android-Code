package com.cpfei.toastanimdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		GridView gr = (GridView) findViewById(R.id.gridView);
		gr.setAdapter(new MyAdapter());
	}
	
	
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return 5;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@SuppressLint("ViewHolder")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View view  = LayoutInflater.from(MainActivity.this).inflate(R.layout.gridview_item, null);
			
			return view;
		}
		
	}
	
	
	

	public void showToast(View v) {

//		AnimToast.makeTextAnim(this, "测试自定义动画Toast", 8000,R.style.anim_view).show();
	
		
		CToast.makeText(this, "测试", 500).show();
		

	}


}
