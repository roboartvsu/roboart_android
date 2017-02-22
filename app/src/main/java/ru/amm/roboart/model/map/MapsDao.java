package ru.amm.roboart.model.map;

import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import ru.amm.roboart.base.orm.Dao;

public class MapsDao  extends Dao<Maps,Long>{

    public MapsDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Maps.class);
    }
}
