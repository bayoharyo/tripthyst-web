package com.tripthyst.webapp.service;

import com.tripthyst.webapp.Mapper.KeywordMapper;
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
}
