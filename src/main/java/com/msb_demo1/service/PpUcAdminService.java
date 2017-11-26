package com.msb_demo1.service;

import com.msb_demo1.entity.PpUcAdmin;
import com.msb_demo1.entity.PpUcRole;

import java.util.List;

/**
 * @author haodaquan
 * @create 2017-11-23 10:12
 **/
public interface PpUcAdminService {
    List<PpUcAdmin> getAdminsForPage(String loginName, String realName);
    PpUcAdmin getAdminInfoByLoginName(String loginName);
    PpUcAdmin getOneAdminById(Integer id);
    Integer createAdmin(PpUcAdmin record);
    Integer updateAdmin(PpUcAdmin record);
    Integer changeAdminStatus(Integer Id,Short status);

}
