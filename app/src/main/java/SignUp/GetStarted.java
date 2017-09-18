package SignUp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.iqraa.driverui.R;

/**
 * Created by iqraa on 9/8/2017.
 */

public class GetStarted extends Fragment implements View.OnClickListener{
    LinearLayout layout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v=inflater.inflate(R.layout.get_started,container,false);
        layout=(LinearLayout)v.findViewById(R.id.touch);
        layout.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        if (v==layout) {




            //Toast.makeText(getActivity().getBaseContext(), "Touched", Toast.LENGTH_SHORT).show();
            FragmentManager fm = getFragmentManager();

            FragmentTransaction ft = fm.beginTransaction();

            GetStarted fb = (GetStarted) fm.findFragmentByTag("gs");

            if (fb != null) {
                Log.i("TAG","Removing");
                ft.remove(fb);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();

            }

            fm = getFragmentManager();

            ft = fm.beginTransaction();

            PhoneNumber ph = (PhoneNumber) fm.findFragmentByTag("ph");

            if(ph == null) {
                Log.i("TAG","Adding");
                ph = new PhoneNumber();
                ft.add(R.id.welcome, ph, "ph");
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();

            }

        }
    }
}

