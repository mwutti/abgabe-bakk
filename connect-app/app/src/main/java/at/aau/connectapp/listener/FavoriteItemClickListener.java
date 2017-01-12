package at.aau.connectapp.listener;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import at.aau.connectapp.MainActivity;
import at.aau.connectapp.R;
import at.aau.connectapp.beans.Aussteller;
import at.aau.connectapp.dao.AusstellerDao;
import at.aau.connectapp.fragment.CompanyProfileFragment;
import at.aau.connectapp.fragment.EducationalProfileFragment;
import at.aau.connectapp.fragment.MinimalProfileFragment;

public class FavoriteItemClickListener implements OnItemClickListener {
	private static final String TAG = "FavoriteItemClickListener";
	private Fragment fragment;
	private long targetId;
	private Context context;
	private MainActivity main;
	
	public FavoriteItemClickListener(Fragment fragment,Context context, MainActivity main){
		this.fragment = fragment;		
		this.context = context;
		this.main = main;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		this.targetId = id;
		checkAusstellerType(id);
		
		

	}
	
	private void checkAusstellerType(long id){
		Aussteller aus;
		AusstellerDao aDao = new AusstellerDao(context);
		aDao.open();
		aus = aDao.findAusstellerByID(id);
		aDao.open();
		main.setmTitle("Aussteller");
		main.pushToBackStackHeader("Favoriten");
		main.restoreActionBar();
		
		if(aus.getType().equals("A")){
			openCompanyProfileFragment();
		}else if(aus.getType().equals("C")){
			openMinimalProfileFragment();
		}else if(aus.getType().equals("B")){
			openEducationalProfileFragment();
		}
	}
	private void openCompanyProfileFragment(){
		FragmentManager fm = fragment.getFragmentManager();
		fm.beginTransaction()
		.add(CompanyProfileFragment.newInstance(1,this.targetId), TAG)
		.addToBackStack(null)
		.replace(R.id.container, CompanyProfileFragment.newInstance(1,this.targetId))
		.commit();	
	}
	
	private void openEducationalProfileFragment(){
		FragmentManager fm = fragment.getFragmentManager();
		fm.beginTransaction()
		.add(EducationalProfileFragment.newInstance(1,this.targetId), TAG)
		.addToBackStack(null)
		.replace(R.id.container, EducationalProfileFragment.newInstance(1,this.targetId))
		.commit();	
	}
	
	private void openMinimalProfileFragment(){
		FragmentManager fm = fragment.getFragmentManager();
		fm.beginTransaction()
		.add(MinimalProfileFragment.newInstance(1,this.targetId), TAG)
		.addToBackStack(null)
		.replace(R.id.container, MinimalProfileFragment.newInstance(1,this.targetId))
		.commit();	
	}

}
