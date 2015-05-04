package mangues.myasynctask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	ImageView iamge;
	ProgressBar progressBar1;
	myAsyncTask task;
	String url = "https://coding.net/static/5ee8025c9dc63a6ff53153705d0e7ce8.png";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iamge = (ImageView) findViewById(R.id.iamge);
		progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
		progressBar1.setMax(10);
		task= new myAsyncTask();
		task.execute(url);
	}
	
	
	class myAsyncTask extends AsyncTask<String, Integer, Bitmap>{
		//获取传递的参数
		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap bitmap = null;
			InputStream is = null;
			BufferedInputStream in = null;
			
			try {
				for (int i = 0; i < 10; i++) {
					if(isCancelled()){
						break;
					}
					publishProgress(i);
					Thread.sleep(500); //模拟下载
				}
				
				URL url = new URL(params[0]);
				URLConnection conn = url.openConnection();
				is = conn.getInputStream();
				in = new BufferedInputStream(is);
				bitmap = BitmapFactory.decodeStream(in);	
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					in.close();
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			}
			//返回bitmap
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap bitmap) {
			super.onPostExecute(bitmap);
			progressBar1.setVisibility(View.GONE);
			iamge.setImageBitmap(bitmap);
			
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressBar1.setVisibility(View.VISIBLE);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			if(isCancelled()){
				return;
			}
			//获取进度更新值
			progressBar1.setProgress(values[0]);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if(task!=null &&task.getStatus() == AsyncTask.Status.RUNNING){
			//cancel方法只是将对应的AsyncTask标记为cancel状态，并不是真正的取消线程的执行
			//java不能粗暴的结束线程
			task.cancel(true);
			
		}
	}

}
