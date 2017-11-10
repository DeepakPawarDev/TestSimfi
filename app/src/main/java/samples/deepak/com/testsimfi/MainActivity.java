package samples.deepak.com.testsimfi;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.StringTokenizer;

import samples.deepak.com.testsimfi.webservices.AsynchResult;
import samples.deepak.com.testsimfi.webservices.CustomServiceCallAdapter;
import samples.deepak.com.testsimfi.webservices.MyService;
import samples.deepak.com.testsimfi.webservices.ServiceCallByAsyncTask;
import samples.deepak.com.testsimfi.webservices.WalletBalance;

public class MainActivity extends Activity implements AsynchResult {

    private boolean isInternetPresent = false;
    AsynchResult asynchInterface;
    ListView listViewServices;


    Button buttonService;
    Button buttonLog;
    ProgressBar progressBar;
    LinearLayout linearLayoutStatus;
    TextView textViewStatus;
    MyService myService;
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initializeComponent();
        bindList();
    }


    private void initializeComponent() {

        listViewServices = (ListView) findViewById(R.id.list_view_services);

    }

    private void bindList() {

        CustomServiceCallAdapter customServiceCallAdapter = new CustomServiceCallAdapter(this, asynchInterface = this, getArrayList());
        listViewServices.setAdapter(customServiceCallAdapter);


        listViewServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });


    }

    private ArrayList<MyService> getArrayList() {

        ArrayList<MyService> myServiceArrayList = new ArrayList<>();

        myServiceArrayList.add(

                new MyService("Wallet Blnce", 1, "#FF4081", false, "Not Start")
        );
        myServiceArrayList.add(

                new MyService("Register User", 2, "#FF4081", false, "Not Start")
        );


        return myServiceArrayList;

    }

    private void initializeViewComponent(View view) {

        this.buttonService = (Button) view.findViewById(R.id.button_m_recharge);
        this.buttonLog = (Button) view.findViewById(R.id.button_log);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progresbar_w_bal);
        this.linearLayoutStatus = (LinearLayout) view.findViewById(R.id.linear_layout_m_bal_status);
        this.textViewStatus = (TextView) view.findViewById(R.id.text_view_w_bln_stus);


    }

    private MyService setValues(MyService myService){

        myService.setStringColorCode("#00ff00");
        myService.setStringStatus("Complete");
        return myService;

    }

    private void updateServiceUI(MyService myService){

        progressBar.setVisibility(View.GONE);
        buttonLog.setVisibility(View.VISIBLE);
        textViewStatus.setText(myService.getStringStatus());
        buttonService.setClickable(true);
        linearLayoutStatus.setBackgroundColor(Color.parseColor(myService.getStringColorCode()));

    }

    @Override
    public void getAsynchResult(String str, View view, MyService myService) {


        if ("".equals(str) || null == str) {

            Toast.makeText(MainActivity.this, "service is not available,Please try Later", Toast.LENGTH_SHORT).show();

        } else {

            initializeViewComponent(view);
            updateServiceUI(setValues(myService));


        }
    }

    @Override
    public void getAsynchResult(String str) {

    }


    @Override
    public void getAsynchResult(String strResponse, int strType, String strGuid) {


    }

}