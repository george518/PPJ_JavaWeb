package com.msb_demo1.dao.mapper;

import com.msb_demo1.entity.PpUcAuth;
import com.msb_demo1.entity.PpUcAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PpUcAuthMapper {
    long countByExample(PpUcAuthExample example);

    int deleteByExample(PpUcAuthExample example);

    int insert(PpUcAuth record);

    int insertSelective(PpUcAuth record);

    List<PpUcAuth> selectByExample(PpUcAuthExample example);

    int updateByExampleSelective(@Param("record") PpUcAuth record, @Param("example") PpUcAuthExample example);

    int updateByExample(@Param("record") PpUcAuth record, @Param("example") PpUcAuthExample example);
}