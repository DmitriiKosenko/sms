package com.springapp.mvc;

import com.springapp.mvc.model.entities.SmsHistory;
import junit.framework.TestCase;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by dmitry on 04.10.15.
 */
public abstract class SmsHistoryTestCase extends TestCase {

    protected static final String insertString = "INSERT INTO sms_history_temp (TELNUMBER, DATESEND, STATUS, MESSAGE) VALUES (?, ?, ?, ?)";
    protected static final String truncateTempTableSql = "TRUNCATE TABLE sms_history_temp";

    private static final String createTempTableSql = "CREATE TABLE sms_history_temp (\n" +
            "ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
            "TELNUMBER BIGINT NOT NULL,\n" +
            "DATESEND DATETIME NOT NULL,\n" +
            "STATUS TINYINT NOT NULL,\n" +
            "MESSAGE VARCHAR(1000)\n" +
            ");";
    private static final String dropTempTableSql = "DROP TABLE IF EXISTS sms_history_temp";

    protected JdbcTemplate template;

    @Override
    public void setUp() throws Exception {
        template = DataSource.getJDBCTemplate();

        template.execute(dropTempTableSql);
        template.execute(createTempTableSql);
    }

    @Override
    public void tearDown() {
        template.execute(dropTempTableSql);
    }

    //
    protected void setTempTableToSmsHistoryObject(SmsHistory smsHistory) throws Exception {
        Class smsHistoryClass = SmsHistory.class;

        Field field = smsHistoryClass.getDeclaredField("insertString");
        field.setAccessible(true);
        field.set(smsHistory, insertString);
    }

    protected SmsHistory create() {
        long telNumber = Util.randomTelNumber();
        Date dateSend = new Date();
        int status = Util.randomStatus();
        String message = Util.randomMessage();

        return new SmsHistory(telNumber, dateSend, status, message);
    }

    protected void insertRecords(int count) throws Exception {
        for (int i = 0; i < count; i++) {
            SmsHistory smsHistory = create();
            setTempTableToSmsHistoryObject(smsHistory);
            smsHistory.insert(template);
        }
    }

}
