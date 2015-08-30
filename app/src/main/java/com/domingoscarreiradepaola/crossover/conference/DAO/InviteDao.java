package com.domingoscarreiradepaola.crossover.conference.DAO;

import com.domingoscarreiradepaola.crossover.conference.Entity.Invite;
import com.domingoscarreiradepaola.crossover.conference.Interfaces.IInviteDao;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by domin on 30/08/2015.
 */
public class InviteDao extends BaseDaoImpl<Invite, Integer> implements IInviteDao {
    public InviteDao(Class<Invite> dataClass) throws SQLException {
        super(dataClass);
    }

    public InviteDao(ConnectionSource connectionSource, Class<Invite> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public InviteDao(ConnectionSource connectionSource, DatabaseTableConfig<Invite> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    public List<Invite> getInviteBy(Invite invite) {
        List<Invite> list = null;
        try {
            QueryBuilder<Invite, Integer> queryBuilder = this.queryBuilder();

            queryBuilder.where().eq(Invite.CONFERENCE_FIELD, invite.IdConference).and().eq(Invite.USER_CREATED_FIELD, invite.IdUserCreated).and().eq(Invite.USER_INVITED_FIELD, invite.IdUserInvited);
            list = queryBuilder.query();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public Invite getBy(int idDoctor,int idConference){
        Invite invite = new Invite();
        try {
            QueryBuilder<Invite, Integer> queryBuilder = this.queryBuilder();

            queryBuilder.where().eq(Invite.CONFERENCE_FIELD, idConference).and().eq(Invite.USER_INVITED_FIELD, idDoctor);
            List<Invite> list = queryBuilder.query();
            for(Invite item : list){
                invite = item;
                break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return invite;
    }
    public List<Invite> getByIdDoctor(int idDoctor){
        List<Invite> list = null;
        try {
            QueryBuilder<Invite, Integer> queryBuilder = this.queryBuilder();

            queryBuilder.where().eq(Invite.USER_INVITED_FIELD, idDoctor);
            list = queryBuilder.query();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
