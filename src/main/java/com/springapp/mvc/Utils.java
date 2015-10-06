package com.springapp.mvc;

import com.springapp.mvc.model.entities.SmsHistory;
import com.springapp.mvc.model.entities.SmsHistoryDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dmitry on 06.10.15.
 */
public final class Utils {

    public static List<SmsHistoryDTO> convert(Collection<SmsHistory> smsHistories) {
        List<SmsHistoryDTO> result = new ArrayList<SmsHistoryDTO>();
        for (SmsHistory elem : smsHistories) {
            result.add(new SmsHistoryDTO(elem));
        }

        return result;
    }
}
