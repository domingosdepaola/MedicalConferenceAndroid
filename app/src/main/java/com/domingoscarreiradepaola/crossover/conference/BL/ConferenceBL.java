package com.domingoscarreiradepaola.crossover.conference.BL;


import com.domingoscarreiradepaola.crossover.conference.DAO.DatabaseHelper;
import com.domingoscarreiradepaola.crossover.conference.Interfaces.IConferenceDao;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.OrmLiteDao;

/**
 * Created by domin on 29/08/2015.
 */
@EBean
public class ConferenceBL {
    @OrmLiteDao(helper = DatabaseHelper.class)
    public IConferenceDao conferenceDao;
}
