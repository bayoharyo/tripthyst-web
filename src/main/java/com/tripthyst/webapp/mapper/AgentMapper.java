package com.tripthyst.webapp.mapper;

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
            @Result(property = "bankAccountNumber", column = "bank_account_number"),
            @Result(property = "officeAddress", column = "office_address"),
            @Result(property = "rating", column = "rating")
    })
    AgentModel selectAgent(int id);

}
