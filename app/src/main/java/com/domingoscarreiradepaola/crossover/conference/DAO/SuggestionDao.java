package com.domingoscarreiradepaola.crossover.conference.DAO;

import com.domingoscarreiradepaola.crossover.conference.Entity.Suggestion;
import com.domingoscarreiradepaola.crossover.conference.Interfaces.ISuggestionDao;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

/**
 * Created by domin on 30/08/2015.
 */
public class SuggestionDao extends BaseDaoImpl<Suggestion,Integer> implements ISuggestionDao {
    public SuggestionDao(Class<Suggestion> dataClass) throws SQLException {
        super(dataClass);
    }

    public SuggestionDao(ConnectionSource connectionSource, Class<Suggestion> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public SuggestionDao(ConnectionSource connectionSource, DatabaseTableConfig<Suggestion> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
