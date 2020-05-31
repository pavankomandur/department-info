package com.tential.departmentinfo.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
public class Department {

   /*
     * This will be assign auto-incremented ids
     * sequentially.
     */
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** timeCreated is when domain objects are created and it is not updatable. */
    @Column(updatable = false)
    private Date timeCreated;

    /**
     * timeUpdated is the same as timeCreated if the entity object is created first. And it will be
     * updated everytime there is an update for the object.
     */
    private Date timeUpdated;


    private String department_name;


    private int department_count;

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public int getDepartment_count() {
        return department_count;
    }

    public void setDepartment_count(int department_count) {
        this.department_count = department_count;
    }

    /**
     * The below method will make sure , it will update current date and time when u create a new object
     */
    @PrePersist
    void onCreate() {
        Date now = Date.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant());
        setTimeCreated(now);
        setTimeUpdated(now);
    }

    /**
     * The below method will make sure , it will update current date and time when u update an existing object
     */

    @PreUpdate
    void onUpdate() {
        Date now = Date.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant());
        setTimeUpdated(now);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Date getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(Date timeUpdated) {
        this.timeUpdated = timeUpdated;
    }
}


