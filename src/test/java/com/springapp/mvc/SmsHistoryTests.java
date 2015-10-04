package com.springapp.mvc;

import com.springapp.mvc.exceptions.ValidationException;
import com.springapp.mvc.exceptions.ValidationMessages;

import com.springapp.mvc.model.entities.SmsHistory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dmitry on 04.10.15.
 */

public class SmsHistoryTests extends SmsHistoryTestCase {

    private static final String selectFromTempTable = "SELECT COUNT(*) total FROM sms_history_temp";

    private static final int countRows = 10;

    public void testInsert_create10Rows_Insert10Rows() throws Exception {
        insertRecords(countRows);

        List<Integer> result = template.query(selectFromTempTable, new Object[]{}, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("total");
            }
        });

        assertTrue("Вставлено 10 записей", result.get(0) == countRows);
    }

    public void testInsert_createIncorrectRow_NoInsertions() throws Exception {
        try {
            SmsHistory smsHistory = create();
            setTempTableToSmsHistoryObject(smsHistory);
            smsHistory.setDateSend(null);
            smsHistory.setMessage(null);

            smsHistory.insert(template);

            throw new Exception();
        } catch (DataIntegrityViolationException e) {
            assertTrue("Не удалось вставить в таблицу не корректное значение", true);
        }

    }

    public void testValidate_IncorrectTelNumber_IncorrectTelNumberMessage() throws Exception {
        try {
            SmsHistory smsHistory = create();
            smsHistory.setTelNumber(1l);

            smsHistory.validate();

            throw new Exception();
        } catch (ValidationException e) {
            assertTrue(e.getMessage().equals(ValidationMessages.INCORRECT_TEL_NUMBER));
        }

        try {
            SmsHistory smsHistory = create();
            smsHistory.setTelNumber(88888888888l);

            smsHistory.validate();

            throw new Exception();
        } catch (ValidationException e) {
            assertTrue(e.getMessage().equals(ValidationMessages.INCORRECT_TEL_NUMBER));
        }

        try {
            SmsHistory smsHistory = create();
            smsHistory.setTelNumber(7123123123122l);

            smsHistory.validate();

            throw new Exception();
        } catch (ValidationException e) {
            assertTrue(e.getMessage().equals(ValidationMessages.INCORRECT_TEL_NUMBER));
        }
    }

    public void testValidate_IncorrectStatus_IncorrectStatusMessage() throws Exception {
        try {
            SmsHistory smsHistory = create();
            smsHistory.setStatus(2);

            smsHistory.validate();

            throw new Exception();
        } catch (ValidationException e) {
            assertTrue(e.getMessage().equals(ValidationMessages.INCORRECT_STATUS));
        }
    }

    public void testValidate_IncorrectMessage_IncorrectMessageMessage() throws Exception {
        try {
            SmsHistory smsHistory = create();
            smsHistory.setMessage(null);

            smsHistory.validate();

            throw new Exception();
        } catch (ValidationException e) {
            assertTrue(e.getMessage().equals(ValidationMessages.INCORRECT_MESSAGE));
        }

        try {
            SmsHistory smsHistory = create();

            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < 10000; i++) {
                stringBuffer.append("1234567890");
            }
            smsHistory.setMessage(stringBuffer.toString());

            smsHistory.validate();

            throw new Exception();
        } catch (ValidationException e) {
            assertTrue(e.getMessage().equals(ValidationMessages.INCORRECT_MESSAGE));
        }
    }

}
