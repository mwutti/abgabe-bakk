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
import at.aau.connectapp.fragment.ImpulsvortraegeFragment;
import at.aau.connectapp.listener.MapScrollToPositionListener;

public class ImpulsvortraegeListAdapter extends BaseAdapter {
	private final static String TAG = "ImpulsvortraegeListAdapter";
	private Context context = null;
	List<Impulsvortrag> imp= null;	
	private ImpulsvortraegeFragment fragment;
	
	public ImpulsvortraegeListAdapter(Context context, List<Impulsvortrag> imp, ImpulsvortraegeFragment fragment){
		this.context = context;
		this.imp = imp;		
		this.fragment = fragment;
	}

	@Override
	public int getCount() {
		
		return imp.size();
	}

	@Override
	public Object getItem(int position) {
		
		return imp.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return imp.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.impulsvortraege_row_layout, parent, false);
		
		
		Button ImpulsvortraegeMapButton = (Button) rowView.findViewById(R.id.buttonImpulsvortraegeMap);
		TextView von = (TextView) rowView.findViewById(R.id.von);
		TextView bis = (TextView) rowView.findViewById(R.id.bis);
		TextView header = (TextView) rowView.findViewById(R.id.header);
		TextView description = (TextView) rowView.findViewById(R.id.description);
		
		
		von.setText(imp.get(position).getVon() + "-");
		bis.setText(imp.get(position).getBis());
		header.setText(imp.get(position).getName());
		description.setText(imp.get(position).getBeschreibung());
		ImpulsvortraegeMapButton.setText("HS "+Integer.valueOf(imp.get(position).getStandNr())%100);		
		ImpulsvortraegeMapButton.setOnClickListener(new MapScrollToPositionListener(this.fragment,getItemId(position),(MainActivity)fragment.getActivity()));		
		
		return rowView;
	}

}
