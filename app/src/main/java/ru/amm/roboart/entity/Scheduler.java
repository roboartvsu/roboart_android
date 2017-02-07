package ru.amm.roboart.entity;

import com.google.gson.annotations.SerializedName;

import io.objectbox.BoxStore;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Generated;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Relation;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.exception.DbDetachedException;
import io.objectbox.relation.ToOne;
import io.objectbox.Box;
import io.objectbox.exception.DbException;

@Entity
public class Scheduler {

    @Id
    private long id;

    private String title;

    private String description;

    @Relation
    private Category category;

    @SerializedName("displayed_time")
    private String displayedTime;

    @SerializedName("start_time")
    private long startTime;

    @SerializedName("end_time")
    private long endTime;

    private String location;

    private long categoryId;

    /** Used to resolve relations */
    @Internal
    @Generated(hash = 1307364262)
    transient BoxStore __boxStore;

    @Internal
    @Generated(hash = 2133608096)
    private transient ToOne<Scheduler, Category> category__toOne;

    public Scheduler() {
    }

    @Generated(hash = 1478863993)
    public Scheduler(long id, String title, String description,
            String displayedTime, long startTime, long endTime, String location,
            long categoryId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.displayedTime = displayedTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.categoryId = categoryId;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public String toString() {
        return "Scheduler{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    /** See {@link io.objectbox.relation.ToOne} for details. */
    @Generated(hash = 1735742230)
    public synchronized ToOne<Scheduler, Category> getCategory__toOne() {
        if (category__toOne == null) {
            category__toOne = new ToOne<>(this, Scheduler_.categoryId,
                    Category.class);
        }
        return category__toOne;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 2011000533)
    public Category getCategory() {
        category = getCategory__toOne().getTarget(this.categoryId);
        return category;
    }

    /** Set the to-one relation including its ID property. */
    @Generated(hash = 1815047735)
    public void setCategory(Category category) {
        getCategory__toOne().setTarget(category);
        this.category = category;
    }

    /**
     * Removes entity from its object box. Entity must attached to an entity context.
     */
    @Generated(hash = 740568067)
    public void remove() {
        if (__boxStore == null) {
            throw new DbDetachedException();
        }
        __boxStore.boxFor(Scheduler.class).remove(this);
    }

    /**
     * Puts the entity in its object box.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1398187036)
    public void put() {
        if (__boxStore == null) {
            throw new DbDetachedException();
        }
        __boxStore.boxFor(Scheduler.class).put(this);
    }
}

