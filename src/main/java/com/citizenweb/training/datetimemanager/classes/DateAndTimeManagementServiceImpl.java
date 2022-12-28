package com.citizenweb.training.datetimemanager.classes;

import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Log
public class DateAndTimeManagementServiceImpl implements DateAndTimeManagementService {

    private final Clock simulatedTimeClock;
    private final long timeAtInit;
    private final ZoneId defaultZoneId;
    private final ZoneOffset defaultOffset;

    public DateAndTimeManagementServiceImpl(@NotNull LocalDateTime simulatedDateTime) {
        log.info("Injected date & time are : " + simulatedDateTime);
        this.timeAtInit = System.currentTimeMillis();
        this.defaultZoneId = ZoneId.systemDefault();
        this.defaultOffset = simulatedDateTime.atZone(this.defaultZoneId).getOffset();
        this.simulatedTimeClock = Clock.fixed(simulatedDateTime.toInstant(this.defaultOffset), this.defaultZoneId);
    }

    @Override
    public Calendar obtainCalendar() {
        Instant instant = this.simulatedTimeClock.instant().plusMillis(timePassing());
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, this.defaultZoneId);
        return GregorianCalendar.from(zdt);
    }

    @Override
    public Calendar obtainCalendarAtStartOfDay() {
        Instant instant = this.simulatedTimeClock.instant();
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, this.defaultZoneId).toLocalDate().atStartOfDay(this.defaultZoneId);
        return GregorianCalendar.from(zdt);
    }

    @Override
    public Date obtainDate() {
        Instant instant = this.simulatedTimeClock.instant().plusMillis(timePassing());
        return Date.from(instant);
    }

    @Override
    public Date obtainDateAtStartOfDay() {
        Instant instant = this.simulatedTimeClock.instant();
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, this.defaultZoneId);
        return Date.from(zdt.toLocalDate().atStartOfDay().toInstant(this.defaultOffset));
    }

    @Override
    public LocalDateTime obtainLocalDateTime() {
        Instant instant = Instant.now(this.simulatedTimeClock).plusMillis(timePassing());
        return LocalDateTime.ofInstant(instant, this.defaultZoneId);
    }

    @Override
    public LocalDateTime obtainLocalDateTimeAtStartOfDay() {
        return LocalDateTime.now(this.simulatedTimeClock).toLocalDate().atStartOfDay();
    }

    private long timePassing() {
        return System.currentTimeMillis()-this.timeAtInit;
    }
}
