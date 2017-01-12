package at.aau.connectapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.database.SQLException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import at.aau.connectapp.dao.MySQLiteHelper;
import at.aau.connectapp.fragment.AusstellerListFragment;
import at.aau.connectapp.fragment.FavoritenFragment;
import at.aau.connectapp.fragment.ImpressumFragment;
import at.aau.connectapp.fragment.ImpulsvortraegeFragment;
import at.aau.connectapp.fragment.InfoFragment;
import at.aau.connectapp.fragment.LageplanFragment;
import at.aau.connectapp.fragment.NavigationDrawerFragment;
import at.aau.connectapp.fragment.SpecialsFragment;
import at.aau.connectapp.fragment.SucheFragment;


public class MainActivity extends Activity
implements NavigationDrawerFragment.NavigationDrawerCallbacks {
	private DrawerLayout mDrawerLayout;
	private Toast toast;
	private static final String TAG = "MAIN_ACTIVITY";
	private MySQLiteHelper sqLiteHelper;
	private List<String> backstackHeader = new ArrayList <String>();
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */

	public static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
	 */
	protected NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in {@link #restoreActionBar()}.
	 */
	protected CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		backstackHeader.add("Connect 2015");
		/**
		 * Checks if db already exists and create the db if necessary
		 */

		sqLiteHelper = new MySQLiteHelper(this);
		try {
			sqLiteHelper.createDataBase();
		} catch (IOException e) {
			Log.e(TAG, "Database creation error");
		}
		try{
			sqLiteHelper.openDataBase();
		}catch(SQLException sqlex){
			Log.e("TAG","OpenDataBase error");
		}



		mNavigationDrawerFragment = (NavigationDrawerFragment)getFragmentManager().findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(
				R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));

		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4699c2")));

		ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
				) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {	           
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item

		}
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		//Log.d("NR", "pos: "+position);
		switch(position){
		case 0 :	//Info Page	

			// Clear Backstack
			for(int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {    
				fragmentManager.popBackStack();
				Log.d(TAG,"clearing Backstack");
			}
			backstackHeader = new ArrayList<String>();
			backstackHeader.add("Connect 2015");


			fragmentManager.beginTransaction()			
			//.add(InfoFragment.newInstance(position),TAG)
			//.addToBackStack("Connect")
			
			.replace(R.id.container, InfoFragment.newInstance(position))			
			.commit();
			break; 

		case 1 : 	//Aussteller List
			fragmentManager.beginTransaction()
			.add(AusstellerListFragment.newInstance(position, false),TAG)			
			.addToBackStack("Aussteller")
			.replace(R.id.container, AusstellerListFragment.newInstance(position, false))
			.commit();
			backstackHeader.add("Aussteller");
			break;    
		case 2 : 	//Impulsvortraege List
			fragmentManager.beginTransaction()
			.add(ImpulsvortraegeFragment.newInstance(position),TAG)
			.addToBackStack("Impulsvorträge")
			.replace(R.id.container, ImpulsvortraegeFragment.newInstance(position))
			.commit();
			backstackHeader.add("Impulsvorträe");
			break; 
		case 3 : 	//Specials List
			fragmentManager.beginTransaction()
			.add(SpecialsFragment.newInstance(position),TAG)
			.addToBackStack("Messespecials")
			.replace(R.id.container, SpecialsFragment.newInstance(position))
			.commit();
			backstackHeader.add("Messespecials");
			break; 
		case 4 : 	//Lageplan Map

			fragmentManager.beginTransaction()
			.add(LageplanFragment.newInstance(position,0,"none"),TAG)			
			.addToBackStack("Lageplan")
			.replace(R.id.container, LageplanFragment.newInstance(position,0,"none"))
			.commit();
			backstackHeader.add("Lageplan");
			break; 
		case 5 : 	//Favoriten

			fragmentManager.beginTransaction()
			.add(FavoritenFragment.newInstance(position),TAG)			
			.addToBackStack("Favoriten")
			.replace(R.id.container, FavoritenFragment.newInstance(position))
			.commit();
			backstackHeader.add("Favoriten");
			break; 
		case 6 : 	//Suche

			fragmentManager.beginTransaction()
			.add(SucheFragment.newInstance(position),TAG)			
			.addToBackStack("Suche")
			.replace(R.id.container, SucheFragment.newInstance(position))
			.commit();
			backstackHeader.add("Suche");
			break; 
		case 7 : 	//Impressum

			fragmentManager.beginTransaction()
			.add(ImpressumFragment.newInstance(position),TAG)			
			.addToBackStack("Impressum")
			.replace(R.id.container, ImpressumFragment.newInstance(position))
			.commit();
			backstackHeader.add("Impressum");
			break;		
		}

	}





	/**
	 * switches the title in action bar corresponding to the List entry in menu drawer 
	 * @param number of entity in menu drawer
	 */
	public void onSectionAttached(int number) {
		switchSection(number);
		
	}
	
	private void switchSection(int number){
		switch (number) {
		case 0:

			mTitle = getString(R.string.title_info);		
			break;
		case 1:
			mTitle = getString(R.string.title_aussteller);	
			break;
		case 2:
			mTitle = getString(R.string.title_impulsvortraege);
			break;
		case 3:
			mTitle = getString(R.string.title_messespecials);
			break;
		case 4:
			mTitle = getString(R.string.title_lageplan);
			break;
		case 5:
			mTitle = getString(R.string.title_favoriten);
			break;
		case 6:
			mTitle = getString(R.string.title_suche);
			break;
		case 7:
			mTitle = getString(R.string.title_impressum);
			break;
		}
	}
	/**
	 * restores Title in action bar
	 */
	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}




	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void goToMap(View view){
		FragmentManager fragmentManager = getFragmentManager();				
		fragmentManager.beginTransaction()
		.replace(R.id.container, LageplanFragment.newInstance(4,0,"aus"))			
		.commit();

	}
	@Override
	public void onBackPressed(){
		FragmentManager fm = getFragmentManager();
		if (fm.getBackStackEntryCount() > 0) {
			Log.i("MainActivity", "popping backstack");				
			fm.popBackStack();
			backstackHeader.remove(backstackHeader.size()-1);
			mTitle = backstackHeader.get(backstackHeader.size()-1);
			
			restoreActionBar();
		} else {
			Log.i("MainActivity", "nothing on backstack, calling super");
			super.onBackPressed();  
		}
		if(toast != null)
			toast.cancel();
	}

	public Toast getToast() {
		return toast;
	}

	public void setToast(Toast toast) {
		this.toast = toast;
	}
	
	public void  pushToBackStackHeader(String header){
		this.backstackHeader.add(header);
		
	}
	
	public void setmTitle(String title){
		this.mTitle = title;
	}

	public List<String> getBackstackHeader() {
		return backstackHeader;
	}
}
