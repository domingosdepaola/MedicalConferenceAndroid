package com.domingoscarreiradepaola.crossover.conference.Common.userinterface;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.domingoscarreiradepaola.crossover.conference.Common.ListItem;
import com.domingoscarreiradepaola.crossover.conference.UserInterface.ItemView.SpinnerItemView;
import com.domingoscarreiradepaola.crossover.conference.UserInterface.ItemView.SpinnerItemView_;

import java.util.List;

/**
 * Created by domin on 29/08/2015.
 */
public class SpinnerAdapter extends BaseArrayAdapter<ListItem>{


    private List<ListItem> objects;
    public SpinnerAdapter(Context context, List objects) {
        super(context, 0, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SpinnerItemView spinnerItemView; //= new SpinnerItemView(super.getContext());
        if (convertView == null) {
           spinnerItemView = SpinnerItemView_.build(super.getContext());
        } else {
            spinnerItemView = (SpinnerItemView) convertView;
        }
        spinnerItemView.bind(getItem(position));


        return spinnerItemView;
    }
}
