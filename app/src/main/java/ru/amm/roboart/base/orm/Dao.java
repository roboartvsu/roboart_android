package ru.amm.roboart.base.orm;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;
import java.util.concurrent.Callable;

abstract public class Dao<T, ID> extends BaseDaoImpl<T, ID> {

    public Dao(ConnectionSource connectionSource, Class<T> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public int createOrUpdate(List<T> data) throws SQLException {
        int rows = 0;
        if (data == null || data.isEmpty()) {
            return rows;
        }

        DatabaseConnection databaseConnection = startThreadConnection();
        Savepoint savePoint = null;
        try {
            savePoint = databaseConnection.setSavePoint(null);
            for (int i = 0; i < data.size(); i++) {
                CreateOrUpdateStatus status = createOrUpdate(data.get(i));
                rows += status.getNumLinesChanged();
            }
        } finally {
            databaseConnection.commit(savePoint);
            endThreadConnection(databaseConnection);
        }
        return rows;
    }

    public int create(List<T> data) throws SQLException {
        int rows = 0;
        if (data == null || data.isEmpty()) {
            return rows;
        }

        DatabaseConnection databaseConnection = startThreadConnection();
        Savepoint savePoint = null;
        try {
            savePoint = databaseConnection.setSavePoint(null);
            for (int i = 0; i < data.size(); i++) {
                rows += create(data.get(i));
            }
        } finally {
            databaseConnection.commit(savePoint);
            endThreadConnection(databaseConnection);
        }
        return rows;
    }

    public void createIfNotExists(List<T> data) throws SQLException {
        if (data == null || data.isEmpty()) {
            return;
        }

        DatabaseConnection databaseConnection = startThreadConnection();
        Savepoint savePoint = null;
        try {
            savePoint = databaseConnection.setSavePoint(null);
            for (int i = 0; i < data.size(); i++) {
                createIfNotExists(data.get(i));
            }
        } finally {
            databaseConnection.commit(savePoint);
            endThreadConnection(databaseConnection);
        }
    }

    public int update(List<T> data) throws SQLException {
        int rows = 0;
        if (data == null || data.size() <= 0) {
            return rows;
        }

        DatabaseConnection databaseConnection = startThreadConnection();
        Savepoint savePoint = null;
        try {
            savePoint = databaseConnection.setSavePoint(null);
            for (int i = 0; i < data.size(); i++) {
                rows += update(data.get(i));
            }
        } finally {
            databaseConnection.commit(savePoint);
            endThreadConnection(databaseConnection);
        }
        return rows;
    }

    public int delete(List<T> data) throws SQLException {
        if (data == null || data.size() <= 0) {
            return 0;
        }
        return callBatchTasks(new RemoveDataBatch(data));
    }

    private class RemoveDataBatch implements Callable<Integer> {

        private List<T> data;

        public RemoveDataBatch(List<T> data) {
            this.data = data;
        }

        @Override
        public Integer call() throws Exception {
            int rows = 0;
            for (int i = 0; i < data.size(); i++) {
                rows += delete(data.get(i));
            }
            return rows;
        }
    }
}