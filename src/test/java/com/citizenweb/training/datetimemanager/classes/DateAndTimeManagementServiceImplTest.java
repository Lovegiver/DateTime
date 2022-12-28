package com.citizenweb.training.datetimemanager.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class DateAndTimeManagementServiceImplTest {

    @BeforeEach
    void waitFewSeconds() throws InterruptedException {
        Thread.sleep(637);
    }

    private final DateAndTimeManagementService dateTimeService =
            new DateAndTimeManagementServiceImpl(LocalDateTime.of(
                    2022,
                    7,
                    8,
                    14,
                    0,
                    1
            ));

    @Test
    void obtainCalendar() {
        Calendar calendar = dateTimeService.obtainCalendar();
        assertThat(calendar).isNotNull();
        assertThat(calendar.get(Calendar.HOUR_OF_DAY)).isEqualTo(14);
        System.out.println("CALENDAR :\n" + calendar);
    }

    @Test
    void obtainCalendarAtStartOfDay() {
        Calendar calendar = dateTimeService.obtainCalendarAtStartOfDay();
        assertThat(calendar).isNotNull();
        assertThat(calendar.get(Calendar.HOUR_OF_DAY)).isZero();
        System.out.println("CALENDAR AT 00:00 :\n" + calendar);
    }

    @SuppressWarnings("deprecation")
    @Test
    void obtainDate() {
        Date date = dateTimeService.obtainDate();
        assertThat(date).isNotNull();
        assertThat(date.getHours()).isEqualTo(14);
        System.out.println("DATE :\n" + date);
    }

    @SuppressWarnings("deprecation")
    @Test
    void obtainDateAtStartOfDay() {
        Date date = dateTimeService.obtainDateAtStartOfDay();
        assertThat(date).isNotNull();
        assertThat(date.getHours()).isZero();
        System.out.println("DATE AT 00:00 :\n" + date);
    }

    @Test
    void obtainLocalDateTime() {
        LocalDateTime ldt = dateTimeService.obtainLocalDateTime();
        assertThat(ldt).isNotNull();
        assertThat(ldt.getHour()).isEqualTo(14);
        System.out.println("LOCAL_DATE_TIME :\n" + ldt);
    }

    @Test
    void obtainLocalDateTimeAtStartOfDay() {
        LocalDateTime ldt = dateTimeService.obtainLocalDateTimeAtStartOfDay();
        assertThat(ldt).isNotNull();
        assertThat(ldt.getHour()).isZero();
        System.out.println("LOCAL_DATE_TIME AT 00:00 :\n" + ldt);
    }
}