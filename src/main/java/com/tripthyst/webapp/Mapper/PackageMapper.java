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
    List<PackageModel> selectAllPackage();

    @Select("select * from travel_package where id=#{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "idAgent", column = "id_agent"),
            @Result(property = "packageName", column = "package_name"),
            @Result(property = "description", column = "description"),
            @Result(property = "destination", column = "destination"),
            @Result(property = "price", column = "price"),
    })
    List<PackageModel> selectPackagesByDest(@Param("id") int id);

    @Insert("insert into travel_package (id_agent, package_name, description, destination, price) values" +
            " (#{idAgent}, #{package_name}, #{description}, #{destination}, #{price})")
    void insertPackage(@Param("idAgent") int idAgent, @Param("package_name") String package_name,
                       @Param("description") String description, @Param("destination") int destination,
                       @Param("price") double price);
}
