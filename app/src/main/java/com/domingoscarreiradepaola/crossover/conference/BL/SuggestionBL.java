package com.domingoscarreiradepaola.crossover.conference.BL;

import com.domingoscarreiradepaola.crossover.conference.DAO.DatabaseHelper;
import com.domingoscarreiradepaola.crossover.conference.Entity.Suggestion;
import com.domingoscarreiradepaola.crossover.conference.Entity.User;
import com.domingoscarreiradepaola.crossover.conference.Interfaces.ISuggestionDao;
import com.domingoscarreiradepaola.crossover.conference.Interfaces.IUserDao;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.OrmLiteDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by domin on 30/08/2015.
 */
@EBean
public class SuggestionBL {
    @OrmLiteDao(helper = DatabaseHelper.class)
    public ISuggestionDao suggestionDao;
    @OrmLiteDao(helper = DatabaseHelper.class)
    public IUserDao userDao;

    public List<Suggestion> getAllSugestions(){
        List<Suggestion> lst = null;
        try {
            lst = suggestionDao.queryForAll();
            for(Suggestion item : lst){
                this.refresh(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }
    private void refresh(Suggestion suggestion) throws SQLException {
        this.userDao.refresh(suggestion.User);
    }
}
