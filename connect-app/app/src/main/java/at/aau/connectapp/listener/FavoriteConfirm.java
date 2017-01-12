package at.aau.connectapp.listener;

import java.util.List;
import android.content.DialogInterface;
import at.aau.connectapp.MainActivity;
import at.aau.connectapp.adapter.AusstellerListAdapter;
import at.aau.connectapp.beans.Aussteller;
import at.aau.connectapp.dao.AusstellerDao;

public class FavoriteConfirm implements  android.content.DialogInterface.OnClickListener {
	private AusstellerListAdapter adapter;
	private int id;
	private MainActivity main;

	public FavoriteConfirm (AusstellerListAdapter adapter, int id, MainActivity main){
		this.adapter = adapter;
		this.id = id;
		this.main = main;
	}


	@Override
	public void onClick(DialogInterface dialog, int which) {
		int position = 0;
		List<Aussteller> aussteller = adapter.getAussteller();

		for(int i = 0 ; i < aussteller.size() ; i++ ){
			if(aussteller.get(i).getId() == id){
				position = i;
				break;
			}

		}
		adapter.getAussteller().remove(position);
		adapter.notifyDataSetChanged();
		
		
		AusstellerDao dao = new AusstellerDao(main);
		dao.open();
		dao.setFavorite(0, id);
		dao.close();
		
	}

}






