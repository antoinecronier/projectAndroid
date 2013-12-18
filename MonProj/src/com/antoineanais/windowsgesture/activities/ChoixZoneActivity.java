package com.antoineanais.windowsgesture.activities;

import com.antoineanais.windowsgesture.R;
import com.antoineanais.windowsgesture.User;
import com.antoineanais.windowsgesture.R.layout;
import com.antoineanais.windowsgesture.R.menu;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ChoixZoneActivity extends Activity {

	Context monContext;
	User userForInstance;

	/*
	 * Intent monIntent = new Intent(ChoixZoneActivity.this,
	 * LoginActivity.class); monIntent.putExtra("CurrentUser", userForInstance);
	 * ChoixZoneActivity.this.startActivityForResult(monIntent, 21);
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choix_zone);

		monContext = (Context) this;

		Bundle monBundle;
		monBundle = this.getIntent().getExtras();

		userForInstance = (User) monBundle.get("CurrentUser");

		ImageButton buttonConnexion = (ImageButton) this
				.findViewById(R.id.imageButton1);
		buttonConnexion.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SharedPreferences prefs = PreferenceManager
						.getDefaultSharedPreferences(ChoixZoneActivity.this);
				Editor edit = prefs.edit();
				edit.putInt("LastCurrentUser", userForInstance.getId_user());
				edit.putString("LastCurrentScreen",
						ChoixZoneActivity.class.toString());
				ChoixZoneActivity.this.finishActivity(21);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choix_zone, menu);
		return true;
	}

}
