package SignUp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.iqraa.driverui.R;

/**
 * Created by iqraa on 9/8/2017.
 */

public class PhoneNumber extends Fragment implements View.OnClickListener {
    ImageView back;
    ImageView forward;
    EditText phone;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ProgressBar progressBar;
    //Request request;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v=inflater.inflate(R.layout.phone_number, container, false);

        back = (ImageView) v.findViewById(R.id.back);
        back.setOnClickListener(this);
        forward = (ImageView) v.findViewById(R.id.forward);
        phone = (EditText) v.findViewById(R.id.phone);
        forward.setOnClickListener(this);
        progressBar = (ProgressBar) v.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        //sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        /*editor = sharedPreferences.edit();
        editor.apply();*/
        //request=new Request();
        return v;
    }

    public void onClick(View v) {

        if (v == back) {
            Toast.makeText(getActivity().getBaseContext(), "Back", Toast.LENGTH_SHORT).show();
            FragmentManager fm = getFragmentManager();

            FragmentTransaction ft = fm.beginTransaction();

            PhoneNumber ph = (PhoneNumber) fm.findFragmentByTag("ph");

            if (ph != null) {

                Log.i("TAG", "Removing");
                ft.remove(ph);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();

            }

            fm = getFragmentManager();

            ft = fm.beginTransaction();

            GetStarted gs = (GetStarted) fm.findFragmentByTag("gs");

            if (gs == null) {
                Log.i("TAG", "Adding");
                gs = new GetStarted();
                ft.add(R.id.welcome, gs, "gs");
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();

            }
        } else if (v == forward) {
            if (phone.getText().toString().trim().isEmpty()) {
                Toast.makeText(getActivity().getBaseContext(), "Please Enter Your Phone", Toast.LENGTH_SHORT).show();
            } else if (!phone.getText().toString().startsWith("03")) {
                Toast.makeText(getActivity().getBaseContext(), "Please Enter Correct Phone Number", Toast.LENGTH_SHORT).show();
            } else if (phone.getText().toString().length() != 11) {

                Toast.makeText(getActivity().getBaseContext(), "Please Enter Complete Phone Number", Toast.LENGTH_SHORT).show();
            } else {

                FragmentManager fm = getFragmentManager();

                FragmentTransaction ft = fm.beginTransaction();

                PhoneNumber ph = (PhoneNumber) fm.findFragmentByTag("ph");

                if (ph != null) {

                    Log.i("TAG", "Removing");
                    ft.remove(ph);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                    ft.commit();

                }

                fm = getFragmentManager();

                ft = fm.beginTransaction();

                VerificationCode gs = (VerificationCode) fm.findFragmentByTag("vc");

                if (gs == null) {
                    Log.i("TAG", "Adding");
                    gs = new VerificationCode();
                    ft.add(R.id.welcome, gs, "vc");
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.commit();

                }


               /* if (!isNetworkaAvailable()){
                    Toast.makeText(getActivity().getBaseContext(),"Network in not Available",Toast.LENGTH_SHORT).show();

                }else {
                    VerifyPhoneNumber phoneNumber = new VerifyPhoneNumber();


                    phoneNumber.execute("http://awamitest.azurewebsites.net/passenger/verifyphonenumber", phone.getText().toString());
                }*/

            }

        }
    }
}
