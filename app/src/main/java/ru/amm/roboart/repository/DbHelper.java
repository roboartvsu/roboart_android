package ru.amm.roboart.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import ru.amm.roboart.model.event.Category;
import ru.amm.roboart.model.event.Event;
import ru.amm.roboart.model.map.Maps;

public class DbHelper extends OrmLiteSqliteOpenHelper {

    private static final String DB_NAME = "roboart_db";
    private Class[] dbClasses = new Class[]{
            Event.class,
            Category.class,
            Maps.class};

    private static final int DB_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            for (Class clazz : dbClasses) {
                TableUtils.createTable(connectionSource, clazz);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        dropTables(connectionSource);
        onCreate(database, connectionSource);
    }

    private void dropTables(ConnectionSource connectionSource) {
        try {
            for (Class clazz : dbClasses) {
                dropTable(connectionSource, clazz);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void dropTable(ConnectionSource connectionSource, Class dbClass) throws SQLException {
        TableUtils.dropTable(connectionSource, dbClass, true);
    }
}
