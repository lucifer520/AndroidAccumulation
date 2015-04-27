package mangues.animationdemo;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class PropertyActivity extends Activity {

	Button btn;
	ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_property);
		imageView = (ImageView) findViewById(R.id.imageView_pro);
		btn = (Button) findViewById(R.id.button_pro);
		btn.setOnClickListener(new OnClickListener() {
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				ObjectAnimator animator = new ObjectAnimator().ofInt(new WrapView(imageView), "width", 10);     
				animator.setDuration(2000);//设置动画持续的时间
	            animator.setRepeatCount(2);//设置动画重复的次数  
	            animator.start();//开启动画
			}
		});
	}

	/*
	 * 包装类用于封装View的with、height,
     * 你可以通过getXxx以及setXxx方法设置View的宽和高
     */
    class WrapView{
        private View view;
        private int width;
        private int height;     
        public WrapView(View view){
            this.view=view;
        }       
        public int getWidth() {
            return view.getLayoutParams().width;
        }
        public void setWidth(int width) {
            this.width = width;
            view.getLayoutParams().width=width;//修改View的高度
            view.requestLayout();//刷新View的布局
        }
        public int getHeight() {
            return view.getLayoutParams().height;
        }
        public void setHeight(int height) {
            this.height=height;
            view.getLayoutParams().height = height;
            view.requestLayout();
        }               
    }
}
