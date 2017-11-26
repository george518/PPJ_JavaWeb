package com.msb_demo1.service.impl;

import com.msb_demo1.dao.mapper.PpUcRoleMapper;
import com.msb_demo1.entity.PpUcRole;
import com.msb_demo1.entity.PpUcRoleExample;
import com.msb_demo1.service.PpUcRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haodaquan
 * @create 2017-11-25 16:17
 **/

@Service("PpUcRoleService")
public class PpUcRoleServiceImpl implements PpUcRoleService {
    @Autowired
    private PpUcRoleMapper ppUcRoleMapper;

    public List<PpUcRole> getRolesForPage(String roleName,Short status){
        PpUcRoleExample example = new PpUcRoleExample();
        PpUcRoleExample.Criteria criteria = example.createCriteria();
        if (status!=-1){
            criteria.andStatusEqualTo(status);
        }

        if(!"".equals((roleName == null) ? "" : roleName)){
            criteria.andRoleNameLike("%"+roleName+"%");
        }
        example.setOrderByClause("id desc");
        return ppUcRoleMapper.selectByExample(example);
    }

    /**
     * 获取一个Role
     * @param id
     * @return
     */
    public PpUcRole getOneRoleById(Integer id){
        PpUcRoleExample example = new PpUcRoleExample();
        PpUcRoleExample.Criteria criteria = example.createCriteria();
        if (id==0){
            return new PpUcRole();
        }
        criteria.andIdEqualTo(id);
        example.setOrderByClause("id desc");
        List<PpUcRole> roles = ppUcRoleMapper.selectByExample(example);

        if (roles.isEmpty()){
            return  new PpUcRole();
        }else{
            return roles.get(0);
        }
    }

    /**
     * 新增角色
     * @param record
     * @return
     */
    public Integer createRole(PpUcRole record) {
        if (record.getRoleName()==""){
            return 0;
        }
        return ppUcRoleMapper.insert(record);
    }

    public Integer updateRole(PpUcRole record){
        PpUcRoleExample example = new PpUcRoleExample();
        PpUcRoleExample.Criteria criteria = example.createCriteria();

        if(!"".equals(record.getId() == null ? "" : record.getId())){
            criteria.andIdEqualTo(record.getId());
        }

        return ppUcRoleMapper.updateByExampleSelective(record,example);
    }

    /**
     * 修改状态
     * @param roleId
     * @param status
     * @return
     */
    public Integer changeRoleStatus(Integer roleId,Short status){
        if (roleId<1){
            return 0;
        }
        PpUcRoleExample example = new PpUcRoleExample();
        PpUcRoleExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(roleId);
        PpUcRole record = getOneRoleById(roleId);
        record.setStatus(status);
        return ppUcRoleMapper.updateByExampleSelective(record,example);
    }
}
