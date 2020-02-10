package com.ego.service;

import com.ego.pojo.GoodsCategory;
import com.ego.result.BaseResult;
import com.ego.vo.GoodsCategoryVo;

import java.util.List;

/**
 * Created by jick on 2019/3/28.
 */
public interface GoodsCategoryServiceI {
    List<GoodsCategory>   selectTopCategory();
    List<GoodsCategory>   selectCategoryGetByParentId(Short parentId);
    BaseResult categotySave(GoodsCategory goodsCategory);
    List<GoodsCategoryVo>    selectCategoryListFroView();
    //查询所有分类给商品列表使用
    List<GoodsCategory>  selectCategoryListForGoodsList();
    BaseResult categoryDelete(Short id);
    BaseResult categoryListDelete(Short[] ids);
    GoodsCategory   categoryGetById(Short id);
    BaseResult   categoryUpdate(GoodsCategory  goodsCategory);
}
