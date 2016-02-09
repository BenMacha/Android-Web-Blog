package tn.benmacha.benmacha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Home extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		// Execute some code after 4 seconds have passed
	    Handler handler = new Handler(); 
	    handler.postDelayed(new Runnable() { 
	         public void run() { 
	        	Intent i = new Intent(getApplicationContext(), Blog.class);                      
	         	startActivity(i);
	         	finish();
	         } 
	    }, 4000);
		
	}

	
}
