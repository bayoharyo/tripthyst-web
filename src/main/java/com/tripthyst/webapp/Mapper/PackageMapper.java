package com.tripthyst.webapp.Mapper;

import com.tripthyst.webapp.model.AgentModel;
import com.tripthyst.webapp.model.PackageModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PackageMapper {

    @Select("select * from travel_package")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "agent", column = "id_agent", one = @One(select = "selectAgent")),
            @Result(property = "destinationName", column = "destination_name"),
            @Result(property = "island", column = "island"),
            @Result(property = "price", column = "price")
    })
    List<PackageModel> selectAllPackage();

    @Select("select * from travel_package where id in (" +
            "select id_package from package_keyword where word like #{word})")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "agent", column = "id_agent", one = @One(select = "selectAgent")),
            @Result(property = "destinationName", column = "destination_name"),
            @Result(property = "island", column = "island"),
            @Result(property = "price", column = "price")
    })
    List<PackageModel> selectPackagesByKeyword(@Param("word") String word);

    @Select("select * from travel_agent where id=#{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "agentName", column = "agent_name"),
            @Result(property = "rating", column = "rating")
    })
    AgentModel selectAgent(@Param("id") int id);
}
