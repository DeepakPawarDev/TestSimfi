package samples.deepak.com.testsimfi.webservices;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.StringTokenizer;

import samples.deepak.com.testsimfi.MainActivity;
import samples.deepak.com.testsimfi.R;

/**
 * Created by deepak on 11/9/2017.
 */

public class WalletBalance {

    Context context;
    AsynchResult asynchInterface;
    private boolean isInternetPresent = false;
    Button buttonService;
    Button buttonLog;
    ProgressBar progressBar;
    LinearLayout linearLayoutStatus;
    TextView textViewStatus;
    MyService myService;
    View view;


    public WalletBalance(Context context, AsynchResult asynchInterface, View view, MyService myService)

    {

        this.asynchInterface = asynchInterface;
        this.context = context;
        this.myService = myService;
        this.view=view;
        this.buttonService = (Button) view.findViewById(R.id.button_m_recharge);
        this.buttonLog = (Button) view.findViewById(R.id.button_log);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progresbar_w_bal);
        this.linearLayoutStatus = (LinearLayout) view.findViewById(R.id.linear_layout_m_bal_status);
        this.textViewStatus = (TextView) view.findViewById(R.id.text_view_w_bln_stus);


    }

    public static String convertDateForServer1(String strDate)

    {
        StringTokenizer tokens = new StringTokenizer(strDate, "-");
        String strTokenizerDay = tokens.nextToken();
        String strTokenizerMonth = tokens.nextToken();
        String strTokenizerYear = tokens.nextToken();

        return strTokenizerDay + "/" + strTokenizerMonth + "/" + strTokenizerYear;
    }


    public void makeMRechargeCall()

    {

        isInternetPresent = isConnectingToInternet();

        if (isInternetPresent == true) {

            try {

                String json = "";
                JSONObject jsonObj = new JSONObject();
                JSONObject jsonObject = new JSONObject();

                    textViewStatus.setText(myService.getStringStatus());
                    linearLayoutStatus.setBackgroundColor(Color.parseColor(myService.getStringColorCode()));



                try {
                    jsonObject.accumulate("strAgentMobileNo", "9623170438");
                    jsonObject.accumulate("intVerficationType", 0);
                    jsonObject.accumulate("strBirthdate", convertDateForServer1("02-05-2012"));
                    jsonObject.accumulate("intUserRequestType", 2);
                    jsonObject.accumulate("intMobileAppTypeId", 1);
                    jsonObj.accumulate("objAgent", jsonObject);

                    json = jsonObj.toString();

                } catch (JSONException e) {

                    e.printStackTrace();
                }
                new ServiceCallByAsyncTask(context, asynchInterface, json,
                        0, "",view,myService).execute("http://202.143.96.21/SimfiTestingService/Meghdoot_Service.svc/" + "GetMobileWalletBalance");


            } catch (Exception e) {

                System.out.println("*********** Error In Wallet Balance:- " + e);
            }

        } else {

            System.out.println("********************** Internet Not Available");
        }

    }


    public boolean isConnectingToInternet()

    {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }


}
