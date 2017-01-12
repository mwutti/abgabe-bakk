package at.aau.connectapp.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import at.aau.connectapp.MainActivity;
import at.aau.connectapp.R;
import at.aau.connectapp.beans.Impulsvortrag;
import at.aau.connectapp.beans.Special;
import at.aau.connectapp.fragment.ImpulsvortraegeFragment;
import at.aau.connectapp.fragment.SpecialsFragment;
import at.aau.connectapp.listener.MapScrollToPositionListener;

public class SpecialListAdapter extends BaseAdapter {
	private final static String TAG = "ImpulsvortraegeListAdapter";
	private Context context = null;
	List<Special> sp= null;	
	private SpecialsFragment fragment;
	
	public SpecialListAdapter(Context context, List<Special> sp, SpecialsFragment fragment){
		this.context = context;
		this.sp = sp;		
		this.fragment = fragment;
	}
	@Override
	public int getCount() {
		return sp.size();
	}

	@Override
	public Object getItem(int position) {
		return sp.get(position);
	}

	@Override
	public long getItemId(int position) {
		return sp.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.specials_row_layout, parent, false);
		
		
		Button SpecialMapButton = (Button) rowView.findViewById(R.id.buttonSpecialMap);		
		TextView description = (TextView) rowView.findViewById(R.id.description);		
		
		description.setText(sp.get(position).getBeschreibung());
		SpecialMapButton.setText(sp.get(position).getStandNR());		
		SpecialMapButton.setOnClickListener(new MapScrollToPositionListener(this.fragment,getItemId(position),(MainActivity)fragment.getActivity()));		
		
		return rowView;
	}

}
