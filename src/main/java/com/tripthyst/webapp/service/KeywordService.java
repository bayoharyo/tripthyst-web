package com.tripthyst.webapp.service;

import com.tripthyst.webapp.model.KeywordModel;

import java.util.List;

public interface KeywordService {

    List<KeywordModel> getKeywordSuggestion(String word);

    List<KeywordModel> getMostVisited();

}
