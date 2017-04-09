package com.reginalddc.bahalanapp.Fragment.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reginalddc.bahalanapp.Model.Resto;
import com.reginalddc.bahalanapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantDetailFragment extends Fragment {

    TextView title_opening, detail_opening, title_best, detail_best, title_price, detail_price,
            title_contact, detail_contact, title_visit, detail_visit, title_location,
            detail_location, title_rate;

    public RestaurantDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_restaurant_detail, container, false);

        title_opening = (TextView) fragment.findViewById(R.id.title_opening);
        detail_opening = (TextView) fragment.findViewById(R.id.detail_opening);
        title_best = (TextView) fragment.findViewById(R.id.title_best);
        detail_best = (TextView) fragment.findViewById(R.id.detail_best);
        title_price = (TextView) fragment.findViewById(R.id.title_price);
        detail_price = (TextView) fragment.findViewById(R.id.detail_price);
        title_contact = (TextView) fragment.findViewById(R.id.title_contact);
        detail_contact = (TextView) fragment.findViewById(R.id.detail_contact);
        title_visit = (TextView) fragment.findViewById(R.id.title_visit);
        detail_visit = (TextView) fragment.findViewById(R.id.detail_visit);
        title_location = (TextView) fragment.findViewById(R.id.title_location);
        detail_location = (TextView) fragment.findViewById(R.id.detail_location);
        title_rate = (TextView) fragment.findViewById(R.id.title_rate);

        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial_rounded.ttf");

        title_opening.setTypeface(typeface);
        detail_opening.setTypeface(typeface);
        title_best.setTypeface(typeface);
        detail_best.setTypeface(typeface);
        title_price.setTypeface(typeface);
        detail_price.setTypeface(typeface);
        title_contact.setTypeface(typeface);
        detail_contact.setTypeface(typeface);
        title_visit.setTypeface(typeface);
        detail_visit.setTypeface(typeface);
        title_location.setTypeface(typeface);
        detail_location.setTypeface(typeface);
        title_rate.setTypeface(typeface);

        willView();
        return fragment;
    }

    private void willView() {
        detail_opening.setText(Resto.getSelectedRestoHours());
        detail_best.setText(Resto.getSelectedRestoBest());
        detail_price.setText(Resto.getSelectedRestoPrice());
        detail_contact.setText(Resto.getSelectedRestoContact());
        detail_visit.setText(Resto.getSelectedRestoVisit());
        detail_location.setText(Resto.getSelectedRestoDetailedLoc());
    }
}
