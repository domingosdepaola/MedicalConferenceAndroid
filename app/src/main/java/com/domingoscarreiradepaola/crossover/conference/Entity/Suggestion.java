package com.domingoscarreiradepaola.crossover.conference.Entity;

import com.domingoscarreiradepaola.crossover.conference.DAO.SuggestionDao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by domin on 30/08/2015.
 */
@DatabaseTable(daoClass = SuggestionDao.class)
public class Suggestion {
    public  static final String ID_FIELD_NAME = "Id";
    public static final String ID_USER_FIELD_NAME = "IdUser";

    @DatabaseField(allowGeneratedIdInsert = true,generatedId = true)
    public int Id;

    @DatabaseField
    public String Title;

    @DatabaseField(canBeNull = true, foreign = true, columnName = "IdUser")
    public User User;
    public int IdUser;

}
