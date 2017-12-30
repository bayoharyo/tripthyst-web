package com.tripthyst.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageModel {

    int id;
    int idAgent;
    String pakageName;
    String description;
    String destination;
    double price;

}
