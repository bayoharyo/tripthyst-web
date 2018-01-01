package com.tripthyst.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageModel {

    private int id;
    private int idAgent;
    private String packageName;
    private String description;
    private int destination;
    private double price;

}
