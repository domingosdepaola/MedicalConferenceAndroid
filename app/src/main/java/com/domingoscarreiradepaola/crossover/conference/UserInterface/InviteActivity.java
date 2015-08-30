package com.domingoscarreiradepaola.crossover.conference.UserInterface;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.domingoscarreiradepaola.crossover.conference.BL.ConferenceBL;
import com.domingoscarreiradepaola.crossover.conference.BL.InviteBL;
import com.domingoscarreiradepaola.crossover.conference.BL.LoginBL;
import com.domingoscarreiradepaola.crossover.conference.BL.UserBL;
import com.domingoscarreiradepaola.crossover.conference.Common.AlertUtil;
import com.domingoscarreiradepaola.crossover.conference.Entity.Conference;
import com.domingoscarreiradepaola.crossover.conference.Entity.Invite;
import com.domingoscarreiradepaola.crossover.conference.Entity.User;
import com.domingoscarreiradepaola.crossover.conference.R;
import com.domingoscarreiradepaola.crossover.conference.UserInterface.Adapter.ConferenceAdapter;
import com.domingoscarreiradepaola.crossover.conference.UserInterface.Adapter.UserAdapter;
import com.domingoscarreiradepaola.crossover.conference.UserInterface.ItemView.ConferenceItemView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@EActivity(R.layout.activity_invite)
@OptionsMenu(R.menu.menu_invite)
public class InviteActivity extends ActionBarActivity {


    @ViewById
    public Spinner spinnerDoctor;

    @ViewById
    public Spinner spinnerConference;
    @Bean
    public UserBL userBL;
    @Bean
    public ConferenceBL conferenceBL;
    @Bean
    public LoginBL loginBL;
    @Bean
    public InviteBL inviteBL;

    List<User> listDoctor;
    List<Conference> listConference;



    private UserAdapter userAdapter;
    @AfterViews
    public void init(){
        fillDoctorSpinner();
        fillConferenceSpinner();
    }
    private void fillDoctorSpinner(){
        try {
            listDoctor = this.userBL.getDoctors();
        } catch (Exception ex) {

        }
        if (listDoctor != null) {
            this.userAdapter = new UserAdapter(this, listDoctor);
            this.userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            this.spinnerDoctor.setAdapter(userAdapter);
        }
    }
    private void fillConferenceSpinner(){
        try{
            listConference = this.conferenceBL.conferenceDao.queryForAll();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        if(listConference != null){
            ConferenceAdapter conferenceAdapter = new ConferenceAdapter(this,listConference);
            spinnerConference.setAdapter(conferenceAdapter.getAdapter());
        }
    }
    @OptionsItem(R.id.saveInvite)
    public void onSaveInvite() {
        saveInvite();
    }
    private void saveInvite(){
        Invite invite = getForm();
        if(this.inviteBL.inviteAlreadyExists(invite)){
            AlertUtil.showOkAlert(this,"Ops",this.getString(R.string.invite_exists));
        }else {
            boolean sucess = false;
            try {
                this.inviteBL.inviteDao.createOrUpdate(invite);
                sucess = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(sucess){
                Toast.makeText(this, R.string.invitation_send, Toast.LENGTH_SHORT).show();
            }else{
                AlertUtil.showOkAlert(this,"Error",this.getString(R.string.default_error_msg));
            }
        }
    }
    private Invite getForm(){
        int idDoctor = ((User) spinnerDoctor.getSelectedItem()).Id;
        int idConference = ((Conference)spinnerConference.getSelectedItem()).Id;
        Invite invite = new Invite();
        invite.IdConference = idConference;
        invite.IdUserInvited = idDoctor;
        invite.IdUserCreated = loginBL.getLoggedUser().Id;
        invite.InvitationDate = new Date();
        invite.acepted = false;
        invite.rejected = false;
        return invite;
    }
    @OptionsItem(R.id.menu_logout)
    public void logout() {
        try {
            this.loginBL.logout();
            LoginActivity_.intent(this).start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @OptionsItem(R.id.menuList)
    public void goList(){
        ListConferenceActivity_.intent(this).start();
    }


}
