package com.tripthyst.webapp.rest;

import com.tripthyst.webapp.model.AgentModel;
import com.tripthyst.webapp.model.PackageModel;
import com.tripthyst.webapp.model.RestModelWrapper;
import com.tripthyst.webapp.service.AgentService;
import com.tripthyst.webapp.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @Autowired
    PackageService packageService;

    @Autowired
    AgentService agentService;

    // ---------- Package ---------- //

    @RequestMapping("/getAllPackages")
    public RestModelWrapper<List<PackageModel>> getAllPackage() {

        RestModelWrapper<List<PackageModel>> result;

        List<PackageModel> allPackage = packageService.getAllPackage();

        if (allPackage.size() == 0) {
            result = new RestModelWrapper<>();
        } else {
            result = new RestModelWrapper<>(allPackage);
        }

        return result;
    }

    @RequestMapping("/getPackagesByKeyword/{word}")
    public RestModelWrapper<List<PackageModel>> getPackagesByDest(@PathVariable("word") String word) {

        RestModelWrapper<List<PackageModel>> result;

        List<PackageModel> packages = packageService.getPackagesByKeyword(word);

        if (packages.size() == 0) {
            result = new RestModelWrapper<>();
        } else {
            result = new RestModelWrapper<>(packages);
        }

        return result;

    }

    // ---------- Agent ----------  //

    @RequestMapping("/getAgent/{id}")
    public RestModelWrapper<AgentModel> getAgent(@PathVariable("id") int id) {

        RestModelWrapper<AgentModel> result;

        AgentModel agent = agentService.getAgent(id);

        if (agent == null) {
            result = new RestModelWrapper<>();
        } else {
            result = new RestModelWrapper<>(agent);
        }

        return result;
    }

}
