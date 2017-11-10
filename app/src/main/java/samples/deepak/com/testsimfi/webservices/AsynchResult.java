package samples.deepak.com.testsimfi.webservices;

import android.view.View;

public interface AsynchResult {

	void getAsynchResult(String str , View view, MyService myService);
	void getAsynchResult(String str);
	
	void getAsynchResult(String strMessage, int strType, String strGuid);
}
