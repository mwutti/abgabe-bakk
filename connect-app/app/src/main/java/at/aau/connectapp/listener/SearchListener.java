package at.aau.connectapp.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import at.aau.connectapp.MainActivity;
import at.aau.connectapp.R;
import at.aau.connectapp.beans.Aussteller;
import at.aau.connectapp.dao.AusstellerDao;
import at.aau.connectapp.fragment.AusstellerListFragment;

public class SearchListener implements OnClickListener {	

	private static final String TAG = "SearchListener";	
	private Activity act;
	private AusstellerDao dao;
	private EditText input;
	private List<Aussteller> aus;
	private static List<Aussteller> results;

	public static List<Aussteller> getResults() {
		return results;
	}

	public SearchListener( Activity activity, EditText input) {

		this.act = activity;
		dao = new AusstellerDao(act);
		this.input = input;

	}

	@Override
	public void onClick(View v) {
		/**
		 * Close android Soft Keyboard if its still open
		 */
		InputMethodManager inputManager = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManager.hideSoftInputFromWindow(act.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


		aus = getAussteller();		


		String searchString = input.getText().toString();
		if(searchString.equals("")){
			//No Input

		}else
			if( isInteger(searchString)){
				//Stand nummer Search
				results = new ArrayList<Aussteller>();
				results.add(getAusstellerNyStandNr(searchString));
				if(results.get(0) == null){
					//Standnummer not found
					showSearchNotFoundAlert();
				}else{
					//Standnummer found
					act.getFragmentManager().beginTransaction()
					.add(AusstellerListFragment.newInstance(2, true),TAG)			
					.addToBackStack("Aussteller")
					.replace(R.id.container, AusstellerListFragment.newInstance(1, true))
					.commit();
					((MainActivity)act).getBackstackHeader().add("Aussteller");
				}




			}else{
				//SearchString

				List <String> strings = Arrays.asList(searchString.split(" "));		

				results = search(strings);

				if(results.isEmpty()){
					showSearchNotFoundAlert();
				}else{
					act.getFragmentManager().beginTransaction()
					.add(AusstellerListFragment.newInstance(2, true),TAG)			
					.addToBackStack("Aussteller")
					.replace(R.id.container, AusstellerListFragment.newInstance(1, true))
					.commit();
					((MainActivity)act).getBackstackHeader().add("Aussteller");
				}				
			}
	}

	private void showSearchNotFoundAlert() {
		AlertDialog.Builder builder = new AlertDialog.Builder(act);
		builder.setMessage("Keine Ergebnisse gefunden").setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
		builder.create().show();

	}

	private List<Aussteller> search(List<String> strings) {

		List <Aussteller> results = new ArrayList<Aussteller>();

		for(String s : strings){
			for(Aussteller a : aus){
				if(a.getName().toLowerCase().contains(s.toLowerCase())){
					//potential Matching
					if(!results.contains(a)){
						//Aussteller is not in List
						results.add(a);
					}
				}

			}
		}
		return results;
	}

	private List<Aussteller> getAussteller(){
		List <Aussteller> aus;
		dao.open();
		aus = dao.getAllAussteller();
		dao.close();

		return aus;
	}

	private boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	private Aussteller getAusstellerNyStandNr(String nr){
		Aussteller aus;
		dao.open();
		aus = dao.findAusstellerByStand(nr);
		dao.close();

		return aus;
	}

}
