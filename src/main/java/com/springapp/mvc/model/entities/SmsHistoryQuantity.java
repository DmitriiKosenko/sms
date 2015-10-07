package com.springapp.mvc.model.entities;

import com.sun.istack.internal.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dmitry on 08.10.15.
 */
public class SmsHistoryQuantity {

    private String selectQuery = "SELECT COUNT(ID) count FROM sms_history";

    public SmsHistoryQuantity() {}

    public @NotNull
    Long get(@NotNull final JdbcTemplate template) {

        return template.queryForObject(selectQuery, new Object[]{}, new RowMapper<Long>() {

            @Override
            public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getLong("count");
            }
        });
    }
}
