package RideRequest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.iqraa.driverui.MainFragment;
import com.example.iqraa.driverui.MapsActivity;
import com.example.iqraa.driverui.R;

import SignUp.PhoneNumber;

/**
 * Created by iqraa on 9/8/2017.
 */

public class RequestFrag extends Fragment implements View.OnClickListener {
    Button acpt,rejct;
    LinearLayout mainLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v=inflater.inflate(R.layout.request_frag,container,false);
        acpt=(Button)v.findViewById(R.id.accept_btn);
        rejct=(Button)v.findViewById(R.id.reject_btn);
        mainLayout=(LinearLayout) ((MapsActivity) getActivity()).findViewById(R.id.main_layout);
        acpt.setOnClickListener(this);
        rejct.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v==acpt){
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            AcceptRequest acr=(AcceptRequest) fm.findFragmentByTag("acr");

            if (acr == null) {
                Log.i("TAG", "Adding acr");
                acr = new AcceptRequest();
                ft.setCustomAnimations(R.animator.fragment_slide_left_enter,R.animator.fragment_slide_left_exit,
                        R.animator.fragment_slide_right_enter,R.animator.fragment_slide_right_exit);

                ft.replace(R.id.cont, acr, "acr");
                //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

        }else if (v==rejct){

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            MainFragment mf=(MainFragment) fm.findFragmentByTag("mf");
            if (mf == null) {
                Log.i("TAG", "Adding mf");
                mf = new MainFragment();
                ft.setCustomAnimations(R.animator.fragment_slide_left_enter,R.animator.fragment_slide_left_exit,
                        R.animator.fragment_slide_right_enter,R.animator.fragment_slide_right_exit);

                ft.replace(R.id.cont, mf, "mf");
                //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

        }
    }
}

