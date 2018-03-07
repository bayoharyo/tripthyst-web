package com.tripthyst.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentModel {

    private long id;
    private String username;
    private String agentName;
    private String bankAccountNumber;
    private String officeAddress;
    private double rating;
    private String imageName;
    private String imageUrl;

    public void setImageUrl() {
        this.setImageUrl("/api/getImage/" + this.imageName);
    }

}
