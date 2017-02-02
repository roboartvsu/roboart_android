package ru.amm.roboart.entity;


import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Generated;

@Entity
public class Maps {

    @Id
    private long id;

    private String title;

    private String image;

    @Generated(hash = 1002995087)
    public Maps(long id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    @Generated(hash = 830892839)
    public Maps() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Maps{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
