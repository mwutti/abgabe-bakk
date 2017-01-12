package at.aau.connectapp.listener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;

import at.aau.connectapp.MainActivity;
import at.aau.connectapp.adapter.AusstellerListAdapter;
import at.aau.connectapp.beans.Aussteller;
import at.aau.connectapp.dao.AusstellerDao;

public class OnFavoriteClickListener implements OnClickListener {
	private static final String TAG = "OnFavoriteClick-Listener";
	private int id;
	private AusstellerDao dao;
	private Aussteller aussteller;
	private int starResID = 0;
	private int starFavoriteResID= 0;
	private AusstellerListAdapter adapter;
	private Activity main;
	private Fragment fragment;
	


	public OnFavoriteClickListener(int id){
		this.id = id;
		;

	}
	public OnFavoriteClickListener(AusstellerListAdapter adapter , int id, Activity main){
		this.adapter = adapter;
		this.id = id;
		this.main = main;
		
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		if (dao == null){
			dao = new AusstellerDao(v.getContext());
		}
		if(starResID == 0){
			starResID = v.getContext().getResources().getIdentifier("star", "drawable", v.getContext().getPackageName());
		}
		if(starFavoriteResID == 0){
			starFavoriteResID = v.getContext().getResources().getIdentifier("star_favorite", "drawable", v.getContext().getPackageName());
		}

		int isFavorite = getAusstellerFavorite(id);
		if(isFavorite == 0){
			setFavorite(1);
			v.setBackgroundResource(starFavoriteResID);
			//adapter.notifyDataSetChanged();

		}else{
			
					
			/**
			 * If the calling Fragment is the favoriteListFragment, delete item in adapter to make notifyDataSetChanged work
			 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			 */
			if(adapter != null && adapter.isFavorite()){

				AlertDialog alertDialog = new AlertDialog.Builder(
						main).create();

				// Setting Dialog Title
				alertDialog.setTitle("Favorit löschen");

				// Setting Dialog Message
				String aName = getAusstellerById(id).getName();
				alertDialog.setMessage("Wollen Sie "+ aName + " aus Favoriten entfernen?");			
				

				// Setting OK Button
				alertDialog.setButton("OK", new FavoriteConfirm(adapter,this.id, (MainActivity)main));
				//Set Cancel Button
				alertDialog.setButton2("Abbrechen",new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
					
				});

				// Showing Alert Message
				alertDialog.show();
				
			}else{
				setFavorite(0);
				v.setBackgroundResource(starResID);
			}


		}		

	}
	private void setFavorite(int f){
		dao.open();
		dao.setFavorite(f, this.id);
		dao.close();

	}
	private int getAusstellerFavorite(int id){
		dao.open();
		aussteller = dao.findAusstellerByID(id);
		dao.close();

		return aussteller.getIs_favorite();
	}
	private Aussteller getAusstellerById(int id){
		dao.open();
		Aussteller aus = dao.findAusstellerByID(id);
		dao.close();
		return aus;
	}


}
