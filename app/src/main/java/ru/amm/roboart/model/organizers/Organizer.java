package ru.amm.roboart.model.organizers;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "organizer")
public class Organizer {

    @DatabaseField(id = true)
    private long id;

    @DatabaseField
    private String title;

    @DatabaseField
    private String icon;

    @DatabaseField
    private String category;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getIcon() {
        return icon;
    }
}
