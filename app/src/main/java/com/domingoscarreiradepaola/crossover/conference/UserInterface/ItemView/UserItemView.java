package com.domingoscarreiradepaola.crossover.conference.UserInterface.ItemView;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.domingoscarreiradepaola.crossover.conference.Entity.User;
import com.domingoscarreiradepaola.crossover.conference.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by domin on 29/08/2015.
 */
@EViewGroup(R.layout.item_spinner)
public class UserItemView  extends LinearLayout {
    @ViewById
    public TextView textViewDescricao;

    public UserItemView(Context context) {
        super(context);
    }

    public void bind(User usuario) {
        this.textViewDescricao.setText(usuario.Nome);
    }
}
