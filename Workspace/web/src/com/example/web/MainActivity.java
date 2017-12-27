package com.example.web;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	
	public void onStart(){
		super.onStart();
		
		WebView wv = (WebView)this.findViewById(R.id.showWeb);
		wv.getSettings().setJavaScriptEnabled(true);
		
		wv.loadUrl("file:///android_asset/project/hl.html");
		wv.loadUrl("file:///android_asset/project/feng.html");
		wv.loadUrl("file:///android_asset/project/index.html");
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}