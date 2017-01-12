package at.aau.connectapp.listener;

import android.view.View;
import android.view.View.OnClickListener;

import at.aau.connectapp.MainActivity;
import at.aau.connectapp.R;
import at.aau.connectapp.fragment.AusstellerListFragment;
import at.aau.connectapp.fragment.CompanyProfileFragment;
import at.aau.connectapp.fragment.EducationalProfileFragment;
import at.aau.connectapp.fragment.ImpulsvortraegeFragment;
import at.aau.connectapp.fragment.LageplanFragment;
import at.aau.connectapp.fragment.MinimalProfileFragment;
import at.aau.connectapp.fragment.SpecialsFragment;

public class MapScrollToPositionListener implements OnClickListener {
	private long ausstellerId;
	private AusstellerListFragment fragment;
	private ImpulsvortraegeFragment fragmentImp;
	private SpecialsFragment	fragmentSp;
	private String flag;
	private MainActivity main;
	private CompanyProfileFragment fragmentCo;
	private MinimalProfileFragment fragmentMin;
	private EducationalProfileFragment fragmentEdu;

	public MapScrollToPositionListener (AusstellerListFragment fragment, long id, MainActivity main){
		this.fragment = fragment;
		this.ausstellerId = id;
		this.flag = "aussteller";
		this.main = main;
	}

	public MapScrollToPositionListener(ImpulsvortraegeFragment fragment2,
			long itemId, MainActivity main) {
		this.fragmentImp = fragment2;
		this.ausstellerId = itemId;
		this.flag = "impulsvortraege";
		this.main = main;
	}

	public MapScrollToPositionListener(SpecialsFragment fragment3, long itemId, MainActivity main) {
		this.fragmentSp = fragment3;
		this.ausstellerId = itemId;
		this.flag = "specials";
		this.main = main;
	}

	public MapScrollToPositionListener(CompanyProfileFragment fragment4, long itemId, MainActivity main) {
		this.fragmentCo = fragment4;
		this.ausstellerId = itemId;
		this.flag = "company";
		this.main = main;
	}
	public MapScrollToPositionListener(MinimalProfileFragment fragment5, long itemId, MainActivity main) {
		this.fragmentMin = fragment5;
		this.ausstellerId = itemId;
		this.flag = "min";
		this.main = main;
	}
	public MapScrollToPositionListener(EducationalProfileFragment fragment6, long itemId, MainActivity main) {
		this.fragmentEdu = fragment6;
		this.ausstellerId = itemId;
		this.flag = "edu";
		this.main = main;
	}

	@Override
	public void onClick(View v) {
		if(flag.equals("min")){
			fragmentMin.getFragmentManager().beginTransaction()
			.add(LageplanFragment.newInstance(1,ausstellerId,"aus"),"TAG")			
			.addToBackStack(null)
			.replace(R.id.container, LageplanFragment.newInstance(1, ausstellerId, "aus"))
			.commit();
			main.pushToBackStackHeader("Lageplan");
			main.setmTitle("Lageplan");
			main.restoreActionBar();


		}else
		if(flag.equals("edu")){
			fragmentEdu.getFragmentManager().beginTransaction()
			.add(LageplanFragment.newInstance(1,ausstellerId,"aus"),"TAG")			
			.addToBackStack(null)
			.replace(R.id.container, LageplanFragment.newInstance(1, ausstellerId, "aus"))
			.commit();
			main.pushToBackStackHeader("Lageplan");
			main.setmTitle("Lageplan");
			main.restoreActionBar();


		}else
		if(flag.equals("company")){
			fragmentCo.getFragmentManager().beginTransaction()
			.add(LageplanFragment.newInstance(1,ausstellerId,"aus"),"TAG")			
			.addToBackStack(null)
			.replace(R.id.container, LageplanFragment.newInstance(1, ausstellerId, "aus"))
			.commit();
			main.pushToBackStackHeader("Lageplan");
			main.setmTitle("Lageplan");
			main.restoreActionBar();


		}else
			if(flag.equals("impulsvortraege")){
				fragmentImp.getFragmentManager().beginTransaction()
				.add(LageplanFragment.newInstance(1,ausstellerId,"imp"),"TAG")			
				.addToBackStack(null)
				.replace(R.id.container, LageplanFragment.newInstance(1, ausstellerId, "imp"))
				.commit();
				main.pushToBackStackHeader("Lageplan");
				main.setmTitle("Lageplan");
				main.restoreActionBar();


			}else if(flag.equals("aussteller")){
				fragment.getFragmentManager().beginTransaction()
				.add(LageplanFragment.newInstance(1,ausstellerId,"aus"),"TAG")			
				.addToBackStack(null)
				.replace(R.id.container, LageplanFragment.newInstance(1, ausstellerId, "aus"))
				.commit();
				main.pushToBackStackHeader("Lageplan");
				main.setmTitle("Lageplan");
				main.restoreActionBar();


			}else if (flag.equals("specials")){
				fragmentSp.getFragmentManager().beginTransaction()
				.add(LageplanFragment.newInstance(1,ausstellerId,"sp"),"TAG")			
				.addToBackStack(null)
				.replace(R.id.container, LageplanFragment.newInstance(1, ausstellerId, "sp"))
				.commit();
				main.pushToBackStackHeader("Lageplan");
				main.setmTitle("Lageplan");
				main.restoreActionBar();

			}



	}

}
