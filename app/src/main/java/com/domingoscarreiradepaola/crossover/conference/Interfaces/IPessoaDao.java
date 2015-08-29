package com.domingoscarreiradepaola.crossover.conference.Interfaces;

import com.domingoscarreiradepaola.crossover.conference.Entity.Pessoa;
import com.j256.ormlite.dao.Dao;

import java.util.List;

/**
 * Created by domin on 23/08/2015.
 */
public interface IPessoaDao extends Dao<Pessoa,Integer> {
    List<Pessoa> findAllPeople();
    boolean savePeople(Pessoa pessoa);
}
