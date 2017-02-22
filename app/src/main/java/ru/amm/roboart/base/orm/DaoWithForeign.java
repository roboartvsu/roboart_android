package ru.amm.roboart.base.orm;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

abstract public class DaoWithForeign<T, ID> extends Dao<T, ID> {

    public DaoWithForeign(ConnectionSource connectionSource, Class<T> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    @Override
    public int create(T data) throws SQLException {
        int row = super.create(data);
        setForeigners(data);
        createOrUpdateForeigners(data);
        return row;
    }

    @Override
    public int update(T data) throws SQLException {
        int row = super.update(data);
        setForeigners(data);
        createOrUpdateForeigners(data);
        return row;
    }

    @Override
    public List<T> queryForAll() throws SQLException {
        List<T> result = super.queryForAll();
        queryForForeigners(result);
        return result;
    }

    @Override
    public T queryForFirst(PreparedQuery<T> preparedQuery) throws SQLException {
        T result = super.queryForFirst(preparedQuery);
        if (result != null) {
            queryForForeigners(result);
        }
        return result;
    }

    @Override
    public T queryForId(ID id) throws SQLException {
        T result = super.queryForId(id);
        queryForForeigners(result);
        return result;
    }

    @Override
    public List<T> query(PreparedQuery<T> preparedQuery) throws SQLException {
        final List<T> result = super.query(preparedQuery);
        queryForForeigners(result);
        return result;
    }

    abstract protected void createOrUpdateForeigners(T data) throws SQLException;

    abstract protected void setForeigners(T data) throws SQLException;

    abstract protected void queryForForeigners(T data) throws SQLException;

    private void queryForForeigners(List<T> data) throws SQLException {
        for (T t : data) {
            queryForForeigners(t);
        }
    }
}