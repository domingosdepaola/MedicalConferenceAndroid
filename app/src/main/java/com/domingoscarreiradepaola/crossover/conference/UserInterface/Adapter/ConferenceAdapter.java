package com.domingoscarreiradepaola.crossover.conference.UserInterface.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.domingoscarreiradepaola.crossover.conference.Entity.Conference;
import com.domingoscarreiradepaola.crossover.conference.Entity.User;
import com.domingoscarreiradepaola.crossover.conference.UserInterface.ItemView.ConferenceItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by domin on 30/08/2015.
 */
public class ConferenceAdapter {
    List<Conference> objects = null;
    Context context;
    public ConferenceAdapter(Context context, List<Conference> objects) {
        this.objects = objects;
        this.context = context;
    }

    public ArrayAdapter<ConferenceItemView> getAdapter(){
        List<ConferenceItemView> lstConferenceItemView = new ArrayList<ConferenceItemView>();
        for(Conference item : objects){
            ConferenceItemView itemView = new ConferenceItemView();
            itemView.Title = item.Title;
            itemView.Room = item.Room;
            itemView.ConferenceDate = item.ConferenceDate;
            itemView.Description = item.Description;
            itemView.Id = item.Id;
            itemView.IdCreatedUser = item.IdCreatedUser;
            itemView.IdPresenterUser = item.IdPresenterUser;
            lstConferenceItemView.add(itemView);
        }
        ArrayAdapter<ConferenceItemView> adapter = new ArrayAdapter<ConferenceItemView>(this.context, android.R.layout.simple_list_item_1, lstConferenceItemView);
        return adapter;
    }
}
