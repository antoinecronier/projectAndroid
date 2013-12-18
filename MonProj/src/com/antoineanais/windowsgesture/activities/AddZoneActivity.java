package com.antoineanais.windowsgesture.activities;

import com.antoineanais.windowsgesture.R;
import com.antoineanais.windowsgesture.R.layout;
import com.antoineanais.windowsgesture.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AddZoneActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_zone);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_zone, menu);
		return true;
	}

}
