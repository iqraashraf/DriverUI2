package com.example.iqraa.driverui;

/**
 * Created by iqraa on 9/8/2017.
 */

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import SignUp.GetStarted;
import SignUp.Welcome;


public class Splash extends Activity {
    public static final int secondsDelayed = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setWelcomePage();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Welcome fb = (Welcome) fm.findFragmentByTag("wl");
                if(fb != null) {
                    ft.remove(fb);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                    ft.commit();
                }
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                GetStarted gs = (GetStarted) fm.findFragmentByTag("gs");
                if(gs == null) {
                    gs = new GetStarted();
                    ft.add(R.id.welcome, gs, "gs");
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.commit();
                }

            }
        }, secondsDelayed * 2000);

    }

    private void setWelcomePage() {
        FragmentManager fm = getFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();

        Welcome fb = (Welcome) fm.findFragmentByTag("wl");

        if(fb == null) {
            fb = new Welcome();
            ft.add(R.id.welcome, fb, "wl");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
        }



    }
}
