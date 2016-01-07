package org.photon.fooddeliverymobil.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import org.apache.commons.lang3.text.WordUtils;
import org.photon.fooddeliverymobil.R;
import org.photon.fooddeliverymobil.model.BranchModel;
import org.photon.fooddeliverymobil.model.ProductModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * TODO: Add a class header comment!
 */
public class RestaurantListAdapter extends BaseAdapter {

	Context context;
	List<BranchModel> models;
	private TextView txtSelectedBranch ;
	private Button btnCon;


	public RestaurantListAdapter(Activity activity, List<BranchModel> models) {
		context = activity;
		this.models = models;
		txtSelectedBranch=(TextView)activity.findViewById(R.id.restaurant_activity_selectedBranch_textView);
		btnCon = (Button) activity.findViewById(R.id.restaurant_activity_button);
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
			convertView = layoutInflater.inflate(R.layout.row_restaurant, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);

		}
		final BranchModel model = (BranchModel)getItem(position);
		holder.txtName.setText((model.getBranchName() == null || model.getBranchName().equals("")) ? holder.txtName.getText().toString() : WordUtils.capitalize(model.getBranchName()));

		convertView.setOnClickListener(new View.OnClickListener() {

		@Override public void onClick(View v) {

			txtSelectedBranch.setTag(model.getBranchId());
				txtSelectedBranch.setText(holder.txtName.getText().toString());
			btnCon.setEnabled(true);

			}
		});



		return convertView;
	}


	static class ViewHolder {
		@Bind(R.id.row_restaurant_activity_txtName) TextView txtName;
		//@Bind(R.id.row_restaurant_radioBtn) RadioButton radioButton ;



		public ViewHolder(View view) {
			ButterKnife.bind(this, view);
		}
	}

}
