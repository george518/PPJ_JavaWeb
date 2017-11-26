package com.msb_demo1.service;

import com.msb_demo1.entity.PpUcAuth;
import java.util.List;

/**
 * @author haodaquan
 * @create 2017-11-24 15:55
 **/
public interface PpUcAuthService {
    List<PpUcAuth> getAllAuth();
    PpUcAuth getOneAuthById(Integer id);
    Integer createAuth(PpUcAuth record);
    Integer updateAuth(PpUcAuth record);
    Integer deleteAuth(Integer AuthId);
    List<PpUcAuth> getChildAuth(Integer id);

}
