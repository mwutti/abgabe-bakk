package at.aau.connectapp.listener;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class EmailListener implements OnClickListener {
	private String email;

	public EmailListener(String email){
		this.email = email;
	}


	@Override
	public void onClick(View v) {
		if(email.contains(",")){
			//Tow emails, user have to make a decision
		}else{
			String[] email = new String[1];
			email[0]= this.email;
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("text/html");			
			intent.putExtra(Intent.EXTRA_EMAIL, email);
			
			v.getContext().startActivity(intent);
		}


	}

}
