package at.aau.connectapp.listener;

import android.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;

import at.aau.connectapp.MainActivity;
import at.aau.connectapp.R;
import at.aau.connectapp.fragment.CompanyProfileFragment;

public class MapPreviewListener implements OnClickListener {
	
	private int id;
	private Fragment fragment;
	private MainActivity act;
	
	public MapPreviewListener (int id, Fragment fragment,MainActivity act){
		this.id = id;
		this.fragment = fragment;
		this.act = act;
	}
	

	@Override
	public void onClick(View v) {
		//Close toast as quick as possible
		act.getToast().cancel();
		//and replace actual Fragment with CompanyProfileFragment
		fragment.getFragmentManager().beginTransaction()
		.add(CompanyProfileFragment.newInstance(id,id),"TAG")			
		.addToBackStack(null)
		.replace(R.id.container, CompanyProfileFragment.newInstance(id,id))
		.commit();
		

	}

}
