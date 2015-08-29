package com.domingoscarreiradepaola.crossover.conference.Entity;

import com.domingoscarreiradepaola.crossover.conference.DAO.UserProfileDao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by domin on 28/08/2015.
 */
@DatabaseTable(daoClass = UserProfileDao.class)
public class UserProfile {
    @DatabaseField(allowGeneratedIdInsert = true,generatedId = true)
    public int Id;
    @DatabaseField
    public String ProfileName;
    @DatabaseField
    public Date DT_Created;
    @DatabaseField
    public boolean IsAdm;
}
