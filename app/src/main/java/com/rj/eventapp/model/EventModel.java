package com.rj.eventapp.model;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class EventModel implements Serializable {

    private long eventId;
    private String userId;
    private String eventAgenda;
    private String eventDate;
    private String eventTime;
    private List<String> emailParticipants;

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }



    public String getEventAgenda() {
        return eventAgenda;
    }

    public void setEventAgenda(String eventAgenda) {
        this.eventAgenda = eventAgenda;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public List<String> getEmailParticipants() {
        return emailParticipants;
    }

    public void setEmailParticipants(List<String> emailParticipants) {
        this.emailParticipants = emailParticipants;
    }
}
