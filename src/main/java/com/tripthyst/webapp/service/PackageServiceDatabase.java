package com.tripthyst.webapp.service;

import com.tripthyst.webapp.Mapper.PackageMapper;
import com.tripthyst.webapp.model.PackageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageServiceDatabase implements PackageService{

    @Autowired
    PackageMapper packageMapper;

    @Override
    public List<PackageModel> getAllPackage() {
        return  packageMapper.selectAllPackage();
    }

    @Override
    public List<PackageModel> getPackagesByDest(int id) {
        return packageMapper.selectPackagesByDest(id);
    }
}
