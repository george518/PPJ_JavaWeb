package com.msb_demo1.dao.mapper;

import com.msb_demo1.entity.PpUcRole;
import com.msb_demo1.entity.PpUcRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PpUcRoleMapper {
    long countByExample(PpUcRoleExample example);

    int deleteByExample(PpUcRoleExample example);

    int insert(PpUcRole record);

    int insertSelective(PpUcRole record);

    List<PpUcRole> selectByExample(PpUcRoleExample example);

    int updateByExampleSelective(@Param("record") PpUcRole record, @Param("example") PpUcRoleExample example);

    int updateByExample(@Param("record") PpUcRole record, @Param("example") PpUcRoleExample example);
}