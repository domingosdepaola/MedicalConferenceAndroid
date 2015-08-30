package com.domingoscarreiradepaola.crossover.conference.BL.Client;

import com.domingoscarreiradepaola.crossover.conference.BL.LoginBL;
import com.domingoscarreiradepaola.crossover.conference.Entity.User;
import com.domingoscarreiradepaola.crossover.conference.Entity.UserProfile;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.OrmLiteDao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by domin on 30/08/2015.
 */
//Responsible to get data from web service in the future.
@EBean
public class AppClient {
    @Bean
    public LoginBL loginBL;

    public void syncAll() {
        List<User> lst = null;
        try {
            lst = this.loginBL.userDao.queryForAll();
        } catch (Exception ex) {
        }
        if (lst == null || lst.size() == 0) {
            syncProfiles();
            syncUsers();
        }

    }

    private void syncUsers() {
        try {
            UserProfile profile = this.loginBL.profileDao.getProfileByType(true);
            User user = new User();
            user.Nome = "Conference Administrator";
            user.Login = "adm";
            user.Password = this.loginBL.EncriptPassword("123");
            user.IdProfile = profile.Id;
            user.UserProfile = new UserProfile();
            user.UserProfile.Id = profile.Id;
            user.Status = "A";
            this.loginBL.userDao.createOrUpdate(user);
            profile = new UserProfile();
            profile = this.loginBL.profileDao.getProfileByType(false);
            User doctor = new User();
            doctor.Nome = "Doctor James";
            doctor.Login = "james";
            doctor.Password = this.loginBL.EncriptPassword("123");
            doctor.IdProfile = profile.Id;
            doctor.UserProfile = new UserProfile();
            doctor.UserProfile.Id = profile.Id;
            doctor.Status = "A";
            this.loginBL.userDao.createOrUpdate(doctor);
            doctor = new User();
            doctor.Nome = "Doctor Joseph";
            doctor.Login = "jsp";
            doctor.Password = this.loginBL.EncriptPassword("123");
            doctor.IdProfile = profile.Id;
            doctor.UserProfile = new UserProfile();
            doctor.UserProfile.Id = profile.Id;
            doctor.Status = "A";
            this.loginBL.userDao.createOrUpdate(doctor);
            doctor = new User();
            doctor.Nome = "Doctor Arnold";
            doctor.Login = "arnold";
            doctor.Password = this.loginBL.EncriptPassword("123");
            doctor.IdProfile = profile.Id;
            doctor.UserProfile = new UserProfile();
            doctor.UserProfile.Id = profile.Id;
            doctor.Status = "A";
            this.loginBL.userDao.createOrUpdate(doctor);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void syncProfiles() {
        try {
            UserProfile profile = new UserProfile();
            profile.IsAdm = true;
            profile.DT_Created = new Date();
            profile.ProfileName = "Admin";
            this.loginBL.profileDao.createOrUpdate(profile);
            profile = new UserProfile();
            profile.IsAdm = false;
            profile.DT_Created = new Date();
            profile.ProfileName = "Doctor";
            this.loginBL.profileDao.createOrUpdate(profile);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
