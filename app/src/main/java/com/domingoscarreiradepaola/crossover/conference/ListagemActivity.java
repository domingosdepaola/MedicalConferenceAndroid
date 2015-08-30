package com.domingoscarreiradepaola.crossover.conference;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.domingoscarreiradepaola.crossover.conference.BL.PessoaBL;
import com.domingoscarreiradepaola.crossover.conference.Entity.Pessoa;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_listagem)
public class ListagemActivity extends ActionBarActivity {

    @Bean
    public PessoaBL pessoaBL;

    @ViewById(R.id.view1)
    ListView lstView;

    @AfterViews
    public void init(){
        List<Pessoa> lstPessoas = pessoaBL.pessoaDao.findAllPeople();
        ListView lstView = (ListView) findViewById(R.id.view1);
        lstPessoas = pessoaBL.pessoaDao.findAllPeople();
        ArrayAdapter<Pessoa> adapter = new ArrayAdapter<Pessoa>(this, android.R.layout.simple_list_item_1, lstPessoas);
        lstView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listagem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id == R.id.menuTelaInicial){
            Intent intent = new Intent(ListagemActivity.this,MainActivity.class);
            //intent.putExtra("key", value); //Optional parameters
            this.startActivity(intent);
        }


        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}
