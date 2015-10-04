package com.springapp.mvc.model.entities;

import com.springapp.mvc.MessageStatus;
import com.springapp.mvc.exceptions.ValidationException;
import com.springapp.mvc.exceptions.ValidationMessages;
import com.sun.istack.internal.NotNull;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dmitry on 04.10.15.
 */

// TODO: Best way to implement it - via JPA/ORM framework. But according to specification i should use SpringJDBC
public class SmsHistory implements Serializable {

    private long id;
    private long telNumber;
    private Date dateSend;
    private int status;
    private String message;

    private String insertString =
            "INSERT INTO sms_history (TELNUMBER, DATESEND, STATUS, MESSAGE) VALUES (?, ?, ?, ?)";

    // Validation
    private static final String telNumberRegexp = "^79\\d{9}$";
    private static final int maxMessageLength = 1000;

    public SmsHistory(long telNumber, Date dateSend, int status, String message) {
        this.telNumber = telNumber;
        this.dateSend = dateSend;
        this.status = status;
        this.message = message;
    }

    public SmsHistory(long telNumber, Date dateSend, String message) {
        this(telNumber, dateSend, MessageStatus.SUCCESS.getValue(), message);
    }

    public void insert(@NotNull final JdbcTemplate template) throws DataAccessException {
        assert telNumber != 0;
        assert dateSend != null;
        assert message != null;

        template.update(insertString, telNumber, dateSend, status, message);
    }

    public void validate() throws ValidationException {
        validateTelNumber();
        validateStatus();
        validateMessage();
    }

    protected void validateTelNumber() throws ValidationException {
        if (!Long.toString(telNumber).matches(telNumberRegexp)) {
            throw new ValidationException(ValidationMessages.INCORRECT_TEL_NUMBER);
        }
    }

    protected void validateStatus() throws ValidationException {
        if (status != 0 && status != 1) {
            throw new ValidationException(ValidationMessages.INCORRECT_STATUS);
        }
    }

    protected void validateMessage() throws ValidationException {
        if (message == null || message.length() >= maxMessageLength) {
            throw new ValidationException(ValidationMessages.INCORRECT_MESSAGE);
        }
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(long telNumber) {
        this.telNumber = telNumber;
    }

    public Date getDateSend() {
        return dateSend;
    }

    public void setDateSend(Date dateSend) {
        this.dateSend = dateSend;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SmsHistory{" +
                "id=" + id +
                ", telNumber=" + telNumber +
                ", dateSend=" + dateSend +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
