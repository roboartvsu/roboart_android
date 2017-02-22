package ru.amm.roboart.model.event;


import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import ru.amm.roboart.base.orm.Dao;

public class CategoryDao extends Dao<Category, Long> {

    public CategoryDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Category.class);
    }

    public Category queryForEventId(long id) throws SQLException {
        QueryBuilder<Category, Long> queryBuilder = queryBuilder();
        queryBuilder.where().eq(Category.DB_FIELD_ID, id);
        PreparedQuery<Category> preparedQuery = queryBuilder.prepare();
        return queryForFirst(preparedQuery);
    }
}
