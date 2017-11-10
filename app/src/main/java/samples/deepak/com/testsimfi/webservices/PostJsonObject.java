package samples.deepak.com.testsimfi.webservices;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@SuppressWarnings("deprecation")
public class PostJsonObject {

	public PostJsonObject() {

	}

	public String POST(String url, String json) {
		InputStream inputStream = null;
		String result = "";
		try {

			System.out.println("Object:  " + json);
			// 1. create HttpClient
			HttpClient httpclient = new DefaultHttpClient();

			// 2. make POST request to the given URL
			HttpPost httpPost = new HttpPost(url);

			// 3. Set some headers to inform server about the type of the
			// content
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			// System.out.println("Json Object From Call: " +jsonObject);
			// 4. set json to StringEntity
			StringEntity se = new StringEntity(json, "UTF-8");

			// se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
			// "application/json"));
			se.setContentType("application/json");

			// 5. set httpPost Entity
			httpPost.setEntity(se);

			// 6. Execute POST request to the given URL
			HttpResponse httpResponse = httpclient.execute(httpPost);

			// 7. receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();

			// 8. convert inputstream to string
			if (inputStream != null)
				result = convertInputStreamToString(inputStream);

			else
				result = "Did not work!";

		} catch (Exception e) {
			Log.d("InputStream", e.getLocalizedMessage());
		}

		System.out.println("Response From Server:  " + result);

		// 9. return result
		return result;
	}

	private static String convertInputStreamToString(InputStream inputStream) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;

	}

}
