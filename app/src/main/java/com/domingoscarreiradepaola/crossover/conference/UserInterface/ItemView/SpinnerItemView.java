package com.domingoscarreiradepaola.crossover.conference.UserInterface.ItemView;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.domingoscarreiradepaola.crossover.conference.Common.ListItem;
import com.domingoscarreiradepaola.crossover.conference.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;


@EViewGroup(R.layout.item_spinner)
public class SpinnerItemView extends LinearLayout {
    @ViewById
    public TextView textViewDescricao;

    public SpinnerItemView(Context context) {
        super(context);
    }

    public void bind(ListItem listItem) {
        this.textViewDescricao.setText(listItem.Valor);
    }
}
