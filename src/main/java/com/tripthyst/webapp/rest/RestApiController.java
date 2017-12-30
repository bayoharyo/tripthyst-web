package com.tripthyst.webapp.rest;

import com.tripthyst.webapp.model.PackageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @RequestMapping("/getAllPackages")
    public List<PackageModel> getAllPackage() {
        PackageModel package1 = new PackageModel(1, 100, "Paket Lombok Murah", "Sudah termasuk transport di lokasi tujuan", "Lombok", 3000000);
        PackageModel package2 = new PackageModel(2, 102, "Paket Bali Murah", "Belum termasuk transport di lokasi tujuan", "Bali", 4000000);

        List<PackageModel> allPackage = new ArrayList<>();

        allPackage.add(package1);
        allPackage.add(package2);

        return allPackage;
    }

}
