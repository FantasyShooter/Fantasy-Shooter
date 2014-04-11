package com.fshoot.entity;

import com.example.fantasyshooter.R;
import com.fshoot.main.MyApp;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SmallM extends Monster {

	public SmallM() {
		hp = 100;
		atk = 1;
		speed = 3;
	}

	public void create(Activity activity) {
		// Create a monster image
		ImageView view = new ImageView(activity);
		view.setImageResource(R.drawable.small);
		view.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				MyApp myapp = ((MyApp) v.getContext().getApplicationContext());
				SmallM.this.deductHP(myapp.getPlayer().getTotalAtk());
			}
		});
		// Add to battle view
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(30,30);
		LinearLayout rl = (LinearLayout) activity.findViewById(R.id.battleRoot);
		rl.addView(view, lp);
	}

	public void deductHP(int totalATK){
		hp -= totalATK;
		// if hp is lower then 1, delete this
	}
}
