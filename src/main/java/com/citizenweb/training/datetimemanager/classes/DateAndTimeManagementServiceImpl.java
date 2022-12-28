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

    private Clock realTimeClock;
    private Clock simulatedTimeClock;
    private final long timeAtInit;
    private final ZoneId defaultZoneId;
    private final ZoneOffset defaultOffset;
    private boolean isSimulatedTime = Boolean.FALSE;
    private TimeStyle style = TimeStyle.REAL;

    public DateAndTimeManagementServiceImpl() {
        LocalDateTime realDateTime = LocalDateTime.now();
        log.info("Used date & time are NOW ones : " + realDateTime);
        this.timeAtInit = System.currentTimeMillis();
        this.defaultZoneId = ZoneId.systemDefault();
        this.defaultOffset = realDateTime.atZone(this.defaultZoneId).getOffset();
        this.realTimeClock = Clock.systemDefaultZone();
        this.isSimulatedTime = Boolean.FALSE;
        this.style = TimeStyle.REAL;
    }

    public DateAndTimeManagementServiceImpl(@NotNull LocalDateTime simulatedDateTime) {
        log.info("Injected date & time are : " + simulatedDateTime);
        this.timeAtInit = System.currentTimeMillis();
        this.defaultZoneId = ZoneId.systemDefault();
        this.defaultOffset = simulatedDateTime.atZone(this.defaultZoneId).getOffset();
        this.simulatedTimeClock = Clock.fixed(simulatedDateTime.toInstant(this.defaultOffset), this.defaultZoneId);
        this.isSimulatedTime = Boolean.TRUE;
        this.style = TimeStyle.SIMULATED;
    }

    @Override
    public Calendar obtainCalendar() {
        Instant instant;
        if (isSimulatedTime) {
            instant = this.simulatedTimeClock.instant().plusMillis(timePassing());
        } else {
            instant = this.realTimeClock.instant();
        }
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, this.defaultZoneId);
        return GregorianCalendar.from(zdt);
    }

    @Override
    public Calendar obtainCalendarAtStartOfDay() {
        Instant instant;
        if (isSimulatedTime) {
            instant = this.simulatedTimeClock.instant();
        } else {
            instant = this.realTimeClock.instant();
        }
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, this.defaultZoneId).toLocalDate().atStartOfDay(this.defaultZoneId);
        return GregorianCalendar.from(zdt);
    }

    @Override
    public Date obtainDate() {
        Instant instant;
        if (isSimulatedTime) {
            instant = this.simulatedTimeClock.instant().plusMillis(timePassing());
        } else {
            instant = this.realTimeClock.instant();
        }
        return Date.from(instant);
    }

    @Override
    public Date obtainDateAtStartOfDay() {
        Instant instant;
        if (isSimulatedTime) {
            instant = this.simulatedTimeClock.instant();
        } else {
            instant = this.realTimeClock.instant();
        }
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, this.defaultZoneId);
        return Date.from(zdt.toLocalDate().atStartOfDay().toInstant(this.defaultOffset));
    }

    @Override
    public LocalDateTime obtainLocalDateTime() {
        Instant instant;
        if (isSimulatedTime) {
            instant = this.simulatedTimeClock.instant().plusMillis(timePassing());
        } else {
            instant = this.realTimeClock.instant();
        }
        return LocalDateTime.ofInstant(instant, this.defaultZoneId);
    }

    @Override
    public LocalDateTime obtainLocalDateTimeAtStartOfDay() {
        if (isSimulatedTime) {
            return LocalDateTime.now(this.simulatedTimeClock).toLocalDate().atStartOfDay();
        } else {
            return LocalDateTime.now(this.realTimeClock).toLocalDate().atStartOfDay();
        }

    }

    @Override
    public void switchTo(TimeStyle style) {
        if (this.style.equals(style)) return;
        if (this.realTimeClock == null) {
            this.realTimeClock = Clock.systemDefaultZone();
        }
        this.style = style;
        if (TimeStyle.REAL.equals(style)) {
            this.isSimulatedTime = Boolean.FALSE;
        } else {
            this.isSimulatedTime = Boolean.TRUE;
        }
    }

    @Override
    public void switchTo(TimeStyle style, LocalDateTime dateTime) {
        if (this.style.equals(style)) return;
        if (this.simulatedTimeClock == null) {
            this.simulatedTimeClock = Clock.fixed(dateTime.toInstant(this.defaultOffset), this.defaultZoneId);
        }
        this.style = style;
        if (TimeStyle.REAL.equals(style)) {
            this.isSimulatedTime = Boolean.FALSE;
        } else {
            this.isSimulatedTime = Boolean.TRUE;
        }
    }

    private long timePassing() {
        return System.currentTimeMillis()-this.timeAtInit;
    }

    public enum TimeStyle {
        REAL,
        SIMULATED,
    }
}
