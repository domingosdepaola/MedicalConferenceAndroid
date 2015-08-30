package com.domingoscarreiradepaola.crossover.conference.BL;


import com.domingoscarreiradepaola.crossover.conference.DAO.DatabaseHelper;
import com.domingoscarreiradepaola.crossover.conference.Entity.Conference;
import com.domingoscarreiradepaola.crossover.conference.Entity.Invite;
import com.domingoscarreiradepaola.crossover.conference.Interfaces.IConferenceDao;
import com.domingoscarreiradepaola.crossover.conference.Interfaces.IInviteDao;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.OrmLiteDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by domin on 29/08/2015.
 */
//Business Logic
@EBean
public class ConferenceBL {
    @OrmLiteDao(helper = DatabaseHelper.class)
    public IConferenceDao conferenceDao;
    @OrmLiteDao(helper = DatabaseHelper.class)
    public IInviteDao inviteDao;

    public List<Conference> getByInvitedDoctor(int idDoctor) {
        List<Conference> lstConference = null;
        List<Invite> lstInvites = inviteDao.getByIdDoctor(idDoctor);

        for (Invite invite : lstInvites) {
            if(lstConference == null){
                lstConference = new ArrayList<Conference>();
            }
            try {
                if(invite.rejected == false && invite.acepted == false) {
                    Conference conference = conferenceDao.queryForId(invite.IdConference);
                    lstConference.add(conference);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return lstConference;
    }
}
