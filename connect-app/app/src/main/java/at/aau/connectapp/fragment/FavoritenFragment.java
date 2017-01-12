package at.aau.connectapp.fragment;

import java.util.ArrayList;
import java.util.Collections;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import at.aau.connectapp.MainActivity;
import at.aau.connectapp.R;
import at.aau.connectapp.adapter.AusstellerListAdapter;
import at.aau.connectapp.beans.Aussteller;
import at.aau.connectapp.listener.FavoriteItemClickListener;


public class FavoritenFragment extends AusstellerListFragment {
	private static boolean flag = true;
	
	
	public static FavoritenFragment newInstance(int sectionNumber) {
		FavoritenFragment fragment = new FavoritenFragment();
		Bundle args = new Bundle();
		args.putInt(MainActivity.ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setRootView(inflater.inflate(R.layout.fragment_aussteller_list, container, false));
		View rootView = inflater.inflate(R.layout.fragment_aussteller_list, container, false);
		
		setaDao(getDao());
		setAussteller(getFavoriteAussteller());
		
		//GetList and set adapter/onClickListener
		setList((ListView) getRootView().findViewById(R.id.ausstellerList));				
		AusstellerListAdapter adapter = new AusstellerListAdapter(getRootView().getContext(), getAussteller(),this,true);		
		getList().setAdapter(adapter);
		getList().setOnItemClickListener(new FavoriteItemClickListener(this, getRootView().getContext(),(MainActivity)getActivity()));	
		
		
		
		if(flag == true && adapter.getAussteller().size() == 0 ){
			 AlertDialog alertDialog = new AlertDialog.Builder(
	                    getActivity()).create();

	    // Setting Dialog Title
	    alertDialog.setTitle("Noch keine Favoriten hinzugefügt");

	    // Setting Dialog Message
	    alertDialog.setMessage("Fügen Sie Favoriten durch drücken auf den Stern in der Ausstellerliste hinzu.");

	    // Setting OK Button
	    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	           
	           
	            }
	    });

	    // Showing Alert Message
	    alertDialog.show();
	    flag = false;
		}
		
			
		
		
		return getRootView();
	}
	private ArrayList<Aussteller> getFavoriteAussteller(){
		ArrayList<Aussteller> favoriteAussteller= null;
		getaDao().open();
		favoriteAussteller = getaDao().getFavoriteAussteller();
		getaDao().close();
		Collections.sort(favoriteAussteller);
		return favoriteAussteller;
	}
	
}
