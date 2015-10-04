package com.springapp.mvc;

import com.springapp.mvc.exceptions.ValidationException;
import com.springapp.mvc.exceptions.ValidationMessages;
import net.java.quickcheck.generator.PrimitiveGenerators;

import com.springapp.mvc.model.entities.SmsHistory;
import junit.framework.TestCase;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by dmitry on 04.10.15.
 */

public class SmsHistoryTests extends TestCase {

    private SimpleDriverDataSource dataSource;
    private JdbcTemplate template;

    private static final String createTempTableSql = "CREATE TABLE sms_history_temp (\n" +
            "ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
            "TELNUMBER BIGINT NOT NULL,\n" +
            "DATESEND DATE NOT NULL,\n" +
            "STATUS TINYINT NOT NULL,\n" +
            "MESSAGE VARCHAR(1000)\n" +
            ");";
    private static final String dropTempTableSql = "DROP TABLE IF EXISTS sms_history_temp";
    private static final String selectFromTempTable = "SELECT COUNT(*) total FROM sms_history_temp";
    private static final String insertString = "INSERT INTO sms_history_temp (TELNUMBER, DATESEND, STATUS, MESSAGE) VALUES (?, ?, ?, ?)";

    private static final int countRows = 10;

    public void setUp() throws Exception {
        dataSource = DataSource.getInstance();

        Connection connection = dataSource.getConnection();
        assert connection != null;

        template = new JdbcTemplate(dataSource);

        template.execute(dropTempTableSql);
        template.execute(createTempTableSql);
    }

    public void testInsert_create10Rows_Insert10Rows() throws Exception {
        for (int i = 0; i < countRows; i++) {

            SmsHistory smsHistory = create();
            setTempTableToSmsHistoryObject(smsHistory);
            smsHistory.insert(template);
        }

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

    public void tearDown() {
        template.execute(dropTempTableSql);
    }


    //
    private void setTempTableToSmsHistoryObject(SmsHistory smsHistory) throws Exception {
        Class smsHistoryClass = SmsHistory.class;

        Field field = smsHistoryClass.getDeclaredField("insertString");
        field.setAccessible(true);
        field.set(smsHistory, insertString);
    }

    private SmsHistory create() {
        long telNumber = randomTelNumber();
        Date dateSend = new Date();
        int status = randomStatus();
        String message = randomMessage();

        return new SmsHistory(telNumber, dateSend, status, message);
    }

    private long randomTelNumber() {
        return PrimitiveGenerators.longs(79000000000l, 79999999999l).next();
    }

    private int randomStatus() {
        return PrimitiveGenerators.integers(0, 1).next();
    }

    private String randomMessage() {
        return PrimitiveGenerators.strings(1000).next();
    }

}
