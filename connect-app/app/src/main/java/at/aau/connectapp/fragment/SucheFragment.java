package at.aau.connectapp.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import at.aau.connectapp.MainActivity;
import at.aau.connectapp.R;
import at.aau.connectapp.dao.AusstellerDao;
import at.aau.connectapp.listener.SearchListener;

public class SucheFragment extends Fragment {
	
	private View rootView;	
	private AusstellerDao aDao;
	private String searchString;
	
	
	public static SucheFragment newInstance(int sectionNumber) {
		SucheFragment fragment = new SucheFragment();
		Bundle args = new Bundle();
		args.putInt(MainActivity.ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);		
		return fragment;
	}
	
	public SucheFragment(){
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_suche, container, false);

		aDao = getDao();
		
		EditText input = (EditText) rootView.findViewById(R.id.inputSuche);
		Button button = (Button) rootView.findViewById(R.id.buttonSuche);		
		
		
		button.setOnClickListener(new SearchListener(getActivity(),input));		

		return rootView;

	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(
				getArguments().getInt(MainActivity.ARG_SECTION_NUMBER));


	}
	
	protected AusstellerDao getDao(){
		return new AusstellerDao(rootView.getContext());
	}

}
