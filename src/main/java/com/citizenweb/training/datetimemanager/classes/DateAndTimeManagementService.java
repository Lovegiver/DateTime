package com.citizenweb.training.datetimemanager.classes;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public interface DateAndTimeManagementService {

    Calendar obtainCalendar();
    Calendar obtainCalendarAtStartOfDay();
    Date obtainDate();
    Date obtainDateAtStartOfDay();
    LocalDateTime obtainLocalDateTime();
    LocalDateTime obtainLocalDateTimeAtStartOfDay();
    void switchTo(DateAndTimeManagementServiceImpl.TimeStyle style);
    void switchTo(DateAndTimeManagementServiceImpl.TimeStyle style, LocalDateTime dateTime);

}
