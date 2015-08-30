package com.domingoscarreiradepaola.crossover.conference.Entity;

import com.domingoscarreiradepaola.crossover.conference.DAO.UserDao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by domin on 28/08/2015.
 */
@DatabaseTable(daoClass = UserDao.class)
public class User {
    public  static final String ID_FIELD_NAME = "Id";
    public static final String LOGIN_FIELD_NAME = "Login";
    public static final String PASSWORD_FIELD_NAME="Password";
    public static final String STATUS_FIELD_NAME = "Status";
    public static final String ID_PROFILE_FIELD_NAME = "IdProfile";
    @DatabaseField(allowGeneratedIdInsert = true,generatedId = true)
    public int Id;
    @DatabaseField
    public String Nome;

    @DatabaseField
    public String Login;

    @DatabaseField
    public String Password;

    @DatabaseField
    public String IdGcm;

    @DatabaseField
    public String Status;

    @DatabaseField(canBeNull = true, foreign = true, columnName = "IdProfile")
    public UserProfile UserProfile;
    public int IdProfile;

    @Override
    public String toString(){
        return this.Nome;
    }
}
