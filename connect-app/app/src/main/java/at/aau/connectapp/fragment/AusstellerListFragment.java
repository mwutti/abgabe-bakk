package at.aau.connectapp.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import at.aau.connectapp.MainActivity;
import at.aau.connectapp.R;
import at.aau.connectapp.adapter.AusstellerListAdapter;
import at.aau.connectapp.beans.Aussteller;
import at.aau.connectapp.dao.AusstellerDao;
import at.aau.connectapp.listener.CompanyListListener;
import at.aau.connectapp.listener.SearchListener;


public class AusstellerListFragment extends Fragment {
	static final String TAG = "AustellerProfileFragment";
	private View rootView;	
	private AusstellerDao aDao;
	private List<Aussteller> aussteller;
	private ListView list;

	public static AusstellerListFragment newInstance(int sectionNumber, boolean search) {
		AusstellerListFragment fragment = new AusstellerListFragment();
		Bundle args = new Bundle();
		args.putInt(MainActivity.ARG_SECTION_NUMBER, sectionNumber);
		if(search)
			args.putBoolean("search", true);
		else
			args.putBoolean("search", false);
		fragment.setArguments(args);		
		return fragment;
	}

	public AusstellerListFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_aussteller_list, container, false);


		aDao = getDao();
		if(getArguments().getBoolean("search")){
			aussteller = SearchListener.getResults();
		}else
			aussteller = getAllAussteller();

		//GetList and set adapter/onClickListener
		list = (ListView) rootView.findViewById(R.id.ausstellerList);				
		AusstellerListAdapter adapter = new AusstellerListAdapter(rootView.getContext(), aussteller, this,false);		
		list.setAdapter(adapter);
		list.setOnItemClickListener(new CompanyListListener(this,rootView.getContext(),(MainActivity)getActivity()));
		((MainActivity) getActivity()).restoreActionBar();


		return rootView;

	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(
				getArguments().getInt(MainActivity.ARG_SECTION_NUMBER));


	}
	/**
	 * 
	 * @return all Aussteller in db sortet by Ausstellername
	 */
	private List<Aussteller> getAllAussteller(){		
		ArrayList<Aussteller>aussteller = new ArrayList<Aussteller>();
		aDao.open();		
		aussteller = (ArrayList<Aussteller>) aDao.getAllAussteller();		
		aDao.close();	
		Collections.sort(aussteller);
		return aussteller;

	}
	protected AusstellerDao getDao(){
		return new AusstellerDao(rootView.getContext());
	}
	public AusstellerDao getaDao() {
		return aDao;
	}

	public void setRootView(View rootView) {
		this.rootView = rootView;
	}

	public void setAussteller(List<Aussteller> aussteller) {
		this.aussteller = aussteller;
	}

	public View getRootView() {
		return rootView;
	}

	public List<Aussteller> getAussteller() {
		return aussteller;
	}

	public void setaDao(AusstellerDao aDao) {
		this.aDao = aDao;
	}

	public ListView getList() {
		return list;
	}

	public void setList(ListView list) {
		this.list = list;
	}


}
