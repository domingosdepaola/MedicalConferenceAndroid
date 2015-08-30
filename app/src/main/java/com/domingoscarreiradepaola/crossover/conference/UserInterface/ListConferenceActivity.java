package com.domingoscarreiradepaola.crossover.conference.UserInterface;

import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.domingoscarreiradepaola.crossover.conference.BL.ConferenceBL;
import com.domingoscarreiradepaola.crossover.conference.BL.LoginBL;
import com.domingoscarreiradepaola.crossover.conference.Common.SharedPreferencesUtil;
import com.domingoscarreiradepaola.crossover.conference.Entity.Conference;
import com.domingoscarreiradepaola.crossover.conference.Entity.User;
import com.domingoscarreiradepaola.crossover.conference.R;
import com.domingoscarreiradepaola.crossover.conference.UserInterface.Adapter.ConferenceAdapter;
import com.domingoscarreiradepaola.crossover.conference.UserInterface.ItemView.ConferenceItemView;
import com.getbase.floatingactionbutton.AddFloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.codehaus.jackson.map.ObjectMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_list_confere)
@OptionsMenu(R.menu.menu_list_confere)
public class ListConferenceActivity extends ActionBarActivity {

    @ViewById
    public AddFloatingActionButton buttonAddConference;

    @ViewById
    public ListView listViewConferences;
    @ViewById
    public TextView txtInfoDoctor;
    @ViewById
    public TextView txtInfoAdm;
    @Bean
    public ConferenceBL conferenceBL;

    private boolean admProfile;


    @Bean
    public LoginBL loginBL;
    private List<Conference> listConferences;

    @AfterViews
    public void init() {
        adjustForProfile();
        populateSpinner();
    }

    private void adjustForProfile() {
        User userLogged = loginBL.getLoggedUser();
        if (userLogged.UserProfile != null) {
            if (userLogged.UserProfile.IsAdm) {
                admProfile = true;
                fillConferecesAdm();
                txtInfoAdm.setVisibility(View.VISIBLE);
                txtInfoDoctor.setVisibility(View.GONE);
            } else {
                admProfile = false;
                fillConferencesDoctor(userLogged.Id);
                buttonAddConference.setVisibility(View.GONE);
                txtInfoAdm.setVisibility(View.GONE);
                txtInfoDoctor.setVisibility(View.VISIBLE);
            }
        }
    }
    @OptionsItem(R.id.menu_sugestions)
    public void goSuggestions(){
        SuggestionsActivity_.intent(this).start();
    }
    private void populateSpinner() {
        if (listConferences != null && listConferences.size() > 0) {
            ConferenceAdapter conferenceAdapter = new ConferenceAdapter(this,listConferences);
            listViewConferences.setAdapter(conferenceAdapter.getAdapter());
            listViewConferences.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Conference item = listConferences.get(position);
                    selectItem(item.Id);
                }
            });
        } else {
            String[] arrayEmpty = new String[]{"0 medical conferences found"};
            ArrayAdapter<String> adapterEmpty = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayEmpty);
            listViewConferences.setAdapter(adapterEmpty);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (admProfile) {
            menu.findItem(R.id.menu_invite).setVisible(true);
        } else {
            menu.findItem(R.id.menu_invite).setVisible(false);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    private void selectItem(int id) {
        SharedPreferencesUtil.save(this, this.getString(R.string.conference_id_selected), String.valueOf(id));
        ConferenceActivity_.intent(this).start();
    }

    @Click(R.id.buttonAddConference)
    public void onButtonAddConferenceClick() {
        SharedPreferencesUtil.save(this, this.getString(R.string.conference_id_selected), String.valueOf(""));
        ConferenceActivity_.intent(this).start();
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

    private void fillConferencesDoctor(int idDoctor) {
        listConferences = conferenceBL.getByInvitedDoctor(idDoctor);
    }

    private void fillConferecesAdm() {
        try {
            listConferences = conferenceBL.conferenceDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @OptionsItem(R.id.menu_invite)
    public void goInvite() {
        InviteActivity_.intent(this).start();
    }
}
