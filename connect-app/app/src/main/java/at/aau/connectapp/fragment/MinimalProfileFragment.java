package at.aau.connectapp.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import at.aau.connectapp.MainActivity;
import at.aau.connectapp.R;
import at.aau.connectapp.beans.Aussteller;
import at.aau.connectapp.dao.AusstellerDao;
import at.aau.connectapp.listener.HomepageListener;
import at.aau.connectapp.listener.MapScrollToPositionListener;
import at.aau.connectapp.listener.OnFavoriteClickListener;

public class MinimalProfileFragment extends Fragment {
	private View rootView;
	private Context context;
	private static final String GETCOMPANY = "getcompany";
	private static final String TAG ="CompanyProfileFragment";
	private AusstellerDao aDao;
	private Aussteller target;
	private int starResID = 0;
	private int starFavoriteResID= 0;

	public static MinimalProfileFragment newInstance(int sectionNumber, long ausstellerID) {
		MinimalProfileFragment fragment = new MinimalProfileFragment();
		Bundle args = new Bundle();
		args.putInt(MainActivity.ARG_SECTION_NUMBER, sectionNumber);
		args.putLong(GETCOMPANY, ausstellerID);
		fragment.setArguments(args);

		return fragment;
	}

	public MinimalProfileFragment(){
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_minimal_profile, container, false);
		long id = getArguments().getLong(GETCOMPANY);
		this.context = this.rootView.getContext();		
		if(starResID == 0){
			starResID = context.getResources().getIdentifier("star", "drawable", context.getPackageName());
		}
		if(starFavoriteResID == 0){
			starFavoriteResID = context.getResources().getIdentifier("star_favorite", "drawable", context.getPackageName());
		}

		aDao = getAusstellerDao();

		this.target = findAusstellerByID(id);
		Log.d(TAG,"Company "+ target.getName());

		fillData(target);

		return rootView;
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(
				getArguments().getInt(MainActivity.ARG_SECTION_NUMBER));
	}

	private AusstellerDao getAusstellerDao(){
		return this.aDao == null ? new AusstellerDao(this.rootView.getContext()) : this.aDao;
	}
	/**
	 * returns a company specified by its unique id 
	 * @param the unique ausstellerid
	 * @return company with the given id
	 */
	private Aussteller findAusstellerByID(long id){
		Aussteller result = null;
		aDao.open();
		result = aDao.findAusstellerByID(id);
		aDao.close();		
		return result;		
	}

	/**
	 * Fills the views of CompanyProfileFragment with data of given company
	 * @param c the company to display
	 */
	private void fillData(Aussteller a){
		int companyLogoResId = context.getResources().getIdentifier(a.getLogo_name(), "drawable", context.getPackageName());
//

		ImageView logo = (ImageView) rootView.findViewById(R.id.logoCompany);
		Button companyMap = (Button) rootView.findViewById(R.id.companyMapButton);		
		Button companyFavorite = (Button) rootView.findViewById(R.id.companyFavoriteButton);

		logo.setBackgroundResource(companyLogoResId);
		companyMap.setText(a.getStand_nummer());
		companyMap.setOnClickListener(new MapScrollToPositionListener(this, a.getId(), (MainActivity)this.getActivity()));
		companyFavorite.setOnClickListener(new OnFavoriteClickListener(a.getId()));

		if(a.getIs_favorite() == 0){
			companyFavorite.setBackgroundResource(starResID);
		}else
			companyFavorite.setBackgroundResource(starFavoriteResID);




		TextView internet = (TextView) rootView.findViewById(R.id.contentInternet);
		TextView beschreibung = (TextView) rootView.findViewById(R.id.contentBeschreibung);
		
		SpannableString spanString = new SpannableString(a.getInternet());
		spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), 0);
		
		internet.setText(spanString);
		beschreibung.setText(a.getBeschreibung());
		
		
		
		internet.setTextColor(getResources().getColor(R.color.lightblue));
		internet.setOnClickListener(new HomepageListener(internet.getText().toString(),(MainActivity) getActivity()));
		
		

	}
}
