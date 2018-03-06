package com.tripthyst.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryModel {

    private Long id;
    private long idPackage;
    private String place;
    private int day;
    private Time schedule;
    private String description;

}
