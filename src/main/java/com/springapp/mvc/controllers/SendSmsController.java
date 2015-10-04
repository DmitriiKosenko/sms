package com.springapp.mvc.controllers;

import com.springapp.mvc.DataSource;
import com.springapp.mvc.MessageStatus;
import com.springapp.mvc.SmsSender;
import com.springapp.mvc.exceptions.ValidationException;
import com.springapp.mvc.model.entities.SmsHistory;
import com.sun.istack.internal.NotNull;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by dmitry on 04.10.15.
 */
@RestController
public class SendSmsController {

    private final static String CANT_SEND_MESSAGE = "Невозможно отправить сообщение. ";
    private final static String INTERNAL_ERROR = "Произошла внутренняя ошибка. ";
    private final static String SUCCESS = "Сообщение успешно отправлено! ";


    @RequestMapping("/send_sms")
    public @NotNull Object[] sendSms(
            @RequestParam(value = "tel_number") long telNumber,
            @RequestParam(value = "message") String message
    ) {

        Object[] errorObject = new Object[] {CANT_SEND_MESSAGE + INTERNAL_ERROR};

        try {
            boolean isSent = new SmsSender().sendSms(Long.toString(telNumber), message);

            SmsHistory smsHistory =
                    new SmsHistory(telNumber, new Date(), MessageStatus.getByBoolean(isSent).getValue(), message);

            smsHistory.validate();
            smsHistory.insert(DataSource.getJDBCTemplate());

            if (!isSent) {
                return errorObject;
            }

        } catch (ValidationException e) {
            return new Object[] {CANT_SEND_MESSAGE + e.getMessage()};
        } catch (SQLException e) {
            return errorObject;
        } catch (DataAccessException e) {
            return errorObject;
        }

        return new Object[] {SUCCESS};
    }
}
