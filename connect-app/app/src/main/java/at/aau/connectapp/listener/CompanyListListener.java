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
import at.aau.connectapp.beans.Company;
import at.aau.connectapp.dao.AusstellerDao;
import at.aau.connectapp.dao.CompanyDao;
import at.aau.connectapp.fragment.CompanyProfileFragment;
import at.aau.connectapp.fragment.EducationalProfileFragment;
import at.aau.connectapp.fragment.MinimalProfileFragment;

public class CompanyListListener implements OnItemClickListener{
	private static final String TAG = "CompanyListListener";
	private AusstellerDao aDao;
	private CompanyDao cDao;
	private Fragment fragment;
	private Context context;
	private MainActivity main;
	
	public CompanyListListener(Fragment fragment, Context context, MainActivity main){
		this.fragment = fragment;
		this.context = context;		
		this.main = main;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		main.pushToBackStackHeader("Aussteller");
		String type = getAusstellerByID(id).getType();		
		//switch statement doesn't work with String constants. on jre 1.6.
		if(type.equals("A")){
			openCompanyProfile(id);
		}else if(type.equals("C")){
			openMinimalProfile(id);
		}else if(type.equals("B")){
			openEducationalProfile(id);
		}
		
		
		
	}
	
	private void openCompanyProfile(long id){
		FragmentManager fm = fragment.getFragmentManager();
		fm.beginTransaction()
		.add(CompanyProfileFragment.newInstance(1,id), TAG)
		.addToBackStack(null)
		.replace(R.id.container, CompanyProfileFragment.newInstance(1,id))
		.commit();
	}
	
	private void openMinimalProfile(long id){
		//Company  comp = getCompanyByID(id);
		FragmentManager fm = fragment.getFragmentManager();
		fm.beginTransaction()
		.add(MinimalProfileFragment.newInstance(1,id), TAG)
		.addToBackStack(null)
		.replace(R.id.container, MinimalProfileFragment.newInstance(1,id))
		.commit();
	}
	
	private void openEducationalProfile(long id){
		FragmentManager fm = fragment.getFragmentManager();
		fm.beginTransaction()
		.add(EducationalProfileFragment.newInstance(1,id), TAG)
		.addToBackStack(null)
		.replace(R.id.container,EducationalProfileFragment.newInstance(1,id))
		.commit();
		
	}
	private Aussteller getAusstellerByID(long id){
		if(aDao == null){
			aDao = new AusstellerDao(context);
		}
		aDao.open();
		Aussteller aussteller = aDao.findAusstellerByID(id);
		aDao.close();
		return aussteller;

	}
	
	private Company getCompanyByID(long id){
		if(cDao == null){
			cDao = new CompanyDao(context);
		}
		cDao.open();
		Company comp = cDao.findCompanyByID(id);
		cDao.close();
		return comp;

	}

}
