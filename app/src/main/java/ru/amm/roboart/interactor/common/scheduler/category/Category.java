package ru.amm.roboart.interactor.common.scheduler.category;

import com.google.gson.annotations.SerializedName;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Generated;
import io.objectbox.annotation.Id;

@Entity
public class Category {
    @Id
    private long id;

    private String title;
    
    @SerializedName("hex_color")
    private String hexColor;

    @Generated(hash = 783910190)
    public Category(long id, String title, String hexColor) {
        this.id = id;
        this.title = title;
        this.hexColor = hexColor;
    }

    @Generated(hash = 1150634039)
    public Category() {
    }

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
}
