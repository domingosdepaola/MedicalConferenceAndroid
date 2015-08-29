package com.domingoscarreiradepaola.crossover.conference.UserInterface;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.domingoscarreiradepaola.crossover.conference.BL.LoginBL;
import com.domingoscarreiradepaola.crossover.conference.Common.AlertUtil;
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

    @AfterViews
    public void init() {
        List<User> lst = null;
        try {
            lst = this.loginBL.userDao.queryForAll();
        } catch (Exception ex) {
        }
        if (lst == null || lst.size() == 0) {
            try {
                UserProfile profile = new UserProfile();
                profile.IsAdm = true;
                profile.DT_Created = new Date();
                profile.ProfileName = "Admin";
                this.loginBL.profileDao.createOrUpdate(profile);
                User user = new User();
                user.Nome = "Conference Administrator";
                user.Login = "adm";
                user.Password = this.loginBL.EncriptPassword("123");
                user.IdProfile = profile.Id;
                user.UserProfile = new UserProfile();
                user.UserProfile.Id = profile.Id;
                user.Status = "A";
                this.loginBL.userDao.createOrUpdate(user);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        try {

            this.loginBL.logout();
        } catch (Exception e) {


        }
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
            ConferenceActivity_.intent(this).start();
        } else {
            AlertUtil.showOkAlert(this, super.getString(R.string.ok), super.getString(R.string.invalid_user));
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
