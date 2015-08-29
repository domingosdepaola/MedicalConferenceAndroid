package com.domingoscarreiradepaola.crossover.conference.Entity;

import com.domingoscarreiradepaola.crossover.conference.DAO.PessoaDao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by domin on 23/08/2015.
 */
@DatabaseTable(daoClass = PessoaDao.class)
public class Pessoa {

    @DatabaseField(allowGeneratedIdInsert = true,generatedId = true)
    public int Id;
    @DatabaseField
    public String Nome;

    @Override
    public String toString(){
        return Nome;
    }
}
