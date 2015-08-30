package com.domingoscarreiradepaola.crossover.conference.DAO;

import com.domingoscarreiradepaola.crossover.conference.Entity.User;
import com.domingoscarreiradepaola.crossover.conference.Interfaces.IUserDao;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by domin on 28/08/2015.
 */
public class UserDao extends BaseDaoImpl<User,Integer> implements IUserDao {
    public UserDao(Class<User> dataClass) throws SQLException {
        super(dataClass);
    }

    public UserDao(ConnectionSource connectionSource, Class<User> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public UserDao(ConnectionSource connectionSource, DatabaseTableConfig<User> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
    @Override
    public List<User> getUser(int id){
        List<User> list = null;
        try {
            QueryBuilder<User,Integer> queryBuilder = this.queryBuilder();
            queryBuilder.where().eq(User.ID_FIELD_NAME,id);
            list = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public boolean saveUser(User user) {
        try {
            this.createOrUpdate(user);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    @Override
    public List<User> getByUserNameAndPassword(String userName, String password) throws SQLException {

        QueryBuilder<User, Integer> queryBuilder = this.queryBuilder();
        queryBuilder.where().eq(User.LOGIN_FIELD_NAME, userName).and().eq(User.PASSWORD_FIELD_NAME, password).and().eq(User.STATUS_FIELD_NAME, "A");
        return this.query(queryBuilder.prepare());
    }
    @Override
    public User getUserByLogin(String login) {
        User user = null;
        try {
            QueryBuilder<User,Integer> queryBuilder = this.queryBuilder();
            queryBuilder.where().eq(User.LOGIN_FIELD_NAME,login);
            List<User> list = queryBuilder.query();
            if(list != null && list.size() > 0){
                for(User item : list){
                    user = item;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public List<User> getByProfile(int idProfile){
        User user = null;
        List<User> list = null;
        try {
            QueryBuilder<User,Integer> queryBuilder = this.queryBuilder();
            queryBuilder.where().eq(User.ID_PROFILE_FIELD_NAME,idProfile);
            list = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
