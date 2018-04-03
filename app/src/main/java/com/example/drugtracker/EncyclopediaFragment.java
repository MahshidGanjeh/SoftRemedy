package com.example.drugtracker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class EncyclopediaFragment extends Fragment {


    RecyclerView encyclopedia;

    public EncyclopediaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View resultView = inflater.inflate(R.layout.fragment_encyclopedia, container, false);
        Drug drug = new Drug();
        drug.setName("امیکاسین");
        drug.setCommercialName("Amikin");

        Drug drug1 = new Drug();
        drug1.setName("ایزوفلوران");
        drug1.setCommercialName("Florane");

        Drug drug2 = new Drug();
        drug2.setName("ایندومتاسین");
        drug2.setCommercialName("Indocid");

        ArrayList<Drug> list = new ArrayList<>();
        list.add(drug);
        list.add(drug1);
        list.add(drug2);

        Log.d("Count", String.valueOf(list.size()));

        encyclopedia = (RecyclerView) resultView.findViewById(R.id.encyclopedia_recyclerView);
        EncyclopediaAdapter adapter = new EncyclopediaAdapter(list, getContext());
        encyclopedia.setAdapter(adapter);
        encyclopedia.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        return resultView;
    }
}