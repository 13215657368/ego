package com.ego;

import com.ego.result.BaseResult;
import com.ego.service.GoodsCategoryServiceI;
import com.ego.util.JsonUtil;
import com.ego.vo.GoodsCategoryVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
/*
* @ContextConfiguration Spring 整合 JUnit4 测试时，使用注解引入多个配置文件
*  单个文件 @ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
*  多个文件 @ContextConfiguration(locations = {"classpath:spring/spring1.xml",
"classpath:mybatis/config.xml"})
*/
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class GoodsCategoryServiceITest {
    @Autowired
    private GoodsCategoryServiceI goodsCategoryService;

    /**
     * 测试查询所有商品分类
     */
    @Test
    public void testSelectCategoryListFroView() {
        List<GoodsCategoryVo> gcvList = goodsCategoryService.selectCategoryListFroView();
        System.out.println("数据");
        System.out.println(JsonUtil.object2JsonStr(gcvList));
    }



    @Test
    public void testCategoryListDelete() {
        Short[] ids = new Short[]{844, 845, 846};
        BaseResult result = goodsCategoryService.categoryListDelete(ids);
        System.out.println(result);
    }





}