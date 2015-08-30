package com.domingoscarreiradepaola.crossover.conference.Interfaces;

import com.domingoscarreiradepaola.crossover.conference.Entity.Invite;
import com.j256.ormlite.dao.Dao;

import java.util.List;

/**
 * Created by domin on 30/08/2015.
 */
public interface IInviteDao extends Dao<Invite,Integer> {
    List<Invite> getInviteBy(Invite invite);
    List<Invite> getByIdDoctor(int idDoctor);
    Invite getBy(int idDoctor,int idConference);
}
