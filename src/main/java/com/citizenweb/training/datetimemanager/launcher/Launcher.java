package com.citizenweb.training.datetimemanager.launcher;

import com.citizenweb.training.datetimemanager.classes.AnyObject;
import com.citizenweb.training.datetimemanager.classes.DateAndTimeManagementService;
import com.citizenweb.training.datetimemanager.classes.DateAndTimeManagementServiceImpl;
import lombok.extern.java.Log;

import java.time.LocalDateTime;

@Log
public class Launcher {

    public static void main(String[] args) throws InterruptedException {

        LocalDateTime simulatedDate = LocalDateTime.of(2021, 9, 27, 13, 0, 0);
        DateAndTimeManagementService dateTimeService = new DateAndTimeManagementServiceImpl(simulatedDate);

        AnyObject obj1 = AnyObject.builder()
                .nationalHealthId("01DF34Z")
                .name("Anna")
                .nextVisit(dateTimeService.obtainLocalDateTime())
                .build();

        Thread.sleep(750);

        AnyObject obj2 = AnyObject.builder()
                .nationalHealthId("02DJM92A")
                .name("Camille")
                .nextVisit(dateTimeService.obtainLocalDateTimeAtStartOfDay())
                .build();

        Thread.sleep(750);

        AnyObject obj3 = AnyObject.builder()
                .nationalHealthId("02DBB129")
                .name("Robert")
                .nextVisit(dateTimeService.obtainLocalDateTime())
                .build();

        log.info("Visitor 1 :\n" + obj1 + "\n- - - - - - - - - -");
        log.info("Visitor 2 :\n" + obj2 + "\n- - - - - - - - - -");
        log.info("Visitor 3 :\n" + obj3 + "\n- - - - - - - - - -");

    }

}
