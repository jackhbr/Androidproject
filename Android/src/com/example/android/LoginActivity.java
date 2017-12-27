package com.example.android;

import com.example.android.R.id;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        Button btn=(Button)this.findViewById(R.id.login);
        EditText useaccount=(EditText)this.findViewById(R.id.usecount);
        EditText usepassword=(EditText)this.findViewById(R.id.usepassword);
        
        
        aaa al=new aaa(useaccount,usepassword,this);
        
        btn.setOnClickListener(al);
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
    
}
