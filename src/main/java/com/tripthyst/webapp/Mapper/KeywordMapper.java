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

}
