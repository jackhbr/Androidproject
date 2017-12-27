package com.example.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class DrawActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);
	}
	
	 public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.login, menu);
	        return true;
	    }

}
