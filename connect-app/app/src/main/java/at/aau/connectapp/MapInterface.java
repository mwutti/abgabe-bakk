package at.aau.connectapp;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import at.aau.connectapp.beans.Aussteller;
import at.aau.connectapp.dao.AusstellerDao;
import at.aau.connectapp.listener.MapPreviewListener;
/**
 * 
 * @author Michael Wutti
 * Manages the communication between business-logic and Javascript behavior in the web View.
 * The web view is responsible for showing the interactive map and reacts on users touch behavior.
 * The specific id of the company which is touched is assigned to method showPreview.
 * ShowPreview inflates the preview View of the Company, assigning data and brings it to front using Toast. 
 *
 */
@SuppressLint("ShowToast")
public class MapInterface {
	private final static String TAG ="MapInterface";

	private Context context;
	private int ausstellerID;	
	private AusstellerDao dao;
	private View previewView;
	private MainActivity act;
	private Fragment fragment;



	/**
	 * 
	 * @param Context of the rootView which is calling
	 * @param rootView which is calling
	 * @param The MainActivity to manage onBackPressed behavior
	 * @param The Fragment which is calling access Fragment manager
	 */

	public MapInterface(Context context, View view, MainActivity act, Fragment fragment ){
		this.context = context;
		this.previewView = view;
		this.act = act;
		this.fragment = fragment;
		
		
	}
	/**
	 * 
	 * @param Aussteller id
	 * Inflates the preview View for Company and assigns data. 
	 * The View is shown with Android.Toast.
	 */
	public void showPreview(String id, String top , String left){		

		this.ausstellerID = Integer.valueOf(id);		
		Aussteller aussteller = getAusstellerByID(this.ausstellerID);
		//Inflate Layout for previewView
		LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		View preview = (View)inflater.inflate(R.layout.map_aussteller_preview, (ViewGroup)previewView.findViewById(R.id.toast_layout_root));
		//assign data and OnClickListener to views
		ImageView logo = (ImageView)preview.findViewById(R.id.map_preview_logo);
		int resId =context.getResources().getIdentifier(aussteller.getLogo_name(), "drawable", context.getPackageName());
		logo.setBackgroundResource(resId);
		logo.setOnClickListener(new MapPreviewListener(aussteller.getId(), fragment, act));

		TextView name = (TextView)preview.findViewById(R.id.map_preview_name);
		name.setText(aussteller.getName());
		//make Toast, assign view and bring it to front
		Toast toast = new Toast(context);
		toast.setView(preview);
		toast.setGravity(Gravity.TOP, 0,124);
		act.setToast(toast);
		toast.show();
		
	}


	/**
	 * 
	 * @param Aussteller id
	 * @return Aussteller with the given id
	 */
	private Aussteller getAusstellerByID(int id){
		if(dao == null){
			dao = new AusstellerDao(context);
		}
		dao.open();
		Aussteller aussteller = dao.findAusstellerByID(id);
		dao.close();
		return aussteller;

	}
}
