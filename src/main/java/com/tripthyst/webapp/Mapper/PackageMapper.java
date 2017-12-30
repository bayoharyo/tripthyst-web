package com.tripthyst.webapp.Mapper;

import com.tripthyst.webapp.model.PackageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PackageMapper {

    @Select("select * from travel_package")
    public List<PackageModel> selectAllPackage();

}
