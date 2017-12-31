package com.tripthyst.webapp.rest;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import com.tripthyst.webapp.Mapper.PackageMapper;
import com.tripthyst.webapp.model.PackageModel;
import com.tripthyst.webapp.model.RestModelWrapper;
import com.tripthyst.webapp.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @Autowired
    PackageService packageService;

    @RequestMapping("/getAllPackages")
    public RestModelWrapper<List<PackageModel>> getAllPackage() {

        RestModelWrapper<List<PackageModel>> result;

        List<PackageModel> allPackage = packageService.getAllPackage();

        if (allPackage.size() == 0) {
            result = new RestModelWrapper();
        } else {
            result = new RestModelWrapper(allPackage);
        }

        return result;
    }

    @RequestMapping("/getPackagesByDest/{id}")
    public RestModelWrapper<List<PackageModel>> getPackagesByDest(@PathVariable("id") int id) {

        RestModelWrapper<List<PackageModel>> result;

        List<PackageModel> packages = packageService.getPackagesByDest(id);

        if (packages.size() == 0) {
            result = new RestModelWrapper();
        } else {
            result = new RestModelWrapper(packages);
        }

        return result;

    }

}
