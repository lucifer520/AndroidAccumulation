package mangues.animationdemo;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class FrameActivity extends Activity {
	Button button;
	ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frame);
		button = (Button) findViewById(R.id.button_frame);
		imageView = (ImageView) findViewById(R.id.imageView_frame);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//第一步将animation-list设置为ImageView的背景
				imageView.setBackgroundResource(R.anim.framelist);
				//第二步获取ImagView的背景并将其转换成AnimationDrawable
				AnimationDrawable animationDrawable=(AnimationDrawable)imageView.getBackground();
				//第三步开始播放动画
				animationDrawable.start();
				//有一点需要强调的是：启动Frame动画的代码
				//animationDrawable.start();
				//不能应用在OnCreate()方法中，
				//因为在OnCreate()中AnimationDrawable
				//还没有完全的与ImageView绑定。
				//在OnCreate()中启动动画，
				//只能看到第一张图片。这里在触摸事件中实现的
			}
		});
	}
}
