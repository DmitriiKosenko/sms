package com.springapp.mvc;

import com.mysql.jdbc.Driver;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.SQLException;

/**
 * Created by dmitry on 04.10.15.
 */
public class DataSource {

    private static SimpleDriverDataSource dataSource;

    private static final String host = "jdbc:mysql://localhost:3306/sms";
    private static final String name = "root";
    private static final String password = "";

    protected DataSource() {}

    public static synchronized SimpleDriverDataSource getInstance() throws SQLException {
        if (dataSource == null) {
            dataSource = new SimpleDriverDataSource(new Driver(), host, name, password);
        }

        return dataSource;
    }
}
