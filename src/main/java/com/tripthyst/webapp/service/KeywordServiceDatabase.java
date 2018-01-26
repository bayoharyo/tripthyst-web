package com.tripthyst.webapp.service;

import com.tripthyst.webapp.mapper.KeywordMapper;
import com.tripthyst.webapp.model.KeywordModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordServiceDatabase implements KeywordService {

    @Autowired
    KeywordMapper keywordMapper;

    @Override
    public List<KeywordModel> getKeywordSuggestion(String word) {
        return keywordMapper.selectKeywordSuggestion("%" + word + "%");
    }

    @Override
    public List<KeywordModel> getMostVisited() {
        return keywordMapper.selectMostVisited();
    }

    @Override
    public void postKeyword(String destinationName, String island, List<String> places) {
        keywordMapper.insertKeyword(destinationName);
        keywordMapper.insertKeyword(island);

        for (String place : places) {
            keywordMapper.insertKeyword(place);
        }
    }


}
