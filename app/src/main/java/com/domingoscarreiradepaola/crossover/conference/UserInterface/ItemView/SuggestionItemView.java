package com.domingoscarreiradepaola.crossover.conference.UserInterface.ItemView;

import com.domingoscarreiradepaola.crossover.conference.Entity.Suggestion;

/**
 * Created by domin on 30/08/2015.
 */
public class SuggestionItemView extends Suggestion {
    @Override
    public String toString(){
        return this.Title + " - suggested by: " + this.User.Nome;
    }
}
