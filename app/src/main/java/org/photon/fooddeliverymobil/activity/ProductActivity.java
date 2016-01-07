package org.photon.fooddeliverymobil.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.photon.fooddeliverymobil.R;
import org.photon.fooddeliverymobil.adapter.ProductListAdapter;
import org.photon.fooddeliverymobil.application.FoodDeliveryApp;
import org.photon.fooddeliverymobil.connection.ResponseEventModel;
import org.photon.fooddeliverymobil.connection.service.GetProductServiceListener;
import org.photon.fooddeliverymobil.connection.service.ProductSL;
import org.photon.fooddeliverymobil.link.HttpLink;
import org.photon.fooddeliverymobil.model.ProductModel;
import org.photon.fooddeliverymobil.model.OrderProductModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductActivity extends BaseActivity {

	@Bind(R.id.product_activity_listView) ListView listView ;
	@Bind(R.id.product_activity_button) Button btnProCont ;
	private List<ProductModel> productListModel =new ArrayList<>();
private List<ProductModel> sendModel;

	public List<Pair<Long,String>> productIdList ;
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_product);
		ButterKnife.bind(this);
		btnProCont.setEnabled(false);
		/*
		sendModel = new ArrayList<>();
		ProductModel productModel = new ProductModel();
		ProductModel productModel2 = new ProductModel();
		ProductModel productModel3 = new ProductModel();
		productModel.setName("Pro 1");

		productModel.setCurrentPrice(120d);
		productModel.setType("Pro Type 2");
		productModel2.setName("Pro 2");

		productModel2.setCurrentPrice(220d);
		productModel2.setType("Pro Type 33");
		productModel3.setName("Pro 33");

		productModel3.setCurrentPrice(320d);
		productModel3.setType("Pro Type 3");
		this.productListModel.add(productModel);
		this.productListModel.add(productModel2);
		this.productListModel.add(productModel3);


		listView.setAdapter(new ProductListAdapter(ProductActivity.this, this.productListModel));
*/

		ProductSL productSL = new ProductSL(ProductActivity.this,getProductServiceListener, HttpLink.getHttpLink());
		productSL.setGetProductList("");
		showProgress("FoodDelivery Bağlanıyor...");

		btnProCont.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				List<OrderProductModel> models = new ArrayList<OrderProductModel>();
				for ( Pair<Long,String> item : productIdList)
					  {
						  if (item.first>0) {
							  writeLog("selected product id ", " " + item.first);
							  writeLog("selected product name ", " " + item.second);
							  models.add(new OrderProductModel(String.valueOf(item.first), item.second));
						  }

				}
				FoodDeliveryApp.setSendProModels(models);
				Intent intent = new Intent();
				intent.setClass(ProductActivity.this, AddressActivity.class);
				startActivity(intent);
			}
		});


	}


	GetProductServiceListener<String> getProductServiceListener = new GetProductServiceListener<String>() {
		@Override public void getProductList(List<ProductModel> productListModelList) {
			dismissProgress();
			listView.setAdapter(new ProductListAdapter(ProductActivity.this,productListModelList));
			btnProCont.setEnabled(true);
		}

		@Override public void onComplete(ResponseEventModel<String> onComplete) {

		}


	};


}
