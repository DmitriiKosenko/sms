package com.springapp.mvc.controllers;

import com.springapp.mvc.DataSource;
import com.springapp.mvc.model.entities.SmsHistory;
import com.springapp.mvc.model.entities.SmsHistoryList;
import com.sun.istack.internal.NotNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 05.10.15.
 */
@RestController
public class GetHistoryController {

    /**
     *
     * @param pageNumber номер страницы истории, начинается с 1
     * @param pageSize размер страницы истории
     * @return
     */
    @RequestMapping("/get_history")
    public @NotNull List<SmsHistory> getHistory(
            @RequestParam(value="page") int pageNumber,
            @RequestParam(value="size") int pageSize
    ) {

        if (pageNumber < 0 || pageSize <= 0) {
            return new ArrayList<SmsHistory>();
        }

        try {
            return new SmsHistoryList().get(DataSource.getJDBCTemplate(), (pageNumber - 1) * pageSize, pageSize);
        } catch (SQLException e) {
            //
        }

        return new ArrayList<SmsHistory>();

    }
}
