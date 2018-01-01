package com.tripthyst.webapp.service;

import com.tripthyst.webapp.model.PackageModel;

import java.util.List;

public interface PackageService {

    List<PackageModel> getAllPackage();

    List<PackageModel> getPackagesByDest(int id);

    void addPackage(int idAgent, String packageName, String description, int destination, double price);

}
