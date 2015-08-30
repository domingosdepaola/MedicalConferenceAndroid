package com.domingoscarreiradepaola.crossover.conference.UserInterface;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.domingoscarreiradepaola.crossover.conference.BL.LoginBL;
import com.domingoscarreiradepaola.crossover.conference.BL.SuggestionBL;
import com.domingoscarreiradepaola.crossover.conference.Common.AlertUtil;
import com.domingoscarreiradepaola.crossover.conference.Common.SharedPreferencesUtil;
import com.domingoscarreiradepaola.crossover.conference.Entity.Suggestion;
import com.domingoscarreiradepaola.crossover.conference.Entity.User;
import com.domingoscarreiradepaola.crossover.conference.R;
import com.domingoscarreiradepaola.crossover.conference.UserInterface.Adapter.SuggestedAdapter;
import com.getbase.floatingactionbutton.AddFloatingActionButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;
import java.util.List;

@EActivity(R.layout.activity_suggestions)
@OptionsMenu(R.menu.menu_suggestions)
public class SuggestionsActivity extends ActionBarActivity {

    @Bean
    public LoginBL loginBL;

    @Bean
    public SuggestionBL suggestionBL;
    @ViewById
    public ListView listViewSuggestions;

    @ViewById
    public AddFloatingActionButton buttonAddSuggestion;
    @ViewById
    public EditText editTextSuggest;

    private List<Suggestion> lstSuggestion;

    private boolean adminProfile;

    @AfterViews
    public void init(){
        adjustForProfile();
        fillListViewSuggestion();
    }
    @OptionsItem(R.id.menuSave)
    public void saveSuggestion(){
        if(editTextSuggest.getText().toString().equals("")){
            AlertUtil.showOkAlert(this,"Ops",getString(R.string.blank_fields));
            return;
        }
        Suggestion suggestion = new Suggestion();
        String suggestionSelected = SharedPreferencesUtil.get(this, this.getString(R.string.suggestion_id_selected));
        if(!suggestionSelected.equals("")){
            Integer idSuggestion = Integer.parseInt(suggestionSelected);
            suggestion.Id = idSuggestion;
        }
        suggestion.Title = editTextSuggest.getText().toString();
        User userLogged = this.loginBL.getLoggedUser();
        suggestion.IdUser = userLogged.Id;
        suggestion.User = userLogged;
        suggestion.User.Id =userLogged.Id;
        boolean sucess = false;
        try {
            suggestionBL.suggestionDao.createOrUpdate(suggestion);
            sucess = true;
            if(sucess) {
                SharedPreferencesUtil.save(this, this.getString(R.string.suggestion_id_selected), String.valueOf(suggestion.Id));
                fillListViewSuggestion();
            }else{
                AlertUtil.showOkAlert(this,"Ops",getString(R.string.default_error_msg));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Click(R.id.buttonAddSuggestion)
    public void onAddSuggestionClick(){
        SharedPreferencesUtil.save(this, this.getString(R.string.suggestion_id_selected), String.valueOf(""));
        editTextSuggest.setText("");
        Toast.makeText(this, R.string.suggestion_add_toast, Toast.LENGTH_LONG).show();
    }
    private void fillListViewSuggestion(){
        try {
            lstSuggestion = suggestionBL.getAllSugestions();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(lstSuggestion != null && lstSuggestion.size() > 0) {
            SuggestedAdapter suggestionAdapter = new SuggestedAdapter(this, lstSuggestion);
            listViewSuggestions.setAdapter(suggestionAdapter.getAdapter());
            listViewSuggestions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Suggestion item = lstSuggestion.get(position);
                    selectItem(item.Id);
                }
            });
        }else {
            String[] arrayEmpty = new String[]{"0 suggestions"};
            ArrayAdapter<String> adapterEmpty = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayEmpty);
            listViewSuggestions.setAdapter(adapterEmpty);
        }
    }
    private void selectItem(int id) {
        SharedPreferencesUtil.save(this, this.getString(R.string.suggestion_id_selected), String.valueOf(id));
        setFormText();
    }
    private void setFormText(){
        String suggestionSelected = SharedPreferencesUtil.get(this, this.getString(R.string.suggestion_id_selected));
        if(!suggestionSelected.equals("")){
            int idSuggestion = Integer.parseInt(suggestionSelected);
            try {
                Suggestion suggestion = suggestionBL.suggestionDao.queryForId(idSuggestion);
                editTextSuggest.setText(suggestion.Title);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private void adjustForProfile() {
        User userLogged = loginBL.getLoggedUser();
        if (userLogged.UserProfile != null) {
            if (userLogged.UserProfile.IsAdm) {
                adminProfile = true;
                editTextSuggest.setVisibility(View.GONE);
                buttonAddSuggestion.setVisibility(View.GONE);

            } else {
                adminProfile = false;
                editTextSuggest.setVisibility(View.VISIBLE);
                buttonAddSuggestion.setVisibility(View.VISIBLE);
            }
        }
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (adminProfile) {
            menu.findItem(R.id.menuSave).setVisible(false);
        } else {
            menu.findItem(R.id.menuSave).setVisible(true);
        }

        return super.onPrepareOptionsMenu(menu);
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
    @OptionsItem(R.id.menu_list)
    public void goList() {
        ListConferenceActivity_.intent(this).start();
    }
}
