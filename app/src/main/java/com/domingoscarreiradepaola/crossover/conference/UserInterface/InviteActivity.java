package com.domingoscarreiradepaola.crossover.conference.UserInterface;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.domingoscarreiradepaola.crossover.conference.BL.ConferenceBL;
import com.domingoscarreiradepaola.crossover.conference.BL.LoginBL;
import com.domingoscarreiradepaola.crossover.conference.BL.UserBL;
import com.domingoscarreiradepaola.crossover.conference.Entity.Conference;
import com.domingoscarreiradepaola.crossover.conference.Entity.User;
import com.domingoscarreiradepaola.crossover.conference.R;
import com.domingoscarreiradepaola.crossover.conference.UserInterface.Adapter.UserAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewsById;

import java.util.List;

@EActivity(R.layout.activity_invite)
@OptionsMenu(R.menu.menu_invite)
public class InviteActivity extends ActionBarActivity {


    @ViewsById
    public Spinner spinnerDoctor;

    @ViewsById
    public Spinner spinnerConference;
    @Bean
    public UserBL userBL;
    @Bean
    public ConferenceBL conferenceBL;

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
            ArrayAdapter<Conference> adapter = new ArrayAdapter<Conference>(this, android.R.layout.simple_list_item_1, listConference);
            spinnerConference.setAdapter(adapter);
        }
    }
    @OptionsItem(R.id.saveInvite)
    public void onSaveInvite() {

    }


}
