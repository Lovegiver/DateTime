package com.citizenweb.training.datetimemanager.classes;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class AnyObject {

    private final String nationalHealthId;
    private final String name;
    private final LocalDateTime nextVisit;

    public AnyObject(String nationalHealthId, String name, LocalDateTime nextVisit) {
        this.nationalHealthId = nationalHealthId;
        this.name = name;
        this.nextVisit = nextVisit;
    }

}
