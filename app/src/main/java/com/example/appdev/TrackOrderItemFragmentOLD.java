package com.example.appdev;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TrackOrderItemFragmentOLD extends Fragment {


    Button doneButton;
    View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_track_order_old, container, false);

        doneButton = rootView.findViewById(R.id.btnDone);
        doneButton.setOnClickListener(new GoBackClickListener());


        return rootView;

    }


    private class GoBackClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            getActivity().onBackPressed();
        }
    }

}
