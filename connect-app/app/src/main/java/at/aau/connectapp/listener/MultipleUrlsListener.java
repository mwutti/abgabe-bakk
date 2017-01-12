package at.aau.connectapp.listener;

import at.aau.connectapp.MainActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class MultipleUrlsListener implements OnItemClickListener {
	private MainActivity act;
	
	public MultipleUrlsListener(MainActivity act){
		this.act = act;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String url =  parent.getItemAtPosition(position).toString();
		HomepageListener homepage = new HomepageListener(url, act);
		
		homepage.onClick(view);

	}

}
