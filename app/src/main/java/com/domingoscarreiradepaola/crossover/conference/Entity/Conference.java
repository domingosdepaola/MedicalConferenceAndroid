package com.domingoscarreiradepaola.crossover.conference.Entity;

import com.domingoscarreiradepaola.crossover.conference.Common.DateUtil;
import com.domingoscarreiradepaola.crossover.conference.DAO.ConferenceDao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * Created by domin on 29/08/2015.
 */
@DatabaseTable(daoClass = ConferenceDao.class)
public class Conference {
    @DatabaseField(allowGeneratedIdInsert = true,generatedId = true)
    public int Id;

    @DatabaseField
    public String Title;

    @DatabaseField
    public String Description;

    @DatabaseField
    public Date ConferenceDate;

    @DatabaseField
    public int Duration;

    @DatabaseField
    public String Room;

    @DatabaseField
    public int IdPresenterUser;

    @DatabaseField
    public int IdCreatedUser;

    public Date getEndDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ConferenceDate);
        calendar.add(Calendar.HOUR, Duration);
        return calendar.getTime();
    }
}
