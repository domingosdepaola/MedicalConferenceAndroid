package com.domingoscarreiradepaola.crossover.conference.UserInterface.Dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.DatePicker;
import android.widget.EditText;

import com.domingoscarreiradepaola.crossover.conference.Common.DateUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by domin on 28/08/2015.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
{
    private EditText editTextDataNascimento;
    private static int ano;
    private static int mes;
    private static int dia;

    public static DatePickerFragment newInstance(EditText editTextDataNascimento) {

        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.editTextDataNascimento = editTextDataNascimento;
        return datePickerFragment;
    }

    public DatePickerFragment() {


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        this.ano = year;
        this.mes = month;
        this.dia = day;
        String digitoZeroDia = "";
        String digitoZeroMes = "";

        this.mes += 1;
        if(this.dia <= 9)
            digitoZeroDia = "0";

        if(this.mes <= 9)
            digitoZeroMes = "0";
        this.editTextDataNascimento.setText(digitoZeroDia + this.dia +"/"+ digitoZeroMes+ this.mes +"/"+ this.ano);
    }

    @Override
    public int show(FragmentTransaction transaction, String tag)
    {
        return super.show(transaction, tag);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

        final Calendar calendario = Calendar.getInstance();
        if(editTextDataNascimento.getText().toString().equals(""))
            calendario.setTime(new Date());
        else
           calendario.setTime(DateUtil.parseDate(editTextDataNascimento.getText().toString()));

        this.ano = calendario.get(Calendar.YEAR);
        this.mes = calendario.get(Calendar.MONTH);
        this.dia = calendario.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, this.ano, this.mes, this.dia);
    }

}
