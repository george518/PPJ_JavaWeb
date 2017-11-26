package com.msb_demo1.service.impl;


import com.msb_demo1.dao.mapper.PpUcAuthMapper;
import com.msb_demo1.entity.PpUcAuth;
import com.msb_demo1.entity.PpUcAuthExample;
import com.msb_demo1.service.PpUcAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haodaquan
 * @create 2017-11-24 15:56
 **/
@Service("PpUcAuthService")
public class PpUcAuthServiceImpl implements PpUcAuthService {

    @Autowired
    private PpUcAuthMapper ppUcAuthMapper;

    //获取全部节点
    public List<PpUcAuth> getAllAuth() {
        PpUcAuthExample example = new PpUcAuthExample();
        PpUcAuthExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        example.setOrderByClause("sort asc");
        return ppUcAuthMapper.selectByExample(example);
    }

    public List<PpUcAuth> getChildAuth(Integer id) {
        PpUcAuthExample example = new PpUcAuthExample();
        PpUcAuthExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        criteria.andPidEqualTo(id);
        example.setOrderByClause("sort asc");
        return ppUcAuthMapper.selectByExample(example);
    }

    //获取单个几点根据ID
    public PpUcAuth getOneAuthById(Integer id){
        PpUcAuthExample example = new PpUcAuthExample();
        PpUcAuthExample.Criteria criteria = example.createCriteria();

        if(!"".equals(id == null ? "" : id)){
            criteria.andIdEqualTo(id);
        }
        List<PpUcAuth> ppUcAuths = ppUcAuthMapper.selectByExample(example);

        if (ppUcAuths.isEmpty()){
            return  new PpUcAuth();
        }else{
            return ppUcAuths.get(0);
        }
    }

    public Integer createAuth(PpUcAuth record) {
        return ppUcAuthMapper.insert(record);
    }

    public Integer updateAuth(PpUcAuth record){
        PpUcAuthExample example = new PpUcAuthExample();
        PpUcAuthExample.Criteria criteria = example.createCriteria();

        if (record.getId()<1){
            return 0;
        }else{
            criteria.andIdEqualTo(record.getId());
            return ppUcAuthMapper.updateByExampleSelective(record,example);
        }
    }

    //逻辑删除
    public Integer deleteAuth(Integer authId){
        PpUcAuthExample example = new PpUcAuthExample();
        PpUcAuthExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(authId);

        PpUcAuth record = getOneAuthById(authId);
        Short status = 0;
        record.setStatus(status);
        if (authId<1){
            return 0;
        }else{
            return ppUcAuthMapper.updateByExampleSelective(record,example);
        }
    }
}
