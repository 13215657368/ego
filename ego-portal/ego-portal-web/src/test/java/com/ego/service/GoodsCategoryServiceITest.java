package com.ego.service;

import com.ego.util.JsonUtil;
import com.ego.vo.GoodsCategoryVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by jick on 2019/4/6.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class GoodsCategoryServiceITest {
    @Autowired
    private   GoodsCategoryServiceI  goodsCategoryService;


    @Test
    public   void   testSelectCategoryList(){
        List<GoodsCategoryVo> list = goodsCategoryService.selectCategoryList();
        System.out.println(JsonUtil.object2JsonStr(list));
    }
}
