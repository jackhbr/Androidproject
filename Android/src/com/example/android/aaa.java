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
		System.out.println("���");
		Log.d("debug", "nihao");//�Ժ�����������԰�׿���򣬲�Ҫ��syso�ˣ�syso���ܲ��ҵ�
		Log.d("debug",useaccount.getText().toString());
		Log.d("debug",usepassword.getText().toString());  //���logcatû���������ô������һ��ecplise
		Intent in=new Intent(temActivity,DrawActivity.class);
		temActivity.startActivity(in);
		Log.d("debug", "�ұ�ִ����"+useaccount.getText().toString());
		
	}
	

}
