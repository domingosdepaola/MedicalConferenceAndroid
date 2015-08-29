package com.domingoscarreiradepaola.crossover.conference.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.domingoscarreiradepaola.crossover.conference.Entity.Conference;
import com.domingoscarreiradepaola.crossover.conference.Entity.User;
import com.domingoscarreiradepaola.crossover.conference.Entity.UserProfile;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by domin on 27/08/2015.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DataBaseName = "mybase.sqlite";
    private static final int DataBaseVersion = 1;
    public DatabaseHelper(Context context) {
        super(context, DataBaseName, null, DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource,UserProfile.class);
            TableUtils.createTable(connectionSource, Conference.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            List<String> allSql = new ArrayList<String>();
            switch (oldVersion) {
                case 1:
                    // allSql.add("alter table AdData add column `new_col` VARCHAR");
                    // allSql.add("alter table AdData add column `new_col2` VARCHAR");
            }
            for (String sql : allSql) {
                sqLiteDatabase.execSQL(sql);
            }
        } catch (Exception e) {
            Log.e(DatabaseHelper.class.getName(), "exception during onUpgrade",
                    e);
            throw new RuntimeException(e);
        }
    }
}
