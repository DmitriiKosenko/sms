package com.springapp.mvc.model.entities;

import com.sun.istack.internal.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dmitry on 04.10.15.
 */
public class SmsHistoryList {

    // usefull if table rows count over google
    private String selectQuery =
            "SELECT a.ID, a.TELNUMBER, a.DATESEND, a.STATUS, a.MESSAGE FROM " +
                    "(SELECT * FROM sms_history ORDER BY DATESEND DESC LIMIT ?, ?) a " +
                    "INNER JOIN sms_history b ON a.id = b.id";

    public SmsHistoryList() {}

    public @NotNull List<SmsHistory> get(@NotNull final JdbcTemplate template, int offset, int length) {
        assert length >=0;
        assert offset >= 0;

        return template.query(selectQuery, new Object[]{offset, length}, new RowMapper<SmsHistory>() {

            @Override
            public SmsHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new SmsHistory(
                        rs.getLong("ID"),
                        rs.getLong("TELNUMBER"),
                        rs.getDate("DATESEND"),
                        rs.getInt("STATUS"),
                        rs.getString("MESSAGE")
                );
            }
        });
    }
}
