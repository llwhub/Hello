package com.llw.test;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;

public class SensorActivity extends Activity implements SensorEventListener{
	SensorManager sensorManager;
	EditText etTxt1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor);
		etTxt1=(EditText)findViewById(R.id.txt1);
		sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
	}
	@Override
	protected void onResume() {
		super.onResume();
		sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
	}
	@Override
	protected void onStop() {
		sensorManager.unregisterListener(this);
		super.onStop();
	}
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		
	}
	@Override
	public void onSensorChanged(SensorEvent arg0) {
		float[] values=arg0.values;
		StringBuilder sb=new StringBuilder();
		sb.append("X方向上的加速度:");
		sb.append(values[0]);
		sb.append("\nY方向上的加速度：");
		sb.append(values[1]);
		sb.append("\nZ方向上的加速度：");
		sb.append(values[2]);
		etTxt1.setText(sb.toString());
	}
}
