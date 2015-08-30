package com.domingoscarreiradepaola.crossover.conference.UserInterface;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.domingoscarreiradepaola.crossover.conference.BL.ConferenceBL;
import com.domingoscarreiradepaola.crossover.conference.Common.SharedPreferencesUtil;
import com.domingoscarreiradepaola.crossover.conference.Entity.Conference;
import com.domingoscarreiradepaola.crossover.conference.Entity.Pessoa;
import com.domingoscarreiradepaola.crossover.conference.R;
import com.getbase.floatingactionbutton.AddFloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@EActivity(R.layout.activity_list_confere)
@OptionsMenu(R.menu.menu_list_confere)
public class ListConferenceActivity extends ActionBarActivity {

    @ViewById
    public AddFloatingActionButton buttonAddConference;

    @ViewById
    public ListView listViewConferences;

    @Bean
    public ConferenceBL conferenceBL;

    private List<Conference> listConferences;

    @AfterViews
    public  void init(){
        FillConfereces();
        ArrayAdapter<Conference> adapter = new ArrayAdapter<Conference>(this, android.R.layout.simple_list_item_1, listConferences);
        listViewConferences.setAdapter(adapter);
        listViewConferences.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Conference item = listConferences.get(position);
                selectItem(item.Id);
            }
        });
    }
    private void selectItem(int id){
        SharedPreferencesUtil.save(this, this.getString(R.string.conference_id_selected), String.valueOf(id));
        ConferenceActivity_.intent(this).start();
    }
    @OptionsItem(R.id.menu_sugestions)
    public void goSeeSugestions(){
        String x = "";
    }
    @Click(R.id.buttonAddConference)
    public void onButtonAddConferenceClick() {
        SharedPreferencesUtil.save(this, this.getString(R.string.conference_id_selected), String.valueOf(""));
        ConferenceActivity_.intent(this).start();
    }
    private void FillConfereces(){
        try {
            listConferences = conferenceBL.conferenceDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
