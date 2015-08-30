package com.domingoscarreiradepaola.crossover.conference.UserInterface;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.domingoscarreiradepaola.crossover.conference.BL.LoginBL;
import com.domingoscarreiradepaola.crossover.conference.R;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

import java.sql.SQLException;

@EActivity(R.layout.activity_suggestions)
@OptionsMenu(R.menu.menu_suggestions)
public class SuggestionsActivity extends ActionBarActivity {

    @Bean
    public LoginBL loginBL;

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
    public void goList() {
        ListConferenceActivity_.intent(this).start();
    }
}
