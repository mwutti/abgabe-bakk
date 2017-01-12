package at.aau.connectapp.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import at.aau.connectapp.MainActivity;
import at.aau.connectapp.MapInterface;
import at.aau.connectapp.R;
import at.aau.connectapp.beans.Aussteller;
import at.aau.connectapp.beans.Impulsvortrag;
import at.aau.connectapp.beans.Special;
import at.aau.connectapp.dao.AusstellerDao;
import at.aau.connectapp.dao.ImpulsvortraegeDao;
import at.aau.connectapp.dao.SpecialDao;

public class LageplanFragment extends Fragment {
	public final static String TAG ="Lageplan Fragment";
	public final static String ARG_ZOOM_TO = "arg_zoom_to";
	public final static String TYPE = "type";
	private long id;
	private AusstellerDao dao;
	private ImpulsvortraegeDao iDao;
	private SpecialDao sDao;
	private Context context;
	private WebView map;
	private Aussteller aus;	
	private Impulsvortrag imp;
	private Special sp;
	



	public static LageplanFragment newInstance(int sectionNumber, long ausstellerId, String type) {
		LageplanFragment fragment = new LageplanFragment();
		Bundle args = new Bundle();		
		args.putInt(MainActivity.ARG_SECTION_NUMBER, sectionNumber);
		args.putString(TYPE, type);
		args.putLong(ARG_ZOOM_TO, ausstellerId);		
		fragment.setArguments(args);
		return fragment;
	}

	public LageplanFragment() {
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_lageplan, container, false);
		this.context = rootView.getContext();
		map = (WebView) rootView.findViewById(R.id.webViewMap);		

		map.getSettings().setBuiltInZoomControls(true);
		map.getSettings().setJavaScriptEnabled(true);
		
		map.getSettings().setLoadWithOverviewMode(true);
	    map.getSettings().setUseWideViewPort(true);
	    
	   


		/**
		 * Do default operation on map if argument is 0
		 * if not find Aussteller and assign it to Web View Client for asynchronous task
		 */			
		if(getArguments().getLong(ARG_ZOOM_TO)== 0)
			aus = null;
		else{			
			
			if(getArguments().getString(TYPE).equals("imp")){
				id = getArguments().getLong(ARG_ZOOM_TO);
				
				map.setWebViewClient(new WebViewClient(){
					@Override
					public void onPageFinished(WebView view, String url) {	        
						super.onPageFinished(view, url);						
						
						
					    imp = getImpulsvortragByID(id);
						int x = imp.getStand().getX1();
						int y = imp.getStand().getY1();						
						
						Log.d(TAG, "x: " + x + "y : " +y);
						map.loadUrl("javascript:zoomToAussteller(" + x + "," + y +")");
						

					}
				});
			}else if (getArguments().getString(TYPE).equals("aus")){	
				id = getArguments().getLong(ARG_ZOOM_TO);
				map.setWebViewClient(new WebViewClient(){
					@Override
					public void onPageFinished(WebView view, String url) {	        
						super.onPageFinished(view, url);	
						
					    aus = getAusstellerByID(getArguments().getLong(ARG_ZOOM_TO));
						int x = aus.getStand().getX1();
						int y = aus.getStand().getY1();
						
						int n = 10;
						Log.d(TAG, "x: " + x + "y : " +y);
						map.loadUrl("javascript:zoomToAussteller(" + x + "," + y +")");
						map.loadUrl("javascript:highlightAussteller(" + x +"," + y + "," + n +")");

					}
				});
			}else if (getArguments().getString(TYPE).equals("sp")){
				id = getArguments().getLong(ARG_ZOOM_TO);
				map.setWebViewClient(new WebViewClient(){
					@Override
					public void onPageFinished(WebView view, String url) {	        
						super.onPageFinished(view, url);	
						
						map.getSettings().setLoadWithOverviewMode(true);
					    map.getSettings().setUseWideViewPort(true);
						
					    sp = getSpecialByID(getArguments().getLong(ARG_ZOOM_TO));
					    
						int x = sp.getStand().getX1();
						int y = sp.getStand().getY1();
						
						int n = 10;
						Log.d(TAG, "x: " + x + "y : " +y);
						map.loadUrl("javascript:zoomToAussteller(" + x + "," + y +")");
						map.loadUrl("javascript:highlightAussteller(" + x +"," + y + "," + n +")");

					}
				});
			}
			
			
		}		
		MapInterface jsInterface = new MapInterface(rootView.getContext(),rootView, (MainActivity)getActivity(), this);
		map.addJavascriptInterface(jsInterface, "Android");		
		map.loadUrl("file:///android_asset/map/map.html");				


		return rootView;

	}

	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(
				getArguments().getInt(MainActivity.ARG_SECTION_NUMBER));
	}

	private Aussteller getAusstellerByID(long id){
		if(dao == null){
			dao = new AusstellerDao(context);
		}
		dao.open();
		Aussteller aussteller = dao.findAusstellerByID(id);
		dao.close();
		return aussteller;

	}
	
	private Impulsvortrag getImpulsvortragByID(long id){
		if(iDao == null){
			iDao = new ImpulsvortraegeDao(context);
		}
		iDao.open();
		Impulsvortrag imp = iDao.findImpulsvortragByID(id);
		iDao.close();
		return imp;

	}
	
	private Special getSpecialByID(long id){
		if(sDao == null){
			sDao = new SpecialDao(context);
		}
		sDao.open();
		Special sp = sDao.findSpecialByID(id);
		sDao.close();
		return sp;

	}


}
