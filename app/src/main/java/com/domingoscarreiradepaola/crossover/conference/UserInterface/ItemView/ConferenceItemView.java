package com.domingoscarreiradepaola.crossover.conference.UserInterface.ItemView;

import com.domingoscarreiradepaola.crossover.conference.Common.DateUtil;
import com.domingoscarreiradepaola.crossover.conference.Entity.Conference;

/**
 * Created by domin on 30/08/2015.
 */
public class ConferenceItemView extends Conference {

    @Override
    public String toString(){
        return this.Title + "  -  " + DateUtil.format(this.ConferenceDate) + " - Room: " + this.Room;
    }
}
