package com.tripthyst.webapp.Mapper;

import com.tripthyst.webapp.model.AgentModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AgentMapper {

    @Select("select * from travel_agent where id=#{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "agentName", column = "agent_name"),
            @Result(property = "rating", column = "rating")
    })
    AgentModel getAgent(int id);

}
