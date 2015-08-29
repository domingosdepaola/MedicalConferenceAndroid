package com.domingoscarreiradepaola.crossover.conference.Common.userinterface;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.domingoscarreiradepaola.crossover.conference.Common.ListItem;
import com.domingoscarreiradepaola.crossover.conference.R;

import java.util.List;


public class ListAdapter extends BaseArrayAdapter<ListItem> {

    public ListAdapter(Context context, List<ListItem> objects) {

        super(context, 0, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

                convertView = LayoutInflater.from(super.getContext()).inflate(R.layout.item_list, null);
        }

        TextView textViewTitulo = (TextView)convertView.findViewById(R.id.textViewTitulo);
        TextView textViewValor = (TextView)convertView.findViewById(R.id.textViewValor);

        ListItem item = super.getItem(position);

        textViewTitulo.setText(item.Titulo);
        textViewValor.setText(item.Valor);

        return convertView;
    }

}
