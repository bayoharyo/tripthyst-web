package com.tripthyst.webapp.rest;

import com.tripthyst.webapp.Mapper.PackageMapper;
import com.tripthyst.webapp.model.PackageModel;
import com.tripthyst.webapp.model.RestModelWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @Autowired
    PackageMapper packageMapper;

    @RequestMapping("/getAllPackages")
    public RestModelWrapper<List<PackageModel>> getAllPackage() {

        RestModelWrapper<List<PackageModel>> result = new RestModelWrapper(packageMapper.selectAllPackage());

        return result;
    }

}
