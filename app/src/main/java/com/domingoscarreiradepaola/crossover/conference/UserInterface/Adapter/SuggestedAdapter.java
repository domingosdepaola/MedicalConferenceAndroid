package com.domingoscarreiradepaola.crossover.conference.UserInterface.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.domingoscarreiradepaola.crossover.conference.Entity.Suggestion;
import com.domingoscarreiradepaola.crossover.conference.UserInterface.ItemView.SuggestionItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by domin on 30/08/2015.
 */
public class SuggestedAdapter {
    List<Suggestion> objects = null;
    Context context;
    public SuggestedAdapter(Context context, List<Suggestion> objects) {
        this.objects = objects;
        this.context = context;
    }
    public ArrayAdapter<SuggestionItemView> getAdapter(){
        List<SuggestionItemView> lstSuggestionItemView = new ArrayList<SuggestionItemView>();
        for(Suggestion item : objects){
            SuggestionItemView itemView = new SuggestionItemView();
            itemView.Title = item.Title;
            itemView.User = item.User;
            if(item.User != null) {
                itemView.User.Nome = item.User.Nome;
                itemView.User.Id = item.User.Id;
            }
            itemView.Id = item.Id;
            lstSuggestionItemView.add(itemView);
        }
        ArrayAdapter<SuggestionItemView> adapter = new ArrayAdapter<SuggestionItemView>(this.context, android.R.layout.simple_list_item_1, lstSuggestionItemView);
        return adapter;
    }
}
