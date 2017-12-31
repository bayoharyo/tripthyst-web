package com.tripthyst.webapp.Mapper;

import com.tripthyst.webapp.model.PackageModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PackageMapper {

    @Select("select * from travel_package")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "idAgent", column = "id_agent"),
            @Result(property = "packageName", column = "package_name"),
            @Result(property = "description", column = "description"),
            @Result(property = "destination", column = "destination"),
            @Result(property = "price", column = "price"),
    })
    public List<PackageModel> selectAllPackage();

    @Select("select * from travel_package where id=#{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "idAgent", column = "id_agent"),
            @Result(property = "packageName", column = "package_name"),
            @Result(property = "description", column = "description"),
            @Result(property = "destination", column = "destination"),
            @Result(property = "price", column = "price"),
    })
    public List<PackageModel> selectPackagesByDest(@Param("id") int id);

}
