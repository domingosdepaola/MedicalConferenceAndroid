package com.domingoscarreiradepaola.crossover.conference.Interfaces;

import com.domingoscarreiradepaola.crossover.conference.Entity.User;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by domin on 28/08/2015.
 */
public interface IUserDao extends Dao<User,Integer> {
    List<User> getUser(int id);
    boolean saveUser(User user);
    List<User> getByUserNameAndPassword(String userName, String password) throws SQLException;
    User getUserByLogin(String login);
    List<User> getByProfile(int idProfile);
}
