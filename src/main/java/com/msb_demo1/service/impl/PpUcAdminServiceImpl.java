package com.msb_demo1.service.impl;

import com.msb_demo1.dao.mapper.PpUcAdminMapper;
import com.msb_demo1.entity.*;
import com.msb_demo1.service.PpUcAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haodaquan
 * @create 2017-11-23 10:13
 **/
@Service("PpUcAdminService")
public class PpUcAdminServiceImpl implements PpUcAdminService {

    @Autowired
    private PpUcAdminMapper ppUcAdminMapper;

    /**
     * 登录查询
     * @param loginName
     * @return
     */
    public PpUcAdmin getAdminInfoByLoginName(String loginName) {
        PpUcAdminExample example = new PpUcAdminExample();
        PpUcAdminExample.Criteria criteria = example.createCriteria();

        if("".equals((loginName == null) ? "" : loginName)){
            return  new PpUcAdmin();
        }

        criteria.andLoginNameEqualTo(loginName);
        List<PpUcAdmin> ppUcAdmins = ppUcAdminMapper.selectByExample(example);

        if (ppUcAdmins.isEmpty()){
            return  new PpUcAdmin();
        }else{
            return ppUcAdmins.get(0);
        }
    }

    /**
     * 列表查询
     * @param loginName
     * @param realName
     * @return
     */
    public List<PpUcAdmin> getAdminsForPage(String loginName, String realName) {
        PpUcAdminExample example = new PpUcAdminExample();
        PpUcAdminExample.Criteria criteria = example.createCriteria();

        if(!"".equals((loginName == null) ? "" : loginName)){
            criteria.andLoginNameLike("%"+loginName+"%");
        }

        if(!"".equals((realName == null) ? "" : realName)){
            criteria.andRealNameLike("%"+realName+"%");
        }
        example.setOrderByClause("id desc");
        return ppUcAdminMapper.selectByExample(example);
    }


    public PpUcAdmin getOneAdminById(Integer id){
        PpUcAdminExample example = new PpUcAdminExample();
        PpUcAdminExample.Criteria criteria = example.createCriteria();
        if (id==0){
            return new PpUcAdmin();
        }
        criteria.andIdEqualTo(id);
        List<PpUcAdmin> admins = ppUcAdminMapper.selectByExample(example);

        if (admins.isEmpty()){
            return  new PpUcAdmin();
        }else{
            return admins.get(0);
        }
    }

    /**
     * 新增
     * @param record
     * @return
     */
    public Integer createAdmin(PpUcAdmin record) {
        if (record.getLoginName()==""){
            return 0;
        }
        return ppUcAdminMapper.insert(record);
    }

    /**
     * 修改
     * @param record
     * @return
     */
    public Integer updateAdmin(PpUcAdmin record){
        PpUcAdminExample example = new PpUcAdminExample();
        PpUcAdminExample.Criteria criteria = example.createCriteria();

        if(!"".equals(record.getId() == null ? "" : record.getId())){
            criteria.andIdEqualTo(record.getId());
        }else{
            return 0;
        }
        return ppUcAdminMapper.updateByExampleSelective(record,example);
    }

    /**
     * 修改状态
     * @param adminId
     * @param status
     * @return
     */
    public Integer changeAdminStatus(Integer adminId,Short status){

        PpUcAdminExample example = new PpUcAdminExample();
        PpUcAdminExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(adminId);

        PpUcAdmin record = getOneAdminById(adminId);
        record.setStatus(status);

        if (adminId<1){
            return 0;
        }else{
            return ppUcAdminMapper.updateByExampleSelective(record,example);
        }
    }
}
