package com.msb_demo1.service;

import com.msb_demo1.entity.PpUcAdmin;

/**
 * @author haodaquan
 * @create 2017-11-23 10:12
 **/
public interface PpUcAdminService {
    PpUcAdmin getAdminInfoByLoginName(String loginName);
}
