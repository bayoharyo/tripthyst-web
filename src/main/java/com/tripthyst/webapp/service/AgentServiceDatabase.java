package com.tripthyst.webapp.service;

import com.tripthyst.webapp.mapper.AgentMapper;
import com.tripthyst.webapp.model.AgentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentServiceDatabase implements AgentService{

    @Autowired
    AgentMapper agentMapper;

    @Override
    public AgentModel getAgent(long id) {
        AgentModel agent = agentMapper.selectAgent(id);
        return agent;
    }

}
