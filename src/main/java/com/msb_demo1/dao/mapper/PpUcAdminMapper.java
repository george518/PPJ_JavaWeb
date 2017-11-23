package com.msb_demo1.dao.mapper;

import com.msb_demo1.entity.PpUcAdmin;
import com.msb_demo1.entity.PpUcAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PpUcAdminMapper {
    long countByExample(PpUcAdminExample example);

    int deleteByExample(PpUcAdminExample example);

    int insert(PpUcAdmin record);

    int insertSelective(PpUcAdmin record);

    List<PpUcAdmin> selectByExample(PpUcAdminExample example);

    int updateByExampleSelective(@Param("record") PpUcAdmin record, @Param("example") PpUcAdminExample example);

    int updateByExample(@Param("record") PpUcAdmin record, @Param("example") PpUcAdminExample example);
}