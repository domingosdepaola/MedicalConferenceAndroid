package com.domingoscarreiradepaola.crossover.conference.BL;

import com.domingoscarreiradepaola.crossover.conference.DAO.DatabaseHelper;
import com.domingoscarreiradepaola.crossover.conference.Entity.Invite;
import com.domingoscarreiradepaola.crossover.conference.Interfaces.IInviteDao;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.OrmLiteDao;

import java.util.List;

/**
 * Created by domin on 30/08/2015.
 */
@EBean
public class InviteBL {


    @OrmLiteDao(helper = DatabaseHelper.class)
    public IInviteDao inviteDao;

    public boolean inviteAlreadyExists(Invite invite){
        List<Invite> lstInvites = inviteDao.getInviteBy(invite);
        if(lstInvites != null && lstInvites.size() > 0){
            return  true;
        }else{
            return false;
        }
    }
}
