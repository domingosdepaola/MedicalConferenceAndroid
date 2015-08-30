package com.domingoscarreiradepaola.crossover.conference.UserInterface;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.domingoscarreiradepaola.crossover.conference.BL.ConferenceBL;
import com.domingoscarreiradepaola.crossover.conference.BL.InviteBL;
import com.domingoscarreiradepaola.crossover.conference.BL.LoginBL;
import com.domingoscarreiradepaola.crossover.conference.Common.AlertUtil;
import com.domingoscarreiradepaola.crossover.conference.Common.DateUtil;
import com.domingoscarreiradepaola.crossover.conference.Common.SharedPreferencesUtil;
import com.domingoscarreiradepaola.crossover.conference.Entity.Conference;
import com.domingoscarreiradepaola.crossover.conference.Entity.Invite;
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

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    public EditText editTextConferenceDuration;

    @ViewById
    public EditText editTextConferenceTime;

    @Bean
    public LoginBL loginBL;

    @Bean
    public InviteBL inviteBL;

    @Bean
    public ConferenceBL conferenceBL;

    private UserAdapter userAdapter;

    private boolean adminProfile;
    List<User> listUser;

    Integer conferenceIdSelected;

    @AfterViews
    public void init() {
        adjustForProfile();
        conferenceIdSelected = getConferenceId();
        if (conferenceIdSelected != null) {
            setForm();
        }
        fillUserSpinner();
    }
    private void fillUserSpinner(){
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

    private void setDate() {
        DialogFragment ClasseData = DatePickerFragment.newInstance(this.editTextConferenceDate);
        ClasseData.show(super.getSupportFragmentManager(), getString(R.string.datepicker));
    }

    private void setTime() {
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
    private void setDuration(){
        RelativeLayout linearLayout = new RelativeLayout(this);
        final NumberPicker aNumberPicker = new NumberPicker(this);
        aNumberPicker.setMaxValue(8);
        aNumberPicker.setMinValue(1);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50, 50);
        RelativeLayout.LayoutParams numPicerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        numPicerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        linearLayout.setLayoutParams(params);
        linearLayout.addView(aNumberPicker,numPicerParams);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Select the number");
        alertDialogBuilder.setView(linearLayout);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                editTextConferenceDuration.setText(String.valueOf(aNumberPicker.getValue()));
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
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
    @FocusChange(R.id.editTextConferenceDuration)
    public void onFocusDuration(View hello, boolean hasFocus) {
        if (hasFocus) {
            setDuration();
        }
    }
    @Click(R.id.editTextConferenceDuration)
    public void onClickDuration(){
        setDuration();
    }
    @FocusChange(R.id.editTextConferenceDate)
    public void onFocusConferenceDate(View hello, boolean hasFocus) {
        if (hasFocus) {
            setDate();
        }
    }

    @OptionsItem(R.id.menuList)
    public void goList() {
        ListConferenceActivity_.intent(this).start();
    }

    @OptionsItem(R.id.menuSave)
    public void save() {
        try {
            if (blankFields()) {
                AlertUtil.showOkAlert(this, super.getString(R.string.ok), super.getString(R.string.blank_fields));
                return;
            }
            Conference conference = getForm();
            this.conferenceBL.conferenceDao.createOrUpdate(conference);
            Toast.makeText(this, R.string.saved_sucess, Toast.LENGTH_LONG).show();
            ListConferenceActivity_.intent(this).start();
        } catch (Exception ex) {
            AlertUtil.showOkAlert(this, super.getString(R.string.ok), super.getString(R.string.default_error_msg));
        }
    }
    @OptionsItem(R.id.menuAcept)
    public void aceptConference(){
        Conference conference = getForm();
        Intent calIntent = new Intent(Intent.ACTION_INSERT);
        calIntent.setType("vnd.android.cursor.item/event");
        calIntent.putExtra(CalendarContract.Events.TITLE, conference.Title);
        calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, conference.Room);
        calIntent.putExtra(CalendarContract.Events.DESCRIPTION, conference.Description);

        GregorianCalendar calDate = new GregorianCalendar();
        GregorianCalendar calDateEnd = new GregorianCalendar();
        calDate.setTime(conference.ConferenceDate);
        calDateEnd.setTime(conference.getEndDate());
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false);
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,calDate.getTimeInMillis());
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,calDateEnd.getTimeInMillis());
        startActivity(calIntent);
        int idDoctor = loginBL.getLoggedUser().Id;
        Invite invite = inviteBL.inviteDao.getBy(idDoctor,conference.Id);
        if(invite != null) {
            invite.acepted = true;
            try {
                inviteBL.inviteDao.update(invite);
                Toast.makeText(this, R.string.invite_acept, Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @OptionsItem(R.id.menuReject)
    public void onReject(){
        int idDoctor = loginBL.getLoggedUser().Id;
        int idConference = 0;
        if (conferenceIdSelected != null) {
            idConference = conferenceIdSelected;
        }
        Invite invite = inviteBL.inviteDao.getBy(idDoctor,idConference);
        invite.rejected = true;
        try {
            inviteBL.inviteDao.update(invite);
            Toast.makeText(this, R.string.invite_reject, Toast.LENGTH_LONG).show();
            ListConferenceActivity_.intent(this).start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Integer getConferenceId() {
        Integer conferenceId = null;
        try {
            String conferenceSelected = SharedPreferencesUtil.get(this, this.getString(R.string.conference_id_selected));

            if (!conferenceSelected.equals("")) {
                conferenceId = Integer.parseInt(conferenceSelected);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conferenceId;
    }
    private void adjustForProfile() {
        User userLogged = loginBL.getLoggedUser();
        if (userLogged.UserProfile != null) {
            if (userLogged.UserProfile.IsAdm) {
                adminProfile = true;
            } else {
                adminProfile = false;
                editTextDescription.setEnabled(false);
                editTextTitle.setEnabled(false);
                editTextConferenceDate.setEnabled(false);
                editTextConferenceTime.setEnabled(false);
                editTextRoom.setEnabled(false);
                spinnerUser.setEnabled(false);
            }
        }
    }
    private Conference getForm() {
        Conference conference = new Conference();
        if (conferenceIdSelected != null) {
            conference.Id = conferenceIdSelected;
        }
        conference.Title = editTextTitle.getText().toString();
        conference.IdCreatedUser = this.loginBL.getLoggedUser().Id;
        conference.Description = editTextDescription.getText().toString();
        conference.IdPresenterUser = ((User) spinnerUser.getSelectedItem()).Id;
        conference.ConferenceDate = getConferenceDateTime();
        conference.Room = editTextRoom.getText().toString();
        conference.Duration = Integer.parseInt(editTextConferenceDuration.getText().toString());

        return conference;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (adminProfile) {
            menu.findItem(R.id.menuSave).setVisible(true);
            menu.findItem(R.id.menuAcept).setVisible(false);
            menu.findItem(R.id.menuReject).setVisible(false);
        } else {
            menu.findItem(R.id.menuSave).setVisible(false);
            menu.findItem(R.id.menuAcept).setVisible(true);
            menu.findItem(R.id.menuReject).setVisible(true);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    private void setForm() {
        try {
            if(conferenceIdSelected != null) {
                Conference conference = conferenceBL.conferenceDao.queryForId(conferenceIdSelected);
                if (conference != null) {
                    editTextTitle.setText(conference.Title);
                    if (conference.Room != null)
                        editTextRoom.setText(conference.Room);
                    String dateString = DateUtil.format(conference.ConferenceDate);
                    String[] dateVet = dateString.split(" ");
                    editTextConferenceDate.setText(dateVet[0]);
                    editTextConferenceTime.setText(dateVet[1]);
                    if (conference.Description != null)
                        editTextDescription.setText(conference.Description);
                    editTextConferenceDuration.setText(String.valueOf(conference.Duration));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean blankFields() {

        return (editTextConferenceDuration.getText().toString().equals("") || editTextConferenceDate.getText().toString().equals("") || editTextConferenceTime.getText().toString().equals("") || editTextRoom.getText().toString().equals("") || editTextTitle.getText().toString().equals(""));
    }

    private Date getConferenceDateTime() {
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
