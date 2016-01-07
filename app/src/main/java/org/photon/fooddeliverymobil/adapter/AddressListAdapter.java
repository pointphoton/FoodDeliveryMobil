package org.photon.fooddeliverymobil.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import org.apache.commons.lang3.text.WordUtils;
import org.photon.fooddeliverymobil.R;
import org.photon.fooddeliverymobil.activity.AddressActivity;
import org.photon.fooddeliverymobil.model.AddressModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddressListAdapter extends BaseAdapter {

	private AddressActivity addressActivity;
	Context context;
	private List<AddressModel> models;
	private int selectedPosition = 0;
	//private TextView txtSelectedBranch ;
	//private Button btnCon;


	public AddressListAdapter(Activity activity, List<AddressModel> models) {
		addressActivity = (AddressActivity)activity;
		context =activity ;
		this.models = models;
		addressActivity.setAddressID(models.get(0).getAddressId());
		//txtSelectedBranch=(TextView)activity.findViewById(R.id.restaurant_activity_selectedBranch_textView);
		//btnCon = (Button) activity.findViewById(R.id.restaurant_activity_button);
	}

	@Override public int getCount() {
		return models.size();
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
			convertView = layoutInflater.inflate(R.layout.row_address, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);

		}
		final AddressModel model = (AddressModel)getItem(position);

		String addressText =model.getAddressDistrict() == null ||model.getAddressDistrict().isEmpty() ? holder.txtAddress.getText().toString() : WordUtils.capitalize(model.getAddressDistrict());
		addressText += "\n" ;
		addressText +=model.getAddressText();
		holder.txtAddress.setText(addressText);
		holder.radioButton.setChecked(position == selectedPosition);
		holder.radioButton.setTag(position);

		holder.radioButton.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				selectedPosition = (Integer)v.getTag();
				Log.d("address id ",model.getAddressId().toString());
				addressActivity.setAddressID(model.getAddressId());
				notifyDataSetChanged();

}



});



		return convertView;
	}


	static class ViewHolder {
		@Bind(R.id.row_address_textView) TextView txtAddress;
		@Bind(R.id.row_address_radioBtn) RadioButton radioButton ;



		public ViewHolder(View view) {
			ButterKnife.bind(this, view);
		}
	}

}

