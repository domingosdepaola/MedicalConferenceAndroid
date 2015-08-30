package com.domingoscarreiradepaola.crossover.conference.DAO;


import com.domingoscarreiradepaola.crossover.conference.Entity.Conference;
import com.domingoscarreiradepaola.crossover.conference.Interfaces.IConferenceDao;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by domin on 29/08/2015.
 */
public class ConferenceDao extends BaseDaoImpl<Conference,Integer> implements IConferenceDao {
    public ConferenceDao(Class<Conference> dataClass) throws SQLException {
        super(dataClass);
    }

    public ConferenceDao(ConnectionSource connectionSource, Class<Conference> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public ConferenceDao(ConnectionSource connectionSource, DatabaseTableConfig<Conference> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
