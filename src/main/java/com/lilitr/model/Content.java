package com.lilitr.model;

import javax.persistence.*;

@Entity
@Table(name = "content")
public class Content {
    @Id
    @GeneratedValue
    private int contetnId;

    @ManyToOne
    private Event eventId;

    @ManyToOne
    private User userId;

    @Column
    private String text;

    public User getUserId() {
        return userId;
    }

    public Event getEventId() {
        return eventId;
    }

    public String getText() {
        return text;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public void setEventId(Event eventId) {
        this.eventId = eventId;
    }

    public void setText(String text) {
        this.text = text;
    }

}
