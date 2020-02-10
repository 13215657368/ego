package com.ego.service.impl;

import com.ego.mapper.AdminMapper;
import com.ego.pojo.Admin;
import com.ego.pojo.AdminExample;
import com.ego.service.SSOServiceI;
import com.ego.util.JsonUtil;
import com.ego.util.Md5Util;
import com.ego.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import sun.security.krb5.internal.Ticket;

import java.util.List;

public class SSOServiceImpl implements SSOServiceI {

    private   static Logger  logger  = LoggerFactory.getLogger(SSOServiceImpl.class);

    @Autowired
    private AdminMapper  adminMapper;


    @Autowired
    private RedisTemplate  redisTemplate;


    @Value("${user.ticket}")
    private   String  userTicket;

    /**
     * 登录认证方法返回票据 ticket
     *
     * @param admin
     * @return
     */
    @Override
    public String login(Admin admin) {

        //判断输入信息是否合法
        String userName = admin.getUserName().trim();
        String password = admin.getPassword().trim();

        if (null != userName && userName.length() > 0) {
            //比较有没有用户。密码对不对
            AdminExample example = new AdminExample();
            example.createCriteria().andUserNameEqualTo(userName);

            //执行查询操作
            List<Admin> adminList = adminMapper.selectByExample(example);

            //判断用户是否存在
            if (null != adminList && adminList.size() > 0) {
                Admin a = adminList.get(0);
                //如果密码正确
                if (a.getPassword().equals(Md5Util.getMd5WithSalt(password, a.getEcSalt()))) {
                    //返回ticket
                    String ticket = userTicket + ":" + UUIDUtil.getUUID(); //userTicket：49ceb96a64f14e719a3e7c7b14338c26合适

                    //将信息存入到缓存数据库中
                    redisTemplate.opsForValue().set(ticket, JsonUtil.object2JsonStr(a));

                    return ticket;
                }

            } else {
                logger.error(" 系统错误，根据用户名查询出多个用户，用户名为：" + userName);
            }

        }
        logger.error("用户名或密码不能为空！");
        return null;

    }



    /**
     * 验证票据 ticket 返回用户信息
     *
     * @param ticket
     * @return
     */
    @Override
    public Admin validate(String ticket) {
        //根据传递过来的ticketc从redis中获取用户信息
          String  adminJson = (String) redisTemplate.opsForValue().get(ticket);

          //将收到的信息转换成object类型
        Admin admin = JsonUtil.jsonStr2Object(adminJson,Admin.class);

        return adminJson==null ? null :  admin;


    }

    /*
    系统退出
     */
    @Override
    public void logout(String ticket) {
        //清除redis中的键值为ticket的信息
        redisTemplate.delete(ticket);
    }
}