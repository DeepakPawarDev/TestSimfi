package samples.deepak.com.testsimfi.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;


import samples.deepak.com.testsimfi.R;

public class ServiceCallByAsyncTask extends AsyncTask<String, String, String> {
	
	private ProgressDialog progressDialog;
	private Context context;
	private String json,strGuid;
	private PostJsonObject jsonObjectPost;
	private int intType;
	AsynchResult asynchInterface=null;
	MyService myService;
	View view;
	
	public ServiceCallByAsyncTask(Context context, AsynchResult asynchInterface, String json, int intType, String strGuid,View view , MyService myService) {
		
		this.asynchInterface = asynchInterface;
		this.context = context;
		this.json = json;
		this.intType=intType;
		this.strGuid=strGuid;
		this.view=view;
		this.myService=myService;
		
		//System.out.println("Context:   " + context);
		//System.out.println("json:      " + json);
		//System.out.println("asynchInterface:  "  +asynchInterface);
	}

	@Override
	protected void onPreExecute() {

		progressDialog = new ProgressDialog(context);
		progressDialog.setMessage("sending Request...");
		//progressDialog.show();
		progressDialog.setCancelable(false);
		progressDialog.setContentView(R.layout.common_progressbar);
		super.onPreExecute();
	}

	protected String doInBackground(String... urls) {

		jsonObjectPost = new PostJsonObject();
		String strJsonObj = jsonObjectPost.POST(urls[0], json);
		return strJsonObj;
	}

	@Override
	protected void onPostExecute(String strResult) {

		//progressDialog.dismiss();
		System.out.println("******************  Post:::  "  +strResult);
		if (intType==1){
			asynchInterface.getAsynchResult(strResult,intType,strGuid);	
		}else if (intType==2){
			asynchInterface.getAsynchResult(strResult,intType,strGuid);
		}else if (intType==3){
			asynchInterface.getAsynchResult(strResult,intType,strGuid);
		}else {
		asynchInterface.getAsynchResult(strResult,view,myService);
		}
		super.onPostExecute(strResult);
	
	}
}