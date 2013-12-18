package com.antoineanais.windowsgesture.activities;

import com.antoineanais.windowsgesture.Constantes;
import com.antoineanais.windowsgesture.R;
import com.antoineanais.windowsgesture.User;
import com.antoineanais.windowsgesture.R.layout;
import com.antoineanais.windowsgesture.R.menu;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class GestionPieceActivity extends Activity {

	Context monContext;
	User userForInstance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gestion_piece);
		monContext = (Context) this;

		Bundle monBundle;
		monBundle = this.getIntent().getExtras();

		userForInstance = (User) monBundle.get("CurrentUser");

		ImageButton buttonConnexion = (ImageButton) this
				.findViewById(R.id.imageButton1);
		buttonConnexion.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// made save

				SharedPreferences prefs = PreferenceManager
						.getDefaultSharedPreferences(GestionPieceActivity.this);
				Editor edit = prefs.edit();
				edit.putInt("LastCurrentUser", userForInstance.getId_user());
				edit.putString("LastCurrentScreen",
						AccueilUserActivity.class.toString());
				GestionPieceActivity.this
						.finishActivity(Constantes.GESTION_PIECE_ACTIVITY);
			}
		});

		Button textViewBtn_start_pdt = (Button) this
				.findViewById(R.id.btn_start_pdt);
		textViewBtn_start_pdt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button textViewBtn_stop_pdt = (Button) this
				.findViewById(R.id.btn_stop_pdt);
		textViewBtn_stop_pdt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button textViewBtn_next_pdt = (Button) this
				.findViewById(R.id.btn_next_pdt);
		textViewBtn_next_pdt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button textViewBtn_rebut_pdt = (Button) this
				.findViewById(R.id.btn_rebut_pdt);
		textViewBtn_rebut_pdt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button textViewBtn_change_zone = (Button) this
				.findViewById(R.id.btn_change_zone);
		textViewBtn_change_zone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		/* Remplit les objects de la fenêtre */

		TextView textViewZone = (TextView) this.findViewById(R.id.tvZone);
		TextView textViewProduit = (TextView) this.findViewById(R.id.tvProduit);
		TextView textViewStatut = (TextView) this.findViewById(R.id.tvStatut);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gestion_piece, menu);
		return true;
	}

}
