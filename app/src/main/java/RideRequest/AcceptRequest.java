package RideRequest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.iqraa.driverui.MapsActivity;
import com.example.iqraa.driverui.R;

/**
 * Created by iqraa on 9/11/2017.
 */

public class AcceptRequest extends Fragment implements View.OnClickListener {

    ImageView up,down,bottom_up;
    LinearLayout detail;
    private BottomSheetBehavior mBottomSheetBehavior2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v=inflater.inflate(R.layout.accept_req,container,false);
        up=(ImageView)v.findViewById(R.id.up_arrow);
        down=(ImageView)v.findViewById(R.id.down_arrow);
        detail=(LinearLayout)v.findViewById(R.id.detail);
        bottom_up=(ImageView)v.findViewById(R.id.bottom_icon);
        bottom_up.setOnClickListener(this);
        up.setOnClickListener(this);
        down.setOnClickListener(this);
        /////////
        //-----bottom sheet----
        View bottomSheet = v.findViewById(R.id.bottom_sheet2);
        mBottomSheetBehavior2 = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior2.setHideable(true);
        mBottomSheetBehavior2.setPeekHeight(60);
        mBottomSheetBehavior2.setState(BottomSheetBehavior.STATE_COLLAPSED);
        /////
        mBottomSheetBehavior2.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    bottom_up.setImageResource(R.drawable.down);
                }
                else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottom_up.setImageResource(R.drawable.up);
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
            }
        });
        //-----bottom sheet----

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v==up){
            detail.setVisibility(View.GONE);
            down.setVisibility(View.VISIBLE);
        }else if (v==down){
            down.setVisibility(View.GONE);
            detail.setVisibility(View.VISIBLE);
        }else if (v==bottom_up){
            if (mBottomSheetBehavior2.getState()== BottomSheetBehavior.STATE_EXPANDED) {
                mBottomSheetBehavior2.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
            else if (mBottomSheetBehavior2.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                mBottomSheetBehavior2.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        }
    }
}
