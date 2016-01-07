package org.photon.fooddeliverymobil.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;
import org.photon.fooddeliverymobil.R;
import org.photon.fooddeliverymobil.application.FoodDeliveryApp;
import org.photon.fooddeliverymobil.connection.ResponseEventModel;
import org.photon.fooddeliverymobil.connection.service.OrderSL;
import org.photon.fooddeliverymobil.connection.service.PostOrderServiceListener;
import org.photon.fooddeliverymobil.link.HttpLink;
import org.photon.fooddeliverymobil.model.OrderCompleteModel;
import org.photon.fooddeliverymobil.model.OrderMessageModel;

import java.text.SimpleDateFormat;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OrderActivity extends BaseActivity {

	@Bind(R.id.order_activity_orderMessage_textView) TextView txtMessage;
	@Bind(R.id.order_activity_orderTime_textView) TextView txtTime;
	@Bind(R.id.order_activity_orderNo_textView) TextView txtOrderNo ;
	@Bind(R.id.order_activity_orderTotal_textView) TextView txtTotal ;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		ButterKnife.bind(this);
		OrderCompleteModel model = new OrderCompleteModel();
		model.setCustomerId(FoodDeliveryApp.getCustomerId());
		model.setEmail(FoodDeliveryApp.getEmail());
		model.setToken(FoodDeliveryApp.getToken());
		writeLog("token in order", FoodDeliveryApp.getToken() + "*");
		model.setAddressId(FoodDeliveryApp.getAddressId());
		model.setBranchName(FoodDeliveryApp.getBranchName());
		model.setSendProModels(FoodDeliveryApp.getSendProModels());
		model.setBranchId(FoodDeliveryApp.getBranchId());
		OrderSL orderSL = new OrderSL(OrderActivity.this,orderServiceListener, HttpLink.getHttpLink());
		orderSL.sendOrder(model);
		showProgress("FoodDelivery Bağlanıyor...");

	}

	PostOrderServiceListener<String> orderServiceListener = new PostOrderServiceListener<String>() {
		@Override public void getOrderMessage(OrderMessageModel orderMessageModel) {
			dismissProgress();
			writeLog("message ", "es " + orderMessageModel.getOrderMessage());
			txtMessage.setText(orderMessageModel.getOrderMessage());
			txtOrderNo.setText(String.valueOf(orderMessageModel.getOrderId()));
			txtTotal.setText(String.valueOf(orderMessageModel.getTotalPrice())+" TL") ;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
			String d = sdf.format(orderMessageModel.getOrderTime());
			txtTime.setText(d);
		}

		@Override public void onComplete(ResponseEventModel<String> onComplete) {

		}
	};
}
