package at.aau.connectapp.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import at.aau.connectapp.MainActivity;
import at.aau.connectapp.R;
import at.aau.connectapp.adapter.AusstellerListAdapter;
import at.aau.connectapp.adapter.SpecialListAdapter;
import at.aau.connectapp.beans.Special;
import at.aau.connectapp.dao.SpecialDao;
import at.aau.connectapp.listener.CompanyListListener;

public class SpecialsFragment extends Fragment{
	static final String TAG = "SpecialsFragment";
	private View rootView;	
	private SpecialDao sDao;
	private List<Special> specials;
	private ListView list;
	
	public static SpecialsFragment newInstance(int sectionNumber) {
		SpecialsFragment fragment = new SpecialsFragment();
		Bundle args = new Bundle();
		args.putInt(MainActivity.ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);		
		return fragment;
	}

	public SpecialsFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_specials, container, false);
		
		sDao = getDao();
		specials = getAllSpecials();
		
		//GetList and set adapter/onClickListener
		list = (ListView) rootView.findViewById(R.id.specialList);				
		SpecialListAdapter adapter = new SpecialListAdapter(rootView.getContext(), specials, this);		
		list.setAdapter(adapter);
		//list.setOnItemClickListener(new CompanyListListener(this,rootView.getContext()));
		return rootView;
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(
				getArguments().getInt(MainActivity.ARG_SECTION_NUMBER));
	}
//	/**
//	 * 
//	 * @return all Aussteller in db sortet by Ausstellername
//	 */
	private List<Special> getAllSpecials(){		
		ArrayList<Special>specials = new ArrayList <Special>();
		sDao.open();		
		specials = (ArrayList<Special>) sDao.getAllSpecials();		
		sDao.close();			
		return specials;
		
	}
	protected SpecialDao getDao(){
		return new SpecialDao(rootView.getContext());
	}
//	public AusstellerDao getaDao() {
//		return aDao;
//	}
//
//	public void setRootView(View rootView) {
//		this.rootView = rootView;
//	}
//
//	public void setAussteller(List<Aussteller> aussteller) {
//		this.aussteller = aussteller;
//	}
//
//	public View getRootView() {
//		return rootView;
//	}
//
//	public List<Aussteller> getAussteller() {
//		return aussteller;
//	}
//
//	public void setaDao(AusstellerDao aDao) {
//		this.aDao = aDao;
//	}
//	
//	public ListView getList() {
//		return list;
//	}
//
//	public void setList(ListView list) {
//		this.list = list;
//	}
}
