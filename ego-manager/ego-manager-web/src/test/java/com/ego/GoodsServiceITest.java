package com.ego;

import com.ego.pojo.Goods;
import com.ego.result.BaseResult;
import com.ego.service.GoodsServiceI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jick on 2019/4/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class GoodsServiceITest {
    @Autowired
    private GoodsServiceI goodsService;

    @Test
    public void testRedis() {
      BaseResult  result = goodsService. selectGoodsList(new Goods(), 1, 10);
        System.out.println(result);
    }




}
