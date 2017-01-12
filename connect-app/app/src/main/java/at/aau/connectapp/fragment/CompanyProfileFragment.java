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
import at.aau.connectapp.beans.Company;
import at.aau.connectapp.dao.CompanyDao;
import at.aau.connectapp.listener.EmailListener;
import at.aau.connectapp.listener.HomepageListener;
import at.aau.connectapp.listener.MapScrollToPositionListener;
import at.aau.connectapp.listener.OnFavoriteClickListener;
import at.aau.connectapp.listener.TelefonListener;

public class CompanyProfileFragment extends Fragment {
	private View rootView;
	private Context context;
	private static final String GETCOMPANY = "getcompany";
	private static final String TAG ="CompanyProfileFragment";
	private CompanyDao cDao;
	private Company target;
	private int starResID = 0;
	private int starFavoriteResID= 0;

	public static CompanyProfileFragment newInstance(int sectionNumber, long ausstellerID) {
		CompanyProfileFragment fragment = new CompanyProfileFragment();
		Bundle args = new Bundle();
		args.putInt(MainActivity.ARG_SECTION_NUMBER, sectionNumber);
		args.putLong(GETCOMPANY, ausstellerID);
		fragment.setArguments(args);
		
		return fragment;
	}

	public CompanyProfileFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_company_profile, container, false);
		long id = getArguments().getLong(GETCOMPANY);
		this.context = this.rootView.getContext();		
		if(starResID == 0){
			starResID = context.getResources().getIdentifier("star", "drawable", context.getPackageName());
		}
		if(starFavoriteResID == 0){
			starFavoriteResID = context.getResources().getIdentifier("star_favorite", "drawable", context.getPackageName());
		}
				
		cDao = getCompanyDao();
		
		this.target = findCompanyByID(id);
		Log.d(TAG,"Company "+ target.getName());
		
		fillData(target);

		return rootView;
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(
				getArguments().getInt(MainActivity.ARG_SECTION_NUMBER));
	}

	private CompanyDao getCompanyDao(){
		return this.cDao == null ? new CompanyDao(this.rootView.getContext()) : this.cDao;
	}
	/**
	 * returns a company specified by its unique id 
	 * @param the unique ausstellerid
	 * @return company with the given id
	 */
	private Company findCompanyByID(long id){
		Company result = null;
		cDao.open();
		result = cDao.findCompanyByID(id);
		cDao.close();		
		return result;		
	}
	
	/**
	 * Fills the views of CompanyProfileFragment with data of given company
	 * @param c the company to display
	 */
	private void fillData(Company c){
		int personImageResId = context.getResources().getIdentifier(c.getPhoto_name(), "drawable", context.getPackageName());
		int companyLogoResId = context.getResources().getIdentifier(c.getLogo_name(), "drawable", context.getPackageName());
		
		ImageView person = (ImageView) rootView.findViewById(R.id.imagePerson);
		ImageView logo = (ImageView) rootView.findViewById(R.id.logoCompany);
		Button companyMap = (Button) rootView.findViewById(R.id.companyMapButton);
		Button companyFavorite = (Button) rootView.findViewById(R.id.companyFavoriteButton);
		
		person.setBackgroundResource(personImageResId);
		logo.setBackgroundResource(companyLogoResId);
		companyMap.setText(c.getStand_nummer());
		companyMap.setOnClickListener(new MapScrollToPositionListener(this, c.getId(), ((MainActivity)getActivity())));
		companyFavorite.setOnClickListener(new OnFavoriteClickListener(c.getId()));
		
		if(c.getIs_favorite() == 0){
			companyFavorite.setBackgroundResource(starResID);
		}else
			companyFavorite.setBackgroundResource(starFavoriteResID);
			
		
		TextView ansprechsperson = (TextView) rootView.findViewById(R.id.contentAnsprechsperson);
		TextView email = (TextView) rootView.findViewById(R.id.contentEmail);
		TextView telefon = (TextView) rootView.findViewById(R.id.contentTelefon);
		TextView internet = (TextView) rootView.findViewById(R.id.contentInternet);
		TextView beschreibung = (TextView) rootView.findViewById(R.id.contentBeschreibung);
		TextView adresse = (TextView) rootView.findViewById(R.id.contentAdresse);
		TextView branche = (TextView) rootView.findViewById(R.id.contentBranche);
		TextView standorte = (TextView) rootView.findViewById(R.id.contentStandorte);
		TextView mitarbeiter = (TextView) rootView.findViewById(R.id.contentMitarbeiter);
		TextView einstellungen = (TextView) rootView.findViewById(R.id.contentEinstellungen);
		TextView einsatzbereiche = (TextView) rootView.findViewById(R.id.contentEinsatzbereiche);
		
		
		ansprechsperson.setText(c.getPerson());
		email.setText(c.getEmail());
		telefon.setText(c.getTelefon());
		
		SpannableString spanString = new SpannableString(c.getInternet());
		spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), 0);
		
		internet.setText(spanString);
		beschreibung.setText(c.getBeschreibung());
		adresse.setText(c.getAdresse().replace("&&", ","));
		branche.setText(c.getBranche());
		standorte.setText(c.getStandorte());
		mitarbeiter.setText(c.getMitarbeiter());
		einstellungen.setText(c.getEinstellungen());
		einsatzbereiche.setText(c.getEinsatzbereiche());
		
		/**
		 * Set Phone Email and Homepage Listeners
		 */
		telefon.setTextColor(getResources().getColor(R.color.lightblue));
		telefon.setOnClickListener(new TelefonListener(telefon.getText().toString(),((MainActivity)getActivity())));	
		
		internet.setTextColor(getResources().getColor(R.color.lightblue));
		internet.setOnClickListener(new HomepageListener(internet.getText().toString(),(MainActivity) getActivity()));
		
		email.setTextColor(getResources().getColor(R.color.lightblue));
		email.setOnClickListener(new EmailListener(email.getText().toString()));
		
	}
}
