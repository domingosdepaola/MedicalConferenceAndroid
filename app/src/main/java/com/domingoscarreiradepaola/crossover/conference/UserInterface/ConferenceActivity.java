package com.domingoscarreiradepaola.crossover.conference.UserInterface;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.domingoscarreiradepaola.crossover.conference.BL.LoginBL;
import com.domingoscarreiradepaola.crossover.conference.Common.AlertUtil;
import com.domingoscarreiradepaola.crossover.conference.Common.DateUtil;
import com.domingoscarreiradepaola.crossover.conference.Entity.Conference;
import com.domingoscarreiradepaola.crossover.conference.Entity.User;
import com.domingoscarreiradepaola.crossover.conference.R;
import com.domingoscarreiradepaola.crossover.conference.UserInterface.Adapter.UserAdapter;
import com.domingoscarreiradepaola.crossover.conference.UserInterface.Dialog.DatePickerFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FocusChange;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@EActivity(R.layout.activity_conference)
@OptionsMenu(R.menu.menu_conference)
public class ConferenceActivity extends ActionBarActivity {

    @ViewById
    public EditText editTextTitle;

    @ViewById
    public EditText editTextDescription;

    @ViewById
    public EditText editTextConferenceDate;

    @ViewById
    public Spinner spinnerUser;

    @ViewById
    public EditText editTextRoom;

    @ViewById
    public EditText editTextConferenceTime;

    @Bean
    public LoginBL loginBL;

    private UserAdapter userAdapter;

    List<User> listUser;

    @OptionsMenuItem(R.id.menuSave)
    MenuItem menuItem;

    @AfterViews
    public void init() {
        try {
            listUser = this.loginBL.userDao.queryForAll();
        } catch (Exception ex) {

        }
        if (listUser != null) {
            this.userAdapter = new UserAdapter(this, listUser);
            this.userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            this.spinnerUser.setAdapter(userAdapter);
        }
    }
    @Click(R.id.editTextConferenceDate)
    public void onClickConferenceDate() {
        setDate();
    }
    private void setDate(){
        DialogFragment ClasseData = DatePickerFragment.newInstance(this.editTextConferenceDate);
        ClasseData.show(super.getSupportFragmentManager(), getString(R.string.datepicker));
    }
    private void setTime(){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                editTextConferenceTime.setText(selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }
    @Click(R.id.editTextConferenceTime)
    public void onClickConferenceTime() {
        setTime();
    }

    @FocusChange(R.id.editTextConferenceTime)
    public void onFocusConferenceTime(View hello, boolean hasFocus) {
        if (hasFocus) {
            setTime();
        }
    }
    @FocusChange(R.id.editTextConferenceDate)
    public void onFocusConferenceDate(View hello, boolean hasFocus) {
        if (hasFocus) {
            setDate();
        }
    }
    @OptionsItem(R.id.menuList)
    public void goList() {
        String x = "list";
    }

    @OptionsItem(R.id.menuSave)
    public void save() {
        try {
            Conference conference = new Conference();
            conference.Title = editTextTitle.getText().toString();
            conference.CreatedUser = this.loginBL.getLoggedUser();
            conference.Description = editTextDescription.getText().toString();
            conference.PresenterUser = new User();
            conference.PresenterUser.Id = ((User) spinnerUser.getSelectedItem()).Id;
            conference.IdPresenterUser = ((User) spinnerUser.getSelectedItem()).Id;
            conference.ConferenceDate = getConferenceDateTime();

        }catch (Exception ex){
            AlertUtil.showOkAlert(this, super.getString(R.string.ok), super.getString(R.string.invalid_input));
        }
    }
    private Date getConferenceDateTime(){
        Date conferenceDay = DateUtil.parseDate(String.valueOf(this.editTextConferenceDate.getText().toString()));
        String timeString = editTextConferenceTime.getText().toString();
        String[] timeVet = timeString.split(":");
        int hour = Integer.parseInt(timeVet[0]);
        int minute = Integer.parseInt(timeVet[1]);
        Calendar cal = Calendar.getInstance();
        cal.setTime(conferenceDay);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        return cal.getTime();
    }

}
