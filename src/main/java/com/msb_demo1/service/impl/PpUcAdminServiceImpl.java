package com.msb_demo1.service.impl;

import com.msb_demo1.dao.mapper.PpUcAdminMapper;
import com.msb_demo1.entity.PpUcAdmin;
import com.msb_demo1.entity.PpUcAdminExample;
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

    public PpUcAdmin getAdminInfoByLoginName(String loginName) {
        PpUcAdminExample example = new PpUcAdminExample();
        PpUcAdminExample.Criteria criteria = example.createCriteria();

        if("".equals((loginName == null) ? "" : loginName)){
            return null;
        }

        criteria.andLoginNameEqualTo(loginName);
        List<PpUcAdmin> ppUcAdmins = ppUcAdminMapper.selectByExample(example);

        if (ppUcAdmins.isEmpty()){
            return  new PpUcAdmin();
        }else{
            return ppUcAdmins.get(0);
        }
    }
}