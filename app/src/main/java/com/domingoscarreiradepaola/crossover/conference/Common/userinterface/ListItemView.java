package com.domingoscarreiradepaola.crossover.conference.Common.userinterface;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.domingoscarreiradepaola.crossover.conference.Common.ListItem;
import com.domingoscarreiradepaola.crossover.conference.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;


/**
 * Created by marcelo.santos on 19/02/2015.
 */
@EViewGroup(R.layout.item_list)
public class ListItemView extends LinearLayout {

    @ViewById
    public TextView textViewTitulo;

    @ViewById
    public TextView textViewValor;

    public ListItemView(Context context) {
        super(context);
    }

    public void bind(ListItem listItem) {

        this.textViewTitulo.setText(listItem.Titulo);
        this.textViewValor.setText(listItem.Valor);
    }
}
