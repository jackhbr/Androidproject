package com.example.android;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class aaa implements OnClickListener{
	
	private EditText useaccount;
	private EditText usepassword;
	private Activity temActivity;
	
	public aaa(EditText useaccount, EditText usepassword,Activity temActivity)
	{
		this.useaccount=useaccount;
		this.usepassword=usepassword;
		this.temActivity=temActivity;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		System.out.println("你好");
		Log.d("debug", "nihao");//以后用这个来调试安卓程序，不要用syso了，syso不能查找到
		Log.d("debug",useaccount.getText().toString());
		Log.d("debug",usepassword.getText().toString());  //如果logcat没有输出，那么就重启一下ecplise
		Intent in=new Intent(temActivity,DrawActivity.class);
		temActivity.startActivity(in);
		Log.d("debug", "我被执行啦"+useaccount.getText().toString());
		
	}
	

}
