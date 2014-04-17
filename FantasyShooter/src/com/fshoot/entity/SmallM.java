package com.fshoot.entity;

import com.example.fantasyshooter.R;
import android.widget.ImageView;

public class SmallM extends Monster { 

	public SmallM() {
		hp = 200;
		atk = 1;
		speed = 3;
		step = -10 * speed;
		image = new ImageView(activity);
		image.setImageResource(R.drawable.small);
	}


	

}
