package com.reginalddc.bahalanapp.Model;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.reginalddc.bahalanapp.R;

import java.util.ArrayList;

/**
 * Created by reginalddc on 27/02/2017.
 */
public class RestoAdapter extends ArrayAdapter<RestoBaseAdapter> {

    public RestoAdapter(Context context, ArrayList<RestoBaseAdapter> restoBaseAdapter){
        super(context, 0, restoBaseAdapter);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        RestoBaseAdapter restoBaseAdapter = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_toptenresto, parent, false);
        }

        if (position % 2 == 0) {
            convertView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.whiteColor));
        } else {
            convertView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.lightGrayColor));
        }

        TextView name = (TextView) convertView.findViewById(R.id.resto_textView);
        TextView rank = (TextView) convertView.findViewById(R.id.rank_textView);

        int id = restoBaseAdapter.id;
        rank.setText(Integer.toString(restoBaseAdapter.rank));
        name.setText(restoBaseAdapter.name);

        return convertView;
    }
}
