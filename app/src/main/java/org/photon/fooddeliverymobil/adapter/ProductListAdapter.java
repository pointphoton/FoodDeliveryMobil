package org.photon.fooddeliverymobil.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.Pair;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import org.apache.commons.lang3.text.WordUtils;
import org.photon.fooddeliverymobil.R;
import org.photon.fooddeliverymobil.activity.ProductActivity;
import org.photon.fooddeliverymobil.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductListAdapter extends BaseAdapter {

	Context context;
	List<ProductModel> models;
	private TextView txtTotalPrice ;
	private Double price = 0D;
private ProductActivity productActivity ;

	SparseBooleanArray sparseBooleanArray = new SparseBooleanArray(10);

	public ProductListAdapter(Activity activity, List<ProductModel> models) {
		productActivity=(ProductActivity)activity ;
		context = activity;
		this.models = models;

productActivity.productIdList =new ArrayList<>(models.size());
		for (int i=0;i<models.size();i++){
			productActivity.productIdList.add(new Pair<Long, String>(0L,""));
		}

txtTotalPrice=(TextView)activity.findViewById(R.id.product_activity_total_price_textView);

	}

	@Override public int getCount() {
	int size= models.size();

		return size;
	}

	@Override public Object getItem(int position) {
		return models.get(position);
	}

	@Override public long getItemId(int position) {
		return position;
	}

	@Override public View getView(final int position, View convertView, ViewGroup parent) {


		final ViewHolder holder;
		if (convertView != null) {
			holder = (ViewHolder) convertView.getTag();

		} else {
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.row_product, parent, false);

			holder = new ViewHolder(convertView);
			convertView.setTag(holder);


		}

		final ProductModel model = (ProductModel)getItem(position);
		holder.txtName.setText((model.getName() == null || model.getName().equals("")) ? holder.txtName.getText().toString() : WordUtils.capitalize(model.getName()));
		holder.txtType.setText((model.getDescription() == null || model.getDescription().equals("")) ? holder.txtType.getText().toString() : WordUtils.capitalize(model.getDescription()));
		holder.txtPrice.setText(model.getCurrentPrice().toString());
		if (position%4==0){
			holder.imgThumbnail.setImageResource(R.mipmap.a001);
		}
		else if (position%3==0){
			holder.imgThumbnail.setImageResource(R.mipmap.a002);

		}
		else if (position%2==0){
			holder.imgThumbnail.setImageResource(R.mipmap.a003);

		}
		else {

			holder.imgThumbnail.setImageResource(R.mipmap.a004);
		}

		holder.checkBox.setChecked(sparseBooleanArray.get(position));
		holder.checkBox.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				Log.d("position ", position + "selected  +  " + holder.txtPrice.getText().toString());
				if (holder.checkBox.isChecked()) {

					sparseBooleanArray.append(position, true);
					Log.d("sparse if true ", sparseBooleanArray.get(position) + "");
					price += Double.parseDouble(holder.txtPrice.getText().toString());
					txtTotalPrice.setText(String.valueOf(price));
					productActivity.productIdList.add(position, new Pair<>(model.getProductId(), model.getName()));
				} else {

					sparseBooleanArray.append(position, false);
					Log.d("sparse else ", sparseBooleanArray.get(position) + "");

					price -= Double.parseDouble(holder.txtPrice.getText().toString());
					txtTotalPrice.setText(String.valueOf(price));
					productActivity.productIdList.remove(position);
				}

			}
		});


		Log.d("sparse  "+position+" ",sparseBooleanArray.get(position)+"");
		/*
		holder.checkBox
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


					@Override
					public void onCheckedChanged(CompoundButton vw,
												 boolean isChecked) {



						if (isChecked) {
							Log.d("position ", position + "selected  +  " + holder.txtPrice.getText().toString());
							price+= Double.parseDouble( holder.txtPrice.getText().toString());
							txtTotalPrice.setText(String.valueOf(price));
productActivity.productIdList.add(model.getProductId());
							checkBoxState[position]=true;
						}
						else {
							Log.d("position ", position + "selected  -  " + holder.txtPrice.getText().toString());
							price-= Double.parseDouble( holder.txtPrice.getText().toString());
							txtTotalPrice.setText(String.valueOf(price));
							productActivity.productIdList.remove(model.getProductId());
							checkBoxState[position]=false;
						}
					}
				});

*/


		return convertView;
	}


	static class ViewHolder {
		@Bind(R.id.txtName) TextView txtName;
		@Bind(R.id.txtType) TextView txtType;
		@Bind(R.id.txtPrice) TextView txtPrice;
@Bind(R.id.product_checkBox) CheckBox checkBox ;
		@Bind(R.id.imgProduct) ImageView imgThumbnail;


		public ViewHolder(View view) {
			ButterKnife.bind(this, view);
		}
	}

}
