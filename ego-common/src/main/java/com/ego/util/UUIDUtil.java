package com.ego.util;

import java.util.UUID;

/**
 * Created by jick on 2019/3/26.
 */
public class UUIDUtil {
    /*
    * 返回UUID，将“-”替换为“”
    *
    * */

    public   static    String   getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
