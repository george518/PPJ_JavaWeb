package com.msb_demo1.common;

import com.msb_demo1.common.exception.RRException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 * @author theodo
 * @email 36780272@qq.com
 * @date 2017-03-23 15:50
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message,-1);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message,-1);
        }
    }
    public static void isZero(Integer object, String message) {
        if (object <= 0) {
            throw new RRException(message,-1);
        }
    }

}
