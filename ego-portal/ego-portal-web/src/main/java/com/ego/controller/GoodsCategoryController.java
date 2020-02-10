package com.ego.controller;

import com.ego.service.GoodsCategoryServiceI;
import com.ego.vo.GoodsCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品分类controller
 */
@Controller
@RequestMapping("/goodsCategory")
public class GoodsCategoryController {

    @Autowired
    private GoodsCategoryServiceI goodsCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<GoodsCategoryVo> selectCategoryList() {
        return goodsCategoryService.selectCategoryList();
    }

}
