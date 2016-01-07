package org.photon.fooddeliverymobil.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.photon.fooddeliverymobil.R;
import org.photon.fooddeliverymobil.adapter.ProductListAdapter;
import org.photon.fooddeliverymobil.adapter.RestaurantListAdapter;
import org.photon.fooddeliverymobil.application.FoodDeliveryApp;
import org.photon.fooddeliverymobil.connection.ResponseEventModel;
import org.photon.fooddeliverymobil.connection.service.BranchSL;
import org.photon.fooddeliverymobil.connection.service.GetBranchServiceListener;
import org.photon.fooddeliverymobil.connection.service.ProductSL;
import org.photon.fooddeliverymobil.link.HttpLink;
import org.photon.fooddeliverymobil.model.BranchModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RestaurantActivity extends BaseActivity {


	@Bind(R.id.restaurant_activity_selectedBranch_textView) TextView txtSelectedBranch ;
	@Bind(R.id.restaurant_activity_listView) ListView lstRestaurant ;
	@Bind(R.id.restaurant_activity_button) Button btnRestaurant ;

//	private List<BranchModel> branchModelList ;

	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant);
		ButterKnife.bind(this);
		lstRestaurant.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
btnRestaurant.setEnabled(false);
	/*
		branchModelList = new ArrayList<>();
		BranchModel b1 = new BranchModel();
		BranchModel b2 = new BranchModel();
		BranchModel b3 = new BranchModel();
		BranchModel b4 = new BranchModel();
		b1.setBranchName("İstanbul");
		b2.setBranchName("Ankara");
		b3.setBranchName("Bursa");
		b4.setBranchName("Antalya");
		branchModelList.add(b1);
		branchModelList.add(b2);
		branchModelList.add(b3);
		branchModelList.add(b4);
		*/
		//lstRestaurant.setAdapter(new RestaurantListAdapter(RestaurantActivity.this, branchModelList));

		BranchSL productSL = new BranchSL(RestaurantActivity.this,getBranchServiceListener, HttpLink.getHttpLink());
		productSL.setGetBranchList("");
		showProgress("FoodDelivery Bağlanıyor...");


btnRestaurant.setOnClickListener(new View.OnClickListener() {
	@Override public void onClick(View v) {
		Log.d("Selected Branch : ",txtSelectedBranch.getText().toString());
		FoodDeliveryApp.setBranchId(txtSelectedBranch.getTag().toString());
		FoodDeliveryApp.setBranchName(txtSelectedBranch.getText().toString());
		Intent intent = new Intent();
		intent.setClass(RestaurantActivity.this, ProductActivity.class);
		startActivity(intent);
	}
});

	}

	GetBranchServiceListener<String> getBranchServiceListener = new GetBranchServiceListener<String>() {
		@Override public void getBranchList(List<BranchModel> branchModelList) {
			dismissProgress();
			lstRestaurant.setAdapter(new RestaurantListAdapter(RestaurantActivity.this, branchModelList));
		}

		@Override public void onComplete(ResponseEventModel<String> onComplete) {

		}
	};





}
