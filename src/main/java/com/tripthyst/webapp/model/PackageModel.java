package com.tripthyst.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageModel {

    private Long id;
    private AgentModel agent;
    private String destinationName;
    private String island;
    private double price;
    private List<ItineraryModel> itineraryList;
    private List<FacilityModel> facilityList;

}