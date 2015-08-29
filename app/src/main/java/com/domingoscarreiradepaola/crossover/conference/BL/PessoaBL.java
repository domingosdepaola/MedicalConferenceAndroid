package com.domingoscarreiradepaola.crossover.conference.BL;

import com.domingoscarreiradepaola.crossover.conference.DAO.DatabaseHelper;
import com.domingoscarreiradepaola.crossover.conference.Interfaces.IPessoaDao;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.OrmLiteDao;

/**
 * Created by domin on 23/08/2015.
 */
@EBean
public class PessoaBL {

    @OrmLiteDao(helper = DatabaseHelper.class)
    public IPessoaDao pessoaDao;
}
