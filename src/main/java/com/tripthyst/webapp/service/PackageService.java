package com.tripthyst.webapp.service;

import com.tripthyst.webapp.model.PackageModel;

import java.util.List;

public interface PackageService {

    List<PackageModel> getAllPackage();

    List<PackageModel> getPackagesByKeyword(String word);

}
