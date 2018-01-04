package com.tripthyst.webapp.Mapper;

import com.tripthyst.webapp.model.KeywordModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface KeywordMapper {

    @Select("select * from keyword where word like #{word}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "word", column = "word")
    })
    List<KeywordModel> selectKeywordSuggestion(@Param("word") String word);

    @Select("select * from keyword k join visit_count vc on k.id = vc.id_keyword order by vc.count desc limit 6")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "word", column = "word")
    })
    List<KeywordModel> selectMostVisited();
}
