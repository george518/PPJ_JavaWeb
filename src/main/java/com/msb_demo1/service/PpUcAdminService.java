package com.msb_demo1.service;

import com.msb_demo1.entity.PpUcAdmin;

import java.util.List;

/**
 * @author haodaquan
 * @create 2017-11-23 10:12
 **/
public interface PpUcAdminService {
    PpUcAdmin getAdminInfoByLoginName(String loginName);
    List<PpUcAdmin> getAdminsForPage(String loginName, String realName);

}
