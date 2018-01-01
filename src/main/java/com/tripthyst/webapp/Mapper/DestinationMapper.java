package com.tripthyst.webapp.Mapper;

import com.tripthyst.webapp.model.DestinationModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DestinationMapper {

    @Select("select * from destination where id=#{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "destinationName", column = "destination_name")
    })
    DestinationModel getDestination(@Param("id") int id);

}
