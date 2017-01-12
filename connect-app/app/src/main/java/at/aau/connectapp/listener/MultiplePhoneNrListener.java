package at.aau.connectapp.listener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import at.aau.connectapp.MainActivity;

public class MultiplePhoneNrListener implements OnItemClickListener {
	
	private MainActivity act;

	public MultiplePhoneNrListener(MainActivity act){
		this.act = act;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String number = "tel:" + parent.getItemAtPosition(position).toString();
		
		TelefonListener call = new TelefonListener(number ,act);
		call.onClick(view);

	}

}
