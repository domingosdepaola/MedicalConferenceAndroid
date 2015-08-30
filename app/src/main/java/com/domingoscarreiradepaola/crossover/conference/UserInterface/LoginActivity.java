package com.domingoscarreiradepaola.crossover.conference.UserInterface;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.domingoscarreiradepaola.crossover.conference.BL.Client.AppClient;
import com.domingoscarreiradepaola.crossover.conference.BL.LoginBL;
import com.domingoscarreiradepaola.crossover.conference.Common.AlertUtil;
import com.domingoscarreiradepaola.crossover.conference.Common.SharedPreferencesUtil;
import com.domingoscarreiradepaola.crossover.conference.Entity.User;
import com.domingoscarreiradepaola.crossover.conference.Entity.UserProfile;
import com.domingoscarreiradepaola.crossover.conference.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {


    @Bean
    public LoginBL loginBL;
    @ViewById
    public EditText userEditText;
    @ViewById
    public EditText passwordEditText;

    @Bean
    public AppClient appClient;

    @AfterViews
    public void init() {

    }

    @Click(R.id.loginButton)
    public void loginClick() {
        boolean sucess = false;
        try {

            sucess = this.loginBL.login(userEditText.getText().toString(), passwordEditText.getText().toString());

        } catch (Exception ex) {
            sucess = false;
        }
        if (sucess) {
            SharedPreferencesUtil.save(this, this.getString(R.string.conference_id_selected), String.valueOf(""));
            ListConferenceActivity_.intent(this).start();
        } else {
            AlertUtil.showOkAlert(this, super.getString(R.string.ok), super.getString(R.string.invalid_user));
        }

    }
    @Click(R.id.syncUserButton)
    public void syncUsers(){
        this.appClient.syncAll();
        Toast.makeText(this, R.string.syncronized, Toast.LENGTH_LONG).show();
    }
}
