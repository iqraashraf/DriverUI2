package SignUp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.iqraa.driverui.MapsActivity;
import com.example.iqraa.driverui.R;

/**
 * Created by iqraa on 9/8/2017.
 */

public class Information extends Fragment implements View.OnClickListener {
    EditText fname, lname;
    Button button;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ProgressBar progressBar;
    //Request request;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.information, container, false);
        progressBar = (ProgressBar) v.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);
        sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
        fname = (EditText) v.findViewById(R.id.fname);
        lname = (EditText) v.findViewById(R.id.lname);
        button = (Button) v.findViewById(R.id.submit);
        button.setOnClickListener(this);
        //request = new Request();
        return v;


    }
    public void onClick(View v) {
        if (v == button) {

            if (fname.getText().toString().trim().isEmpty()) {
                Toast.makeText(getActivity().getBaseContext(), "Enter First Name", Toast.LENGTH_LONG).show();
            } else if (lname.getText().toString().trim().isEmpty()) {
                Toast.makeText(getActivity().getBaseContext(), "Enter Last Name", Toast.LENGTH_LONG).show();
            } else {
                FragmentManager fm = getFragmentManager();

                FragmentTransaction ft = fm.beginTransaction();

                Information ph = (Information) fm.findFragmentByTag("in");

                if (ph != null) {

                    Log.i("TAG", "Removing");
                    ft.remove(ph);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                    ft.commit();

                }



                startActivity(new Intent(getActivity().getBaseContext(), MapsActivity.class));


                /*if (!isNetworkaAvailable()){
                    Toast.makeText(getActivity().getBaseContext(),"Network in not available",Toast.LENGTH_SHORT).show();
                }else {
                    Inform inform = new Inform();

                    inform.execute("http://awamitest.azurewebsites.net/passenger/register", fname.getText().toString().trim(), lname.getText().toString().trim());

                }*/
            }
        }

    }
}
