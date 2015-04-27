package mangues.animationdemo;

import android.R.anim;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener{
 
	Button button1;
	Button button2;
	Button button3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			Intent intent = new Intent(MainActivity.this,TweenActivity.class);
			startActivity(intent);
			break;
		case R.id.button2:
			Intent intent2 = new Intent(MainActivity.this,FrameActivity.class);
			startActivity(intent2);
			break;
		case R.id.button3:
			Intent intent3 = new Intent(MainActivity.this,PropertyActivity.class);
			startActivity(intent3);
			break;

		default:
			break;
		}
		
	}
	
}
