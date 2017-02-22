package ru.amm.roboart.model.event;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "category", daoClass = CategoryDao.class)
public class Category {

    public static final String DB_FIELD_EVENT_ID = "event_id";
    public static final String DB_FIELD_ID = "id";

    @DatabaseField(id = true, columnName = DB_FIELD_ID)
    private long id;

    @Expose
    @DatabaseField
    private String title;

    @Expose
    @DatabaseField
    private String hexColor;

    @DatabaseField(columnName = DB_FIELD_EVENT_ID)
    private Long eventId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
