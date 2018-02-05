package com.tripthyst.webapp.mapper;

import com.tripthyst.webapp.model.AgentModel;
import com.tripthyst.webapp.model.PackageModel;
import org.apache.ibatis.annotations.*;
import org.springframework.security.access.method.P;

import java.util.Date;
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
            "select id_package from package_keyword where word like #{word1} or word like #{word2} " +
            "or word like #{word3} or word like #{word4})")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "agent", column = "id_agent", one = @One(select = "selectAgent")),
            @Result(property = "destinationName", column = "destination_name"),
            @Result(property = "island", column = "island"),
            @Result(property = "price", column = "price")
    })
    List<PackageModel> selectPackagesByKeyword(@Param("word1") String word1, @Param("word2") String word2,
                                               @Param("word3") String word3, @Param("word4") String word4);

    @Select("select * from travel_package where id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "agent", column = "id_agent", one = @One(select = "selectAgent")),
            @Result(property = "destinationName", column = "destination_name"),
            @Result(property = "island", column = "island"),
            @Result(property = "price", column = "price")
    })
    PackageModel selectPackageById(@Param("id") long id);

    @Select("select * from travel_agent where id=#{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "agentName", column = "agent_name"),
            @Result(property = "bankAccountNumber", column = "bank_account_number"),
            @Result(property = "officeAddress", column = "office_address"),
            @Result(property = "rating", column = "rating")
    })
    AgentModel selectAgent(@Param("id") int id);

    @Select("select * from travel_package where id = (" +
            "select max(id) from travel_package)")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "agent", column = "id_agent", one = @One(select = "selectAgent")),
            @Result(property = "destinationName", column = "destination_name"),
            @Result(property = "island", column = "island"),
            @Result(property = "price", column = "price")
    })
    PackageModel selectLatestInsertedPackage();

    @Insert("insert into travel_package (id_agent, destination_name, island, price) values (#{id_agent}, #{destination_name}, " +
            "#{island}, #{price})")
    void insertTravelPackage(@Param("id_agent") int idAgent, @Param("destination_name") String destinationName,
                             @Param("island") String island, @Param("price") double price);

    @Insert("insert into package_schedule (id_package, schedule) values (#{id_package}, #{schedule})")
    void insertPackageSchedule(@Param("id_package") long idPackage, @Param("schedule") Date schedule);

    @Insert("insert into package_facility (id_package, facility) values (#{id_package}, #{facility})")
    void insertPackageFacility(@Param("id_package") long idPackage, @Param("facility") String facility);

    @Insert("insert into package_itinerary (id_package, place, day, schedule, description) " +
            "values (#{id_package}, #{place}, #{day}, #{schedule}, #{description})")
    void insertPackageItinerary(@Param("id_package") long idPackage, @Param("place") String place, @Param("day") int day,
                                @Param("schedule") Date schedule, @Param("description") String description);

    @Insert("insert into package_keyword (id_package, word) values (#{id_package}, #{word})")
    void insertPackageKeyword(@Param("id_package") long idPackage, @Param("word") String word);
}
