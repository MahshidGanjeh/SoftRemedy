package com.example.drugtracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Hp on 3/15/2018.
 */

public class EncyclopediaAdapter extends
        RecyclerView.Adapter<EncyclopediaAdapter.myViewHolder> {

    private ArrayList<Drug> mDrugList;
    private Context mContext;

    public EncyclopediaAdapter(ArrayList<Drug> drugs, Context context) {
        this.mDrugList = drugs;
        this.mContext = context;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View resultView = LayoutInflater.from(mContext).inflate(R.layout.drug_item, parent, false);

        myViewHolder myViewHolder = new myViewHolder(resultView);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, final int position) {

        String[] alphabets = {"الف", "ب", "پ", "ت", "ث", "ج", "چ", " ح", "خ",
                "د", "ذ", "ر", "ز", "", "س", "ش", "ص", "ض", "ط", "ظ", "ع", "غ", "ف", "ق",
                "ک", "گ", "ل", "م", "ن", "و", " ه", "ی"};

        if (position == 0) {
            holder.headerLetter.setText(alphabets[0]);
        }
        if (position > 0) {
            holder.divider.setVisibility(View.GONE);
            holder.headerLetter.setVisibility(View.GONE);
        }
        holder.drugName.setText(mDrugList.get(position).getName());
        holder.drugCommercialName.setText(mDrugList.get(position).getCommercialName());
    }

    @Override
    public int getItemCount() {
        return mDrugList.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        TextView headerLetter;
        TextView drugName;
        TextView drugCommercialName;
        View divider;

        public myViewHolder(View itemView) {
            super(itemView);
            headerLetter = (TextView) itemView.findViewById(R.id.header_letter_textView);
            drugName = (TextView) itemView.findViewById(R.id.drug_name_textView);
            drugCommercialName = (TextView) itemView.findViewById(R.id.drug_commercial_name_textView);
            divider = itemView.findViewById(R.id.header_letter_divider);
        }
    }
}
