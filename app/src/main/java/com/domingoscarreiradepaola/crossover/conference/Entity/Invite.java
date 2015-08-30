package com.domingoscarreiradepaola.crossover.conference.Entity;

import com.domingoscarreiradepaola.crossover.conference.DAO.InviteDao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by domin on 29/08/2015.
 */
@DatabaseTable(daoClass = InviteDao.class)
public class Invite {
    public static final String USER_INVITED_FIELD = "IdUserInvited";
    public static final String CONFERENCE_FIELD = "IdConference";
    public static final String USER_CREATED_FIELD = "IdUserCreated";
    @DatabaseField(allowGeneratedIdInsert = true,generatedId = true)
    public int Id;

    @DatabaseField
    public int IdUserInvited;
    @DatabaseField
    public int IdConference;
    @DatabaseField
    public int IdUserCreated;

    @DatabaseField
    public String Status;

    @DatabaseField
    public Date InvitationDate;

}
