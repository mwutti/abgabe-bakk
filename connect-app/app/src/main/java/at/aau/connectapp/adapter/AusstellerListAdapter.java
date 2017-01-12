package at.aau.connectapp.adapter;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import at.aau.connectapp.MainActivity;
import at.aau.connectapp.R;
import at.aau.connectapp.beans.Aussteller;
import at.aau.connectapp.fragment.AusstellerListFragment;
import at.aau.connectapp.listener.MapScrollToPositionListener;
import at.aau.connectapp.listener.OnFavoriteClickListener;

public class AusstellerListAdapter extends BaseAdapter {
	private final static String TAG = "AustellerListAdapter";
	private Context context = null;
	List<Aussteller> aussteller= null;
	private int starResID; 
	private int starFavoriteResID;
	private int logoResID;
	private Button ausstellerFavoriteButton;
	private AusstellerListFragment fragment;
	private boolean favorite = false;
	

	public AusstellerListAdapter(Context context, List<Aussteller> aussteller, AusstellerListFragment fragment, boolean favorite){
		this.context = context;
		this.aussteller = aussteller;
		Log.d("TAG", this.aussteller.size() + "..");
		starResID = context.getResources().getIdentifier("star" , "drawable", context.getPackageName());
		starFavoriteResID = context.getResources().getIdentifier("star_favorite" , "drawable", context.getPackageName());
		this.fragment = fragment;
		this.favorite = favorite;
	}
	public void setFavorite(boolean b){
		this.favorite = b;
	}
	
	public boolean isFavorite(){
		return this.favorite;
	}
	
	public List<Aussteller> getAussteller(){
		return this.aussteller;
	}

	@Override
	public int getCount() {

		return this.aussteller.size();
	}

	@Override
	public Object getItem(int position) {
		return aussteller.get(position);
	}

	@Override
	public long getItemId(int position) {

		return aussteller.get(position).getId();
	}

	@Override
	/**
	 * @param convertView
	 *            The old view to overwrite, if one is passed
	 * @returns a ContactEntryView that holds wraps around an ContactEntry
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.ausstellerrowlayout, parent, false);
		
		logoResID = context.getResources().getIdentifier(aussteller.get(position).getLogo_name() , "drawable", context.getPackageName());		
		
		ImageView ausstellerLogo = (ImageView) rowView.findViewById(R.id.ausstellerLogo);		
		Button ausstellerMapButton = (Button) rowView.findViewById(R.id.buttonAusstellerMap);
		ausstellerFavoriteButton = (Button) rowView.findViewById(R.id.buttonAusstellerFavorite);
		ausstellerFavoriteButton.setOnClickListener(new OnFavoriteClickListener(this,aussteller.get(position).getId(),((Activity)fragment.getActivity())));		
		ausstellerMapButton.setText(aussteller.get(position).getStand_nummer());
		ausstellerLogo.setImageResource(logoResID);
		ausstellerMapButton.setOnClickListener(new MapScrollToPositionListener(this.fragment,getItemId(position),(MainActivity)fragment.getActivity()));
		checkFavorite(position);
		
		
		return rowView;
	}
	
	public void checkFavorite(int position){
		if(aussteller.get(position).getIs_favorite() == 0){
			ausstellerFavoriteButton.setBackgroundResource(starResID);
		}else
			ausstellerFavoriteButton.setBackgroundResource(starFavoriteResID);
	}
	

}
