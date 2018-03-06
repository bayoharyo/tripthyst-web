package com.tripthyst.webapp.service;

import com.tripthyst.webapp.model.PackageModel;

import java.util.List;

public interface PackageService {

    List<PackageModel> getAllPackage();

    PackageModel getPackageById(long id);

    List<PackageModel> getPackagesByKeyword(String word);

    void postPackage(int idAgent, String destinationName, String island, double price, List<String> schedules,
                     String facilities, List<String> places, List<Integer> days, List<String> scheds, List<String> descs);

    long getLatestId();

    void insertImage(long id, String imageName);

    List<String> getImage(long id);
}
