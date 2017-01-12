package at.aau.connectapp.fragment;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

import at.aau.connectapp.MainActivity;
import at.aau.connectapp.R;
import at.aau.connectapp.adapter.ImpulsvortraegeListAdapter;
import at.aau.connectapp.beans.Impulsvortrag;
import at.aau.connectapp.dao.ImpulsvortraegeDao;

public class ImpulsvortraegeFragment extends Fragment {
	static final String TAG = "ImpulsvortraegeFragment";
	private View rootView;	
	private ImpulsvortraegeDao iDao;
	private List<Impulsvortrag> imp;
	private ListView list;
	
	public static ImpulsvortraegeFragment newInstance(int sectionNumber) {
		ImpulsvortraegeFragment fragment = new ImpulsvortraegeFragment();
		Bundle args = new Bundle();
		args.putInt(MainActivity.ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);		
		return fragment;
	}

	public ImpulsvortraegeFragment() {
	}
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_impulsvortraege, container, false);
		((MainActivity)getActivity()).restoreActionBar();
		
		iDao = getDao();
		iDao.open();
		imp = iDao.getAllImpulsvortraege();
		iDao.close();
		
		
		//GetList and set adapter/onClickListener
		list = (ListView) rootView.findViewById(R.id.ImpulsvortraegeList);				
		ImpulsvortraegeListAdapter adapter = new ImpulsvortraegeListAdapter(rootView.getContext(), imp, this);		
		list.setAdapter(adapter);
//		list.setOnItemClickListener(new CompanyListListener(this,rootView.getContext()));
		return rootView;
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(
				getArguments().getInt(MainActivity.ARG_SECTION_NUMBER));
	}
	

	protected ImpulsvortraegeDao getDao(){
		return iDao == null ? new ImpulsvortraegeDao(rootView.getContext()) : iDao;
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
