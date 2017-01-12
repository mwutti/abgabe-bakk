package at.aau.connectapp.listener;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import at.aau.connectapp.MainActivity;
import at.aau.connectapp.R;

public class TelefonListener implements OnClickListener {
	private String phoneNumber;
	private MainActivity act;

	public TelefonListener(String phoneNumber, MainActivity act){
		this.phoneNumber = phoneNumber;
		this.act = act;
	}


	@Override
	public void onClick(View v) {

		if (phoneNumber.contains(",")){
			
			String numbers[] = phoneNumber.split(",");
	        AlertDialog.Builder alertDialog = new AlertDialog.Builder(act);
	        LayoutInflater inflater = act.getLayoutInflater();
	        View convertView = (View) inflater.inflate(R.layout.alert_phone, null);
	        alertDialog.setView(convertView);
	        alertDialog.setTitle("Auswählen");
	        ListView lv = (ListView) convertView.findViewById(R.id.listView1);
	        
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(act,android.R.layout.simple_list_item_1,numbers);
	        lv.setOnItemClickListener(new MultiplePhoneNrListener(act));
	        
	        lv.setAdapter(adapter);
	        alertDialog.show();


		}else{
			String number = "tel:";
			number += phoneNumber;
			Intent intent = new Intent("android.intent.action.DIAL");
			intent.setData(Uri.parse(number));
			v.getContext().startActivity(intent);		}



	}

}
