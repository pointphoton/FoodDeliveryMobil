package org.photon.fooddeliverymobil.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.photon.fooddeliverymobil.R;
import org.photon.fooddeliverymobil.adapter.AddressListAdapter;
import org.photon.fooddeliverymobil.application.FoodDeliveryApp;
import org.photon.fooddeliverymobil.connection.ResponseEventModel;
import org.photon.fooddeliverymobil.connection.service.AddressSL;
import org.photon.fooddeliverymobil.connection.service.PostAddressServiceListener;
import org.photon.fooddeliverymobil.link.HttpLink;
import org.photon.fooddeliverymobil.model.AddressModel;
import org.photon.fooddeliverymobil.model.OrderCompleteModel;
import org.photon.fooddeliverymobil.model.SendAddressModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddressActivity extends BaseActivity {


@Bind(R.id.address_activity_listView) ListView lstAddress ;
	@Bind(R.id.address_activity_button) Button btnComplete ;
	private  Long addressID ;
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address);
		ButterKnife.bind(this);

		AddressSL addressSL = new AddressSL(AddressActivity.this,postAddressServiceListener, HttpLink.getHttpLink());
		addressSL.sendAddressInfo(new SendAddressModel(FoodDeliveryApp.getCustomerId(),FoodDeliveryApp.getEmail(),FoodDeliveryApp.getToken() ));
		showProgress("FoodDelivery Bağlanıyor...");

		btnComplete.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
FoodDeliveryApp.setAddressId(String.valueOf(addressID));

				Intent intent = new Intent();
				intent.setClass(AddressActivity.this, OrderActivity.class);
				startActivity(intent);

			}
		});


	}
PostAddressServiceListener<String> postAddressServiceListener = new PostAddressServiceListener<String>() {
	@Override public void getAddressList(List<AddressModel> addressModels) {
		dismissProgress();
		lstAddress.setAdapter(new AddressListAdapter(AddressActivity.this,addressModels));
	}

	@Override public void onComplete(ResponseEventModel<String> onComplete) {

	}
};

	public AddressActivity setAddressID(Long addressID) {
		this.addressID = addressID;
		return this;
	}


}
