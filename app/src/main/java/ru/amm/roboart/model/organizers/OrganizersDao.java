package ru.amm.roboart.model.organizers;


import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import ru.amm.roboart.base.orm.Dao;

public class OrganizersDao extends Dao<Organizer, Long> {

    public OrganizersDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Organizer.class);
    }
}
