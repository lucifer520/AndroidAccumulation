package mangues.animationdemo;
import android.os.Bundle;
import android.app.Activity;
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

public class TweenActivity extends Activity implements OnClickListener{

	private Button alpha_animation,alpha_animation_x;
	private Button scale_animation,scale_animation_x;
	private Button translate,translate_x;
	private Button rotate,rotate_x;
	private ImageView image;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tween);
		alpha_animation=(Button) findViewById(R.id.alpha_animation);
		alpha_animation_x=(Button) findViewById(R.id.alpha_animation_x);
		alpha_animation.setOnClickListener(this);
		alpha_animation_x.setOnClickListener(this);
		
		
		scale_animation=(Button) findViewById(R.id.scale);
		scale_animation_x=(Button) findViewById(R.id.scale_x);
		scale_animation.setOnClickListener(this);
		scale_animation_x.setOnClickListener(this);
		
		
		
		translate=(Button) findViewById(R.id.translate);
		translate_x=(Button) findViewById(R.id.translate_x);
		translate.setOnClickListener(this);
		translate_x.setOnClickListener(this);
		
		rotate=(Button) findViewById(R.id.rotate);
		rotate_x=(Button) findViewById(R.id.rotate_x);
		rotate.setOnClickListener(this);
		rotate_x.setOnClickListener(this);
		
		
		image = (ImageView) findViewById(R.id.image);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.alpha_animation:
			//fromAlpha---toAlpha
			AlphaAnimation animation = new AlphaAnimation(1, (float) 0.1);
			//设置动画持续时间为3秒
			animation.setDuration(3000);
			//设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
			animation.setFillAfter(true);
			image.startAnimation(animation);
			break;

		case R.id.alpha_animation_x:
			Animation animation1 = AnimationUtils.loadAnimation(TweenActivity.this, R.anim.alpha);
			image.startAnimation(animation1);
			break;
			
			
			
		case R.id.scale:
			ScaleAnimation scale=new ScaleAnimation(0.5f, 1.0f,1.0f, 1.0f);
			scale.setDuration(2000);//设置动画持续时间为3秒
			scale.setFillAfter(true);//设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
			scale.setRepeatCount(3); //设置重复次数
			image.startAnimation(scale);
			break;

		case R.id.scale_x:
			Animation scale1 = AnimationUtils.loadAnimation(TweenActivity.this, R.anim.scale);
			image.startAnimation(scale1);
			break;
			
			
			
		case R.id.translate:
			TranslateAnimation translate=new TranslateAnimation(0, 100,0, 0);
			translate.setInterpolator(this, android.R.anim.cycle_interpolator);//设置动画插入器
			translate.setFillAfter(true);//设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
			image.startAnimation(translate);
			break;
			
		case R.id.translate_x:
			Animation translate1 = AnimationUtils.loadAnimation(TweenActivity.this, R.anim.translate);
			image.startAnimation(translate1);
			break;
			
			
			
		case R.id.rotate:
			RotateAnimation rotate=new RotateAnimation(0,45);
			rotate.setInterpolator(this, android.R.anim.cycle_interpolator);//设置动画插入器
			rotate.setFillAfter(true);//设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
			image.startAnimation(rotate);
			break;
			
		case R.id.rotate_x:
			Animation rotate1 = AnimationUtils.loadAnimation(TweenActivity.this, R.anim.rotate);
			image.startAnimation(rotate1);
			break;
		}
		
	}


}
