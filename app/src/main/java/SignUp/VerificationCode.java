package SignUp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iqraa.driverui.R;

/**
 * Created by iqraa on 9/8/2017.
 */


public class VerificationCode extends Fragment implements View.OnClickListener{
    ImageView back,forward;
    EditText one,two,three,four;
    TextView number;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.verification_code, container, false);
        progressBar = (ProgressBar) v.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        //sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        //editor = sharedPreferences.edit();

        back = (ImageView) v.findViewById(R.id.back);
        forward = (ImageView) v.findViewById(R.id.forward);
        one = (EditText) v.findViewById(R.id.one);
        two = (EditText) v.findViewById(R.id.two);
        three = (EditText) v.findViewById(R.id.three);
        four = (EditText) v.findViewById(R.id.four);
        number = (TextView) v.findViewById(R.id.number);
        //editor.apply();
        //number.setText("to you at "+sharedPreferences.getString("number",""));
        back.setOnClickListener(this);
        forward.setOnClickListener(this);
        one.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length()==1){
                    two.requestFocus();
                }
                else{
                    one.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        two.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().trim().length()==1){
                    three.requestFocus();
                }
                else{
                    two.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        three.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().trim().length()==1){
                    four.requestFocus();
                }
                else{
                    three.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        four.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length()==1){
                    four.requestFocus();
                }
                else{
                    one.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return v;

    }

    @Override
    public void onClick(View v) {

        if (v == back) {
            Toast.makeText(getActivity().getBaseContext(), "Back", Toast.LENGTH_SHORT).show();
            FragmentManager fm = getFragmentManager();

            FragmentTransaction ft = fm.beginTransaction();

            VerificationCode vc = (VerificationCode) fm.findFragmentByTag("vc");

            if (vc != null) {

                Log.i("TAG", "Removing");
                ft.remove(vc);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();

            }

            fm = getFragmentManager();

            ft = fm.beginTransaction();

            PhoneNumber ph = (PhoneNumber) fm.findFragmentByTag("ph");

            if (ph == null) {
                Log.i("TAG", "Adding");
                ph = new PhoneNumber();
                ft.add(R.id.welcome, ph, "ph");
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();

            }

        } else if (v == forward) {
            FragmentManager fm = getFragmentManager();

            FragmentTransaction ft = fm.beginTransaction();

            VerificationCode vc = (VerificationCode) fm.findFragmentByTag("vc");

            if (vc != null) {

                Log.i("TAG", "Removing");
                ft.remove(vc);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();

            }

            fm = getFragmentManager();

            ft = fm.beginTransaction();

            Information ph = (Information) fm.findFragmentByTag("in");

            if (ph == null) {
                Log.i("TAG", "Adding");
                ph = new Information();
                ft.add(R.id.welcome, ph, "in");
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();

            }
            /*if (!isNetworkaAvailable()){
                Toast.makeText(getActivity(), "Network is not Available", Toast.LENGTH_LONG).show();
            }else {
                String code = one.getText().toString().trim() + two.getText().toString().trim() + three.getText().toString().trim() + four.getText().toString().trim();
                if (code.length() != 4) {
                    Toast.makeText(getActivity().getBaseContext(), "Please Enter Complete Code", Toast.LENGTH_LONG).show();

                } else {
                    VerifyCode co = new VerifyCode();
                    co.execute("http://awamitest.azurewebsites.net/passenger/verifycode", code);


                }
            }*/
        }
    }
}

