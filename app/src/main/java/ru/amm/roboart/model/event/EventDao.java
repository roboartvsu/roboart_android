package ru.amm.roboart.model.event;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import ru.amm.roboart.base.orm.DaoWithForeign;

public class EventDao extends DaoWithForeign<Event, Long> {

    private CategoryDao categoryDao;

    public EventDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Event.class);
        this.categoryDao = DaoManager.createDao(connectionSource, Category.class);
    }

    @Override
    public int create(Event data) throws SQLException {
        data.setCategoryId(data.getCategory().getId());
        return super.create(data);
    }

    @Override
    protected void createOrUpdateForeigners(Event data) throws SQLException {
        if (data != null && data.getCategory() != null) {
            categoryDao.createOrUpdate(data.getCategory());
        }
    }

    @Override
    protected void setForeigners(Event data) throws SQLException {
    }

    @Override
    protected void queryForForeigners(Event data) throws SQLException {
        data.setCategory(categoryDao.queryForId(data.getCategoryId()));
    }
}
