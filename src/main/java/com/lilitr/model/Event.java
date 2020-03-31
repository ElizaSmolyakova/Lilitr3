package com.lilitr.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue
    @Column(name="ID")
    private int eventId;

    @Column(name="NAME")
    private String eventName;

    @Column(name="DATE")
    private Date eventDate;

    @NotNull
    @OneToOne
    private User eventOwner;

    @ManyToMany
    @JoinTable(name="PARTICIPANTS")
    private List<User> participants;

    public Date getEventDate() {
        return eventDate;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public User getEventOwner() {
        return eventOwner;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventOwner(User eventOwner) {
        this.eventOwner = eventOwner;
    }

    private Event() {
    }

    public Event (User byUser){
        Event event = new Event();
        event.setEventOwner(byUser);
    }

}
