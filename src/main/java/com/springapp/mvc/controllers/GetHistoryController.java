package com.springapp.mvc.controllers;

import com.google.gson.GsonBuilder;
import com.springapp.mvc.DataSource;
import com.springapp.mvc.Utils;
import com.springapp.mvc.model.entities.SmsHistoryQuantity;
import com.springapp.mvc.model.entities.SmsHistoryDTO;
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
    @RequestMapping(value = "/get_history", produces = "text/plain;charset=UTF-8")
    public @NotNull String getHistory(
            @RequestParam(value="page") int pageNumber,
            @RequestParam(value="size") int pageSize
    ) {

        List<SmsHistoryDTO> history = getHistoryImpl(pageNumber, pageSize);
        Long quantity = getHistoryRowsQuantity() / pageSize + 1;
        Object[] result = new Object[] {quantity, history};

        return new GsonBuilder().setPrettyPrinting().create().toJson(result);
    }

    private List<SmsHistoryDTO> getHistoryImpl(int pageNumber, int pageSize) {
        if (pageNumber < 0 || pageSize <= 0) {
            return new ArrayList<SmsHistoryDTO>();
        }

        try {
            return Utils.convert(
                    new SmsHistoryList().get(DataSource.getJDBCTemplate(), (pageNumber - 1) * pageSize, pageSize)
            );
        } catch (SQLException e) {
            //
        }

        return new ArrayList<SmsHistoryDTO>();
    }

    private Long getHistoryRowsQuantity() {
        try {
            return new SmsHistoryQuantity().get(DataSource.getJDBCTemplate());
        } catch (SQLException e) {
            //
        }

        return 0l;
    }
}
