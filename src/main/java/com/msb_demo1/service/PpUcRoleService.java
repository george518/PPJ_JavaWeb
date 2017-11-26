package com.msb_demo1.service;

import com.msb_demo1.entity.PpUcRole;

import java.util.List;

/**
 * @author haodaquan
 * @create 2017-11-25 16:17
 **/
public interface PpUcRoleService {
    List<PpUcRole> getRolesForPage(String roleName);
    PpUcRole getOneRoleById(Integer id);
    Integer createRole(PpUcRole record);
    Integer updateRole(PpUcRole record);
    Integer deleteRole(Integer roleId);
}
