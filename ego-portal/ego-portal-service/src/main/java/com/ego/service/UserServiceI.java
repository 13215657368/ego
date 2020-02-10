package com.ego.service;

import com.ego.pojo.AdminWithBLOBs;
import com.ego.result.BaseResult;

/**
 * Created by jick on 2019/4/15.
 * 用户信息保存
 */
public interface UserServiceI {
    /**
     * 用户注册
     */
    BaseResult  adminSave(AdminWithBLOBs admin);
}
