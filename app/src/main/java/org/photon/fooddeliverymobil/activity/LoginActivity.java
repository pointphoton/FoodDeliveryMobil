package org.photon.fooddeliverymobil.activity;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.photon.fooddeliverymobil.R;
import org.photon.fooddeliverymobil.application.FoodDeliveryApp;
import org.photon.fooddeliverymobil.connection.ResponseEventModel;
import org.photon.fooddeliverymobil.connection.service.LoginSL;
import org.photon.fooddeliverymobil.connection.service.PostLoginServiceListener;
import org.photon.fooddeliverymobil.connection.service.ProductSL;
import org.photon.fooddeliverymobil.link.HttpLink;
import org.photon.fooddeliverymobil.model.LogInUserModel;
import org.photon.fooddeliverymobil.model.LoginModel;
import org.photon.fooddeliverymobil.util.FoodDeliveryUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

	@Bind(R.id.activity_login_button) Button btnLogin;
	@Bind(R.id.activity_login_mail_editText) EditText editMail ;
	@Bind(R.id.activity_login_password_editText) EditText editPass ;
	private String email="";

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);
		ButterKnife.bind(this);
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
			 email = editMail.getText().toString();
				String pass = editPass.getText().toString();
				if (email.length()<3 || pass.length()<3)
				{
					Toast.makeText(LoginActivity.this,"Please enter valid username or password!",Toast.LENGTH_LONG).show();

				}
				LoginSL loginSL = new LoginSL(LoginActivity.this,postLoginServiceListener, HttpLink.getHttpLink());
				loginSL.setLogInAccount(new LoginModel(email,pass));
				showProgress("FoodDelivery Bağlanıyor...");

			}
		});
	}


	PostLoginServiceListener postLoginServiceListener =new PostLoginServiceListener() {
		@Override public void logInAccount(LogInUserModel logInUserModel) {
			dismissProgress();
			if (email.equalsIgnoreCase(logInUserModel.getEmail())) {
				FoodDeliveryApp.setEmail(logInUserModel.getEmail());
				FoodDeliveryApp.setCustomerId(String.valueOf(logInUserModel.getId()));
				writeLog("login email mail", FoodDeliveryApp.getEmail());
				writeLog("login token", FoodDeliveryApp.getToken());
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this,RestaurantActivity.class);
				startActivity(intent);

			}
		}

		@Override public void onComplete(ResponseEventModel onComplete) {

		}
	};





}
