package com.ego.service.impl;

import com.ego.mapper.AdminMapper;
import com.ego.pojo.AdminWithBLOBs;
import com.ego.result.BaseResult;
import com.ego.service.UserServiceI;
import com.ego.util.Md5Util;
import com.ego.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by jick on 2019/4/15.
 */
@Service
public class UserServiceImpl implements UserServiceI {


    @Autowired
    private AdminMapper  adminMapper;


    @Override
    public BaseResult adminSave(AdminWithBLOBs admin) {
        if(null ==admin){
            return  BaseResult.error();
        }

        //生成salt并存入用户信息
        String   salt = RandomUtil.getRandom1();

        admin.setEcSalt(salt);

        //根据salt加密密码
        String  password = Md5Util.getMd5WithSalt(admin.getPassword(),salt);

        admin.setPassword(password);

        //设置注册时间
        admin.setAddTime((int) LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));

        int  result  =  adminMapper.insertSelective(admin);

        return   result >0 ? BaseResult.success() : BaseResult.error();
    }
}
