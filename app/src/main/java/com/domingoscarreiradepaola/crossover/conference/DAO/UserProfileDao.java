package com.domingoscarreiradepaola.crossover.conference.DAO;

import com.domingoscarreiradepaola.crossover.conference.Entity.UserProfile;
import com.domingoscarreiradepaola.crossover.conference.Interfaces.IUserProfileDao;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by domin on 29/08/2015.
 */
public class UserProfileDao extends BaseDaoImpl<UserProfile,Integer> implements IUserProfileDao {
    public UserProfileDao(Class<UserProfile> dataClass) throws SQLException {
        super(dataClass);
    }

    public UserProfileDao(ConnectionSource connectionSource, Class<UserProfile> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public UserProfileDao(ConnectionSource connectionSource, DatabaseTableConfig<UserProfile> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
    public UserProfile getProfileByType(boolean isAdm){
        UserProfile profile = null;
        try {
            QueryBuilder<UserProfile,Integer> queryBuilder = this.queryBuilder();
            queryBuilder.where().eq(UserProfile.IS_ADM_FIELD_NAME,isAdm);
            List<UserProfile> list = queryBuilder.query();
            if(list != null && list.size() > 0){
                for(UserProfile item : list){
                    profile = item;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profile;
    }
}
