package com.springapp.mvc;

import com.springapp.mvc.model.entities.SmsHistory;
import com.springapp.mvc.model.entities.SmsHistoryList;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by dmitry on 04.10.15.
 */
public class SmsHistoryListTests extends SmsHistoryTestCase {

    private static final String selectQuery =
            "SELECT a.TELNUMBER, a.DATESEND, a.STATUS, a.MESSAGE FROM " +
                    "(SELECT * FROM sms_history_temp ORDER BY DATESEND DESC LIMIT ?, ?) a " +
                    "INNER JOIN sms_history_temp b ON a.id = b.id";

    public void testGet_EmptyTable_EmptyList() throws Exception{
        template.execute(truncateTempTableSql);
        insertRecords(0);

        int offset = 0;
        int length = 10;
        SmsHistoryList smsHistoryList = createObject();
        List<SmsHistory> result = smsHistoryList.get(template, offset, length);
        assertTrue(result.isEmpty());

    }

    public void testGet_5RecordsTable_5RecordsList() throws Exception {
        template.execute(truncateTempTableSql);
        insertRecords(5);

        int offset = 0;
        int length = 10;
        SmsHistoryList smsHistoryList = createObject();
        List<SmsHistory> result = smsHistoryList.get(template, offset, length);
        assertTrue(result.size() == 5);
    }

    public void testGet_100RecordsTableOffset40_From40To50Records() throws Exception {
        template.execute(truncateTempTableSql);
        insertRecords(100);

        int offset = 40;
        int length = 10;
        SmsHistoryList smsHistoryList = createObject();
        List<SmsHistory> result = smsHistoryList.get(template, offset, length);
        assertTrue(result.size() == 10);

    }

    protected void setTempTableToSmsHistoryListObject(SmsHistoryList smsHistoryList) throws Exception {
        Class smsHistoryListClass = SmsHistoryList.class;

        Field field = smsHistoryListClass.getDeclaredField("selectQuery");
        field.setAccessible(true);
        field.set(smsHistoryList, selectQuery);
    }

    protected SmsHistoryList createObject() throws Exception {
        SmsHistoryList smsHistoryList = new SmsHistoryList();
        setTempTableToSmsHistoryListObject(smsHistoryList);

        return smsHistoryList;
    }
}
