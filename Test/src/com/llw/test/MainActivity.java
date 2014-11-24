package com.llw.test;


import android.app.Activity;		
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn=(Button)findViewById(R.id.begin);
		Button btnXml=(Button)findViewById(R.id.xml);
		Button btnSensor=(Button)findViewById(R.id.sensor);
		Button btnScrollLayout=(Button)findViewById(R.id.scrollLayout);
		Button btnGreenDroid=(Button)findViewById(R.id.greenDroid);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent();
				intent.setAction("android.intent.action.View");
				intent.addCategory(Intent.CATEGORY_DEFAULT);
				Uri uriBrowsers=Uri.parse("http://www.baidu.com");
				intent.setData(uriBrowsers);
				intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
				startActivity(intent);
			}
		});
		btnXml.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(MainActivity.this,XmlActivity.class);
				startActivity(intent);
			}
		});
		btnSensor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(MainActivity.this,SensorActivity.class);
				startActivity(intent);
			}
		});
		btnScrollLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(MainActivity.this,ScrollLayoutActivity.class);
				startActivity(intent);
			}
		});
		btnGreenDroid.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(MainActivity.this,GreenDroidActivity.class);
				startActivity(intent);
			}
		});
	}

}
