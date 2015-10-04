package com.springapp.mvc;

import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by dmitry on 05.10.15.
 */
public class SmsSender {
//    final Logger log = LoggerFactory.getLogger(getClass());
    final Random random = new Random();
    public boolean sendSms(String phone, String message){
        boolean success = random.nextBoolean();
//        log.info("message sent to {} with status {}:\n{}", new Object[]{phone, success, message});
        return success;
    }
}
