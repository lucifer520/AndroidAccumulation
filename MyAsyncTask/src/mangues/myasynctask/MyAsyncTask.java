package mangues.myasynctask;

import android.os.AsyncTask;
import android.util.Log;

public class MyAsyncTask extends AsyncTask<Void, Void, Void> {

	@Override
	protected Void doInBackground(Void... params) {
		Log.i("mangues","doInBackground");
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		Log.i("mangues","onPostExecute");
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		Log.i("mangues","onPreExecute");
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate(values);
		Log.i("mangues","onProgressUpdate");
	}
	
	

}
