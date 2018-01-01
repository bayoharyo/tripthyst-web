package com.tripthyst.webapp.service;

import com.tripthyst.webapp.Mapper.DestinationMapper;
import com.tripthyst.webapp.model.DestinationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DestinationServiceDatabase implements DestinationService {

    @Autowired
    DestinationMapper destinationMapper;

    @Override
    public DestinationModel getDestination(int id) {
        return destinationMapper.getDestination(id);
    }
}
