package com.domingoscarreiradepaola.crossover.conference.BL;

import android.content.Context;

import com.domingoscarreiradepaola.crossover.conference.DAO.DatabaseHelper;
import com.domingoscarreiradepaola.crossover.conference.Entity.User;
import com.domingoscarreiradepaola.crossover.conference.Entity.UserProfile;
import com.domingoscarreiradepaola.crossover.conference.Interfaces.IUserDao;
import com.domingoscarreiradepaola.crossover.conference.Interfaces.IUserProfileDao;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.RootContext;

import java.util.List;

/**
 * Created by domin on 29/08/2015.
 */
@EBean
public class UserBL {

    @OrmLiteDao(helper = DatabaseHelper.class)
    public IUserDao userDao;

    @OrmLiteDao(helper = DatabaseHelper.class)
    public IUserProfileDao profileDao;

    @RootContext
    public Context context;

    public List<User> getDoctors(){
        UserProfile profile = this.profileDao.getProfileByType(false);
        List<User> lstUser = this.userDao.getByProfile(profile.Id);
        return lstUser;
    }
}
