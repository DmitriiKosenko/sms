package com.springapp.mvc.model.entities;

import com.springapp.mvc.MessageStatus;

import java.util.Date;

/**
 * Created by dmitry on 06.10.15.
 */
public class SmsHistoryDTO {

    private long id;
    private String telNumber;
    private Date dateSend;
    private String status;
    private String message;

    public SmsHistoryDTO(SmsHistory smsHistory) {
        id = smsHistory.getId();
        telNumber = String.valueOf(smsHistory.getTelNumber());
        dateSend = smsHistory.getDateSend();
        status = MessageStatus.getByInteger(smsHistory.getStatus()).getMessage();
        message = smsHistory.getMessage();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public Date getDateSend() {
        return dateSend;
    }

    public void setDateSend(Date dateSend) {
        this.dateSend = dateSend;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
