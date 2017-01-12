package at.aau.connectapp.listener;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import at.aau.connectapp.MainActivity;
import at.aau.connectapp.R;

public class HomepageListener implements OnClickListener {
	private String homepage;
	private MainActivity act;


	public HomepageListener(String h, MainActivity act){

		this.homepage = h;		
		this.act = act;

	}

	@Override
	public void onClick(View v) {
		if(homepage.contains(",")){
			String urls[] = homepage.split(",");
	        AlertDialog.Builder alertDialog = new AlertDialog.Builder(act);
	        LayoutInflater inflater = act.getLayoutInflater();
	        View convertView = (View) inflater.inflate(R.layout.alert_phone, null);
	        alertDialog.setView(convertView);
	        alertDialog.setTitle("Auswählen");
	        ListView lv = (ListView) convertView.findViewById(R.id.listView1);
	        
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(act,android.R.layout.simple_list_item_1,urls);
	        lv.setOnItemClickListener(new MultipleUrlsListener(act));
	        
	        lv.setAdapter(adapter);
	        alertDialog.show();
		}else{
			String h = "http://" + homepage;
			Log.d("TAG", homepage);
			Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(h));
			v.getContext().startActivity(browser);
		}


	}



}
