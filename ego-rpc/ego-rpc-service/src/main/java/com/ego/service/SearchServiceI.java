package com.ego.service;

import com.ego.result.EgoPageInfo;
import com.ego.vo.GoodsVo;

/**
 * @see(功能介绍) : 搜索商品服务
 * @version(版本号) : 1.0
 * @author(创建人) : Dylan
 * @since : JDK 1.8
 */
public interface SearchServiceI {

    /**
     * 搜索商品
     *
     * @param searchStr
     * @param pageNum
     * @param pageSize
     * @return
     */
    EgoPageInfo<GoodsVo> doSearch(String searchStr, Integer pageNum, Integer pageSize);

}
