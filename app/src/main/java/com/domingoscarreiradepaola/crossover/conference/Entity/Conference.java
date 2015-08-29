package com.domingoscarreiradepaola.crossover.conference.Entity;

import com.domingoscarreiradepaola.crossover.conference.DAO.ConferenceDao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

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
    public String Room;

    @DatabaseField(canBeNull = true, foreign = true, columnName = "IdPresenterUser")
    public User PresenterUser;
    public int IdPresenterUser;

    @DatabaseField(canBeNull = false,foreign = false,columnName = "IdUser")
    public User CreatedUser;
    public int IdUser;
}
