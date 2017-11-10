package samples.deepak.com.testsimfi.webservices;

import android.content.Context;
import android.os.AsyncTask;


public class ServiceCall extends AsyncTask<String, String, String> {

	private String json;
	private PostJsonObject jsonObjectPost;
	private AsynchResult asynchInterface = null;
	Context context;

	public ServiceCall(Context context, AsynchResult asynchInterface, String json) {

		this.asynchInterface = asynchInterface;
		this.context = context;
		this.json = json;

		//System.out.println("Context:   " + context);
		//System.out.println("json:      " + json);
		//System.out.println("asynchInterface:  " + asynchInterface);

	}

	@Override
	protected void onPreExecute() {

		super.onPreExecute();
	}

	protected String doInBackground(String... urls) {
		String strJsonObj = null;
		jsonObjectPost = new PostJsonObject();

		strJsonObj = jsonObjectPost.POST(urls[0], json);
		return strJsonObj;

	}

	@Override
	protected void onPostExecute(String mResult) {

		asynchInterface.getAsynchResult(mResult);

		super.onPostExecute(mResult);

	}
}