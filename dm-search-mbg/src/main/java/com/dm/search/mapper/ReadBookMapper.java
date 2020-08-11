package com.dm.search.mapper;

import com.dm.search.model.ReadBook;
import com.dm.search.model.ReadBookExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReadBookMapper {

    long countByExample(ReadBookExample example);

    int deleteByExample(ReadBookExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReadBook record);

    int insertSelective(ReadBook record);

    List<ReadBook> selectByExampleWithBLOBs(ReadBookExample example);

    List<ReadBook> selectByExample(ReadBookExample example);

    ReadBook selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReadBook record, @Param("example") ReadBookExample example);

    int updateByExampleWithBLOBs(@Param("record") ReadBook record, @Param("example") ReadBookExample example);

    int updateByExample(@Param("record") ReadBook record, @Param("example") ReadBookExample example);

    int updateByPrimaryKeySelective(ReadBook record);

    int updateByPrimaryKeyWithBLOBs(ReadBook record);

    int updateByPrimaryKey(ReadBook record);
}