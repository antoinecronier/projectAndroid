package com.antoineanais.windowsgesture.activities;

import com.antoineanais.windowsgesture.R;
import com.antoineanais.windowsgesture.R.layout;
import com.antoineanais.windowsgesture.R.menu;
import com.antoineanais.windowsgesture.User;
import com.example.mooi.fragmentDeMooi;
import com.example.mooi.fragmentDeMooi2;
import com.example.mooi.maclasse;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Ecran utiliser pour logger l'utilisateur par leur role. Si le role est égale
 * à 0 l'utilisateur ne sera pas redirigé vers une autre page. Si le role est
 * égale à 1 l'utilisateur est définit comme étant un opérateur. Si le role est
 * égale à 2 l'utilisateur est définit comme étant un admin qualité. Si le role
 * est égale à 3 l'utilisateur est définit comme étant un admin logiciel.
 * 
 * @author alexandre
 * 
 */
public class LoginActivity extends Activity {

	Context monContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		monContext = (Context) this;

		Button buttonConnexion = (Button) this.findViewById(R.id.button1);
		buttonConnexion.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				/* Remplit l'utilisateur pour le logger */
				User userForInstance = new User(
						((EditText) findViewById(R.id.login)).getText()
								.toString(),
						((EditText) findViewById(R.id.pwd)).getText()
								.toString(), 0);

				userForInstance.getUserForAuthnetification(monContext);

				/*
				 * Test le role de l'utilisateur pour lui charger la page
				 * souhaité
				 */
				if (userForInstance.getId_user() != 0) {
					Intent monIntent = new Intent(LoginActivity.this,
							ChoixZoneActivity.class);
					monIntent.putExtra("CurrentUser", userForInstance);

					switch (userForInstance.getRole()) {
					case 1:

						LoginActivity.this
								.startActivityForResult(monIntent, 42);
						break;

					case 2:

						LoginActivity.this
								.startActivityForResult(monIntent, 42);
						break;

					default:
						break;
					}

				}

				if ((((EditText) findViewById(R.id.editText1)).getText()
						.toString()).equals("imie")
						&& (((EditText) findViewById(R.id.editText2)).getText()
								.toString()).equals("imiePass")) {

					;
					mont.show();

					Intent monI = new Intent(fragmentDeMooi.this.getActivity(),
							fragmentDeMooi2.class);

					maclasse hey = new maclasse();

					hey.setTitle(((EditText) view.findViewById(R.id.editText1))
							.getText().toString());

					monI.putExtra("hey", hey);
					fragmentDeMooi.this.startActivityForResult(monI, 42);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.accueil, menu);
		return true;
	}

}
