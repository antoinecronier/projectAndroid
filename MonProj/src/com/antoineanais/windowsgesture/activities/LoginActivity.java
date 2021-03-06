package com.antoineanais.windowsgesture.activities;

import java.security.KeyRep.Type;

import com.antoineanais.windowsgesture.Constantes;
import com.antoineanais.windowsgesture.R;
import com.antoineanais.windowsgesture.R.layout;
import com.antoineanais.windowsgesture.R.menu;
import com.antoineanais.windowsgesture.User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * <br>
 * Ecran utiliser pour logger l'utilisateur par leur role. </br> <br>
 * Si le role est égale à 0 l'utilisateur ne sera pas redirigé vers une autre
 * page. </br> <br>
 * Si le role est égale à 1 l'utilisateur est définit comme étant un opérateur.
 * </br> <br>
 * Si le role est égale à 2 l'utilisateur est définit comme étant un admin
 * qualité. </br> <br>
 * Si le role est égale à 3 l'utilisateur est définit comme étant un admin
 * logiciel.</br>
 * 
 * @author alexandre
 * 
 */
public class LoginActivity extends Activity {

	Context monContext;
	User userForInstance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		monContext = (Context) this;

		Button buttonConnexion = (Button) this.findViewById(R.id.btnConnexion);
		buttonConnexion.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				/* Remplit l'utilisateur pour le logger */
				userForInstance = new User(
						((EditText) findViewById(R.id.login)).getText()
								.toString(),
						((EditText) findViewById(R.id.pwd)).getText()
								.toString(), 0);

				userForInstance.getUserForAuthnetification(monContext);

				SharedPreferences prefs = PreferenceManager
						.getDefaultSharedPreferences(LoginActivity.this);

				if (prefs.contains("LastCurrentUser")) {
					if (userForInstance.getId_user() == prefs.getInt(
							"LastCurrentUser", 0)) {
						if (prefs.contains("LastCurrentScreen")) {
							String lastScreen = prefs.getString(
									"LastCurrentScreen", "");
							Intent monIntent;
							try {
								monIntent = new Intent(LoginActivity.this,
										Class.forName(lastScreen));
								monIntent.putExtra("CurrentUser",
										userForInstance);
								LoginActivity.this.startActivityForResult(
										monIntent,
										Constantes.LOGIN_ACTIVITY);
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}

				}

				/*
				 * Test le role de l'utilisateur pour lui charger la page
				 * souhaité
				 */
				if (userForInstance.getId_user() != 0) {

					switch (userForInstance.getRole()) {
					case 0:
						Toast mont = Toast.makeText(LoginActivity.this,
								"Can't redirect, please call your "
										+ "administrator", Toast.LENGTH_LONG);
						mont.show();
						break;

					case 1:
						Intent monIntent1 = new Intent(LoginActivity.this,
								AccueilUserActivity.class);
						monIntent1.putExtra("CurrentUser", userForInstance);
						LoginActivity.this.startActivityForResult(monIntent1,
								Constantes.LOGIN_ACTIVITY);
						break;

					case 2:
						Intent monIntent2 = new Intent(LoginActivity.this,
								AccueilQualiteActivity.class);
						monIntent2.putExtra("CurrentUser", userForInstance);
						LoginActivity.this.startActivityForResult(monIntent2,
								Constantes.LOGIN_ACTIVITY);
						break;

					case 3:
						Intent monIntent3 = new Intent(LoginActivity.this,
								AccueilLogicielActivity.class);
						monIntent3.putExtra("CurrentUser", userForInstance);
						LoginActivity.this.startActivityForResult(monIntent3,
								Constantes.LOGIN_ACTIVITY);
						break;

					default:
						break;
					}
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
