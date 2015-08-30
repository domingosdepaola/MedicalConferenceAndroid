package com.domingoscarreiradepaola.crossover.conference.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by domin on 29/08/2015.
 */
@DatabaseTable
public class Invite {
    @DatabaseField(allowGeneratedIdInsert = true,generatedId = true)
    public int Id;

    @DatabaseField(canBeNull = true, foreign = true, columnName = "IdUserInvited")
    public User UserInvited;
    public int IdUserInvited;

    @DatabaseField(canBeNull = true, foreign = true, columnName = "IdConference")
    public Conference Conference;
    public int IdConference;

    @DatabaseField(canBeNull = false, foreign = true, columnName = "IdUserCreated")
    public User UserCreated;
    public int IdUserCreated;


}
