package ru.amm.roboart.model.event;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "events", daoClass = EventDao.class)
public class Event {

    public static final String DB_FIELD_ID = "id";

    @Expose
    @DatabaseField(id = true, columnName = DB_FIELD_ID)
    private long id;

    @Expose
    @DatabaseField
    private String title;

    @Expose
    @DatabaseField
    private String description;

    @Expose
    private Category category;

    @DatabaseField
    private long categoryId;

    @Expose
    @DatabaseField
    private String displayedTime;

    @Expose
    @DatabaseField
    private long startTime;

    @Expose
    @DatabaseField
    private long endTime;

    @Expose
    @DatabaseField
    private String location;

    @Expose
    @DatabaseField
    private String map;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayedTime() {
        return displayedTime;
    }

    public void setDisplayedTime(String displayedTime) {
        this.displayedTime = displayedTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getMap() {
        return map;
    }
}