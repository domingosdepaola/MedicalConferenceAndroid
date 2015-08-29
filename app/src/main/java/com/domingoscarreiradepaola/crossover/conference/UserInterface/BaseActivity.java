package com.domingoscarreiradepaola.crossover.conference.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;


import com.domingoscarreiradepaola.crossover.conference.BL.LoginBL;
import com.domingoscarreiradepaola.crossover.conference.Common.ProgressDialogUtil;
import com.domingoscarreiradepaola.crossover.conference.Common.SharedPreferencesUtil;
import com.domingoscarreiradepaola.crossover.conference.Common.StringUtil;
import com.domingoscarreiradepaola.crossover.conference.R;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;

/**
 * Created by domin on 28/08/2015.
 */
@EActivity
public class BaseActivity extends ActionBarActivity {
    private boolean enableBackButton = true;
    private boolean wasStoppedOrDestroyed = false;
    private boolean wasRestarted = false;

    @Bean
    public ProgressDialogUtil progressDialogUtil;
    @Bean(LoginBL.class)
    public LoginBL loginBL;

    public void showProgress(String label) {

        this.progressDialogUtil.showProgress(label);
    }

    public void afterInject() {

        this.verifyDisableUser();
    }

    @OptionsItem(android.R.id.home)
    public void onBackClicked() {

        super.onBackPressed();
    }


    protected void configureActionBar() {
       // Toolbar toolbar = (Toolbar)super.findViewById(R.id.toolbar);
        //super.setSupportActionBar(toolbar);

        //if (this.enableBackButton)
            //super.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void showProgress() {

        this.showProgress(super.getString(R.string.loading));
    }

    @Override
    public void onCreate(Bundle savedState) {

        super.onCreate(savedState);

        this.verifyDisableUser();
    }

    private void verifyDisableUser() {

        try {

            String disabledUser = SharedPreferencesUtil.get(this, super.getString(R.string.disableduser));

            if (!StringUtil.isNullOrEmpty(disabledUser) && disabledUser.equals("I")) {

                Toast.makeText(this, super.getString(R.string.disabled_user_message), Toast.LENGTH_LONG).show();
                SharedPreferencesUtil.save(this, super.getString(R.string.disableduser), "");
                super.finish();
                LoginActivity_.intent(this).start();
            }
        }
        catch (Exception e) {


        }
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        this.wasStoppedOrDestroyed = true;
    }

    @Override
    protected void onStop() {

        super.onStop();
        this.wasStoppedOrDestroyed = true;
    }

    @Override
    protected void onRestart() {

        super.onRestart();
        this.setWasRestarted(true);
    }

    public void removeProgress() {

        this.progressDialogUtil.removeProgress();
    }

    public boolean isEnableBackButton() {
        return enableBackButton;
    }

    public void setEnableBackButton(boolean enableBackButton) {
        this.enableBackButton = enableBackButton;
    }

    public boolean isWasStoppedOrDestroyed() {
        return wasStoppedOrDestroyed;
    }

    public void setWasStoppedOrDestroyed(boolean wasFinishedOrDestroyed) {
        this.wasStoppedOrDestroyed = wasFinishedOrDestroyed;
    }

    public boolean isWasRestarted() {
        return wasRestarted;
    }

    public void setWasRestarted(boolean wasRestarted) {
        this.wasRestarted = wasRestarted;
    }
}
