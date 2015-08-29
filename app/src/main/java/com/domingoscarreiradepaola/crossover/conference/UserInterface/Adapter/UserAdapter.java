package com.domingoscarreiradepaola.crossover.conference.UserInterface.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.domingoscarreiradepaola.crossover.conference.Common.userinterface.BaseArrayAdapter;
import com.domingoscarreiradepaola.crossover.conference.Entity.User;
import com.domingoscarreiradepaola.crossover.conference.UserInterface.ItemView.UserItemView;
import com.domingoscarreiradepaola.crossover.conference.UserInterface.ItemView.UserItemView_;

import java.util.List;

/**
 * Created by domin on 29/08/2015.
 */
public class UserAdapter  extends BaseArrayAdapter<User> {
    private List<User> objects;

    public UserAdapter(Context context, List<User> objects) {

        super(context, 0, 0, objects);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        UserItemView uisuarioItemView;// = new UserItemView(super.getContext());
        if (convertView == null) {
            uisuarioItemView = UserItemView_.build(super.getContext());
        } else {
            uisuarioItemView = (UserItemView) convertView;
        }
        uisuarioItemView.bind(getItem(position));

        return uisuarioItemView;
    }
}
