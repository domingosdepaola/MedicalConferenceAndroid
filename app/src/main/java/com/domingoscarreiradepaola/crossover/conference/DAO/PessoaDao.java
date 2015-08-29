package com.domingoscarreiradepaola.crossover.conference.DAO;

import com.domingoscarreiradepaola.crossover.conference.Entity.Pessoa;
import com.domingoscarreiradepaola.crossover.conference.Interfaces.IPessoaDao;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by domin on 23/08/2015.
 */
public class PessoaDao extends BaseDaoImpl<Pessoa,Integer> implements IPessoaDao {

    public PessoaDao(ConnectionSource connectionSource, DatabaseTableConfig<Pessoa> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    public PessoaDao(Class<Pessoa> dataClass) throws SQLException {
        super(dataClass);
    }

    public PessoaDao(ConnectionSource connectionSource, Class<Pessoa> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Pessoa> findAllPeople() {
        List<Pessoa> list = null;
        try {
            list = this.queryForAll();
        }catch (Exception ex){
            ex.printStackTrace();

        }
        return list;
    }
    public List<Pessoa> getPeople(int id){
        List<Pessoa> list = null;
        try {
            QueryBuilder<Pessoa,Integer> queryBuilder = this.queryBuilder();
            queryBuilder.where().eq("Id",id);
            queryBuilder.orderBy("Id",false);
            list = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean savePeople(Pessoa pessoa) {
        try {
            this.createOrUpdate(pessoa);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
