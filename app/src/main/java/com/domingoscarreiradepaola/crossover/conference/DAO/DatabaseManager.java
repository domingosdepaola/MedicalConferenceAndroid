package com.domingoscarreiradepaola.crossover.conference.DAO;

import android.content.Context;

/**
 * Created by domin on 23/08/2015.
 */
public class DatabaseManager {
    private static DatabaseManager instance;
    private DatabaseHelper helper;

    public static void init(Context ctx) {
        if (null == instance) {
            instance = new DatabaseManager(ctx);
        }
    }

    public static DatabaseManager getInstance() {
        return instance;
    }
    private DatabaseManager(Context ctx) {
        helper = new DatabaseHelper(ctx);
    }
    public DatabaseHelper getHelper() {
        return helper;
    }

}
