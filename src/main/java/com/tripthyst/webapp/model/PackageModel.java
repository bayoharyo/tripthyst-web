package com.tripthyst.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageModel {

    private int id;
    private AgentModel agent;
    private String destinationName;
    private String island;
    private double price;

}