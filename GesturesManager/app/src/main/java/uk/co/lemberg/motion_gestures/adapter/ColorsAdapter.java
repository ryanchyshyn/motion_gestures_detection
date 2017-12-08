package uk.co.lemberg.motion_gestures.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import uk.co.lemberg.motion_gestures.R;
import uk.co.lemberg.motion_gestures.utils.Label;

public class ColorsAdapter extends ArrayAdapter<Label> {

	private static final class ViewHolder {
		public final TextView txtLabel;

		private ViewHolder(TextView txtLabel) {
			this.txtLabel = txtLabel;
		}
	}

	private final Context context;
	private final LayoutInflater inflater;

	public ColorsAdapter(Context context, List<Label> items) {
		super(context, android.R.layout.simple_spinner_dropdown_item, items);
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent)
	{
		TextView view = (TextView) super.getDropDownView(position, convertView, parent);
		Label item = getItem(position);

		view.setMinWidth((int) context.getResources().getDimension(R.dimen.minSpinnElemWidth));
		view.setBackgroundColor(item.color);
		view.setText(item.toString());

		return view;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		ViewHolder vh;

		if (convertView == null) {
			convertView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, null);

			vh = new ViewHolder((TextView) convertView.findViewById(android.R.id.text1));
			convertView.setTag(vh);
		}
		else {
			vh = (ViewHolder) convertView.getTag();
		}

		Label item = getItem(pos);
		fillViews(vh, item);

		return convertView;
	}

	private void fillViews(ViewHolder vh, Label item) {
		vh.txtLabel.setBackgroundColor(item.color);
		vh.txtLabel.setText(item.toString());
	}
}