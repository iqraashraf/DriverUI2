package com.example.iqraa.driverui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import RideRequest.AcceptRequest;
import RideRequest.RequestFrag;
import static android.os.Build.VERSION_CODES.M;

/**
 * Created by iqraa on 9/12/2017.
 */

public class MainFragment extends Fragment implements View.OnClickListener {
    DrawerLayout mDrawerLayout;
    ImageView drawer;
    LinearLayout top,top1;
    TextView chk_network;
    int time;
    TextView tv1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v=inflater.inflate(R.layout.main_fragment,container,false);
        mDrawerLayout=(DrawerLayout)((MapsActivity) getActivity()).findViewById(R.id.drawer_layout);
        drawer=(ImageView)v.findViewById(R.id.drawer_icon);
        drawer.setOnClickListener(this);
        top=(LinearLayout)v.findViewById(R.id.top);
        top1=(LinearLayout)v.findViewById(R.id.top1);
        chk_network=(TextView)v.findViewById(R.id.network_id);
        tv1 = (TextView) v.findViewById(R.id.timer);
        if (isInternetOn(getActivity())){
            top.setBackgroundColor(Color.parseColor("#1BB911"));
            top1.setBackgroundColor(Color.parseColor("#1BB911"));
            //chk_network.setText("Connected");
            chk_network.setText(getString(R.string.connected));
        }else{
            top.setBackgroundColor(Color.parseColor("#ff0000"));
            top1.setBackgroundColor(Color.parseColor("#ff0000"));
           // chk_network.setText("Disconnected");
            chk_network.setText(getString(R.string.disconnected));
        }
        //-----------Timer starts-------------

        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                tv1.setText("00: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                RequestFrag rf = (RequestFrag) fm.findFragmentByTag("rf");


                if (rf == null) {
                    Log.i("TAG", "Adding rf");
                    rf = new RequestFrag();
                    ft.setCustomAnimations(R.animator.fragment_slide_left_enter,R.animator.fragment_slide_left_exit,
                            R.animator.fragment_slide_right_enter,R.animator.fragment_slide_right_exit);

                    ft.replace(R.id.cont, rf, "rf");
                    //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.commit();
                }
            }
        }.start();


        //-----------Timer end--------------*/
        return v;

    }





    @Override
    public void onClick(View v) {
        if(v==drawer){
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }
    public static boolean isInternetOn(Context context) {
        ConnectivityManager connec = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED
                || mWifi.isConnectedOrConnecting()) {
            return true;
        } else if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||
                connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED ||
                mWifi.getState() == NetworkInfo.State.DISCONNECTED) {
            return false;
        }
        return false;
    }
}
