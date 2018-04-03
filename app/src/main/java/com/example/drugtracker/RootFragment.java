package com.example.drugtracker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class RootFragment extends Fragment {


    public RootFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_root, container, false);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

    /*
     * When this container fragment is created, we fill it with our first
     * "real" fragment
     */
        transaction.replace(R.id.frame, new PillBoxFragment());

        transaction.commit();

        return view;
    }


}
