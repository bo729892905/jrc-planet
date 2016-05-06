package com.jrcplanet.mapper;

import com.jrcplanet.domain.Dictionary;

public interface DictionaryMapper {
    int deleteById(String id);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    Dictionary selectById(String id);

    int updateByIdSelective(Dictionary record);

    int updateById(Dictionary record);
}