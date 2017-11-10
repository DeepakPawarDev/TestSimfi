package samples.deepak.com.testsimfi.webservices;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import samples.deepak.com.testsimfi.R;

/**
 * Created by deepak on 11/9/2017.
 */

public class CustomServiceCallAdapter extends BaseAdapter {

    Context context;
    AsynchResult asynchResult;
    ArrayList<MyService> serviceArrayList;
    private LayoutInflater mInflater;

    public CustomServiceCallAdapter(Context context, AsynchResult asynchResult, ArrayList<MyService> serviceArrayList) {

        this.asynchResult = asynchResult;
        this.context = context;
        this.serviceArrayList = serviceArrayList;
        this.mInflater = LayoutInflater.from(context);


    }


    @Override
    public int getCount() {
        return serviceArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return serviceArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    static class ViewHolder {

        protected TextView textViewStatus;
        protected Button buttonService, buttonLog;
        protected LinearLayout linearLayoutStatus;
        protected ProgressBar progressBar;


    }


    @SuppressLint({"ViewTag", "InflateParams"})
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.service, null);

            viewHolder = new ViewHolder();


            viewHolder.buttonService = (Button) convertView.findViewById(R.id.button_m_recharge);
            viewHolder.buttonLog = (Button) convertView.findViewById(R.id.button_log);
            viewHolder.progressBar = (ProgressBar) convertView.findViewById(R.id.progresbar_w_bal);
            viewHolder.linearLayoutStatus = (LinearLayout) convertView.findViewById(R.id.linear_layout_m_bal_status);
            viewHolder.textViewStatus = (TextView) convertView.findViewById(R.id.text_view_w_bln_stus);


            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }


        final View view = convertView;





        viewHolder.buttonService.setText(serviceArrayList.get(position).getStrServiceName());

        if (serviceArrayList.get(position).isBooleanProgress() == false) {

            viewHolder.progressBar.setVisibility(View.GONE);

        } else {

            viewHolder.progressBar.setVisibility(View.VISIBLE);
        }

        viewHolder.textViewStatus.setText(serviceArrayList.get(position).getStringStatus());
        viewHolder.linearLayoutStatus.setBackgroundColor(Color.parseColor(serviceArrayList.get(position).getStringColorCode()));





        viewHolder.buttonService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "*** " + serviceArrayList.get(position).getIntServiceId(), Toast.LENGTH_LONG).show();

                serviceArrayList.get(position).setStringColorCode("#000000");
                serviceArrayList.get(position).setStringStatus("Running...");


                Button buttonService = (Button) view.findViewById(R.id.button_m_recharge);
                Button buttonLog = (Button) view.findViewById(R.id.button_log);
                ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progresbar_w_bal);
                LinearLayout linearLayoutStatus = (LinearLayout) view.findViewById(R.id.linear_layout_m_bal_status);
                TextView textViewStatus = (TextView) view.findViewById(R.id.text_view_w_bln_stus);


                switch (serviceArrayList.get(position).getIntServiceId()) {

                    case 1:
                        progressBar.setVisibility(View.VISIBLE);
                        buttonLog.setVisibility(View.GONE);
                        buttonService.setClickable(false);

                        WalletBalance walletBalance = new WalletBalance(context, asynchResult, view, serviceArrayList.get(position));
                        walletBalance.makeMRechargeCall();

                        break;

                    case 2:


                        progressBar.setVisibility(View.VISIBLE);
                        buttonLog.setVisibility(View.GONE);
                        buttonService.setClickable(false);

                        WalletBalance walletBalance1 = new WalletBalance(context, asynchResult, view, serviceArrayList.get(position));
                        walletBalance1.makeMRechargeCall();



                        break;

                }

            }
        });


        return convertView;
    }


}
