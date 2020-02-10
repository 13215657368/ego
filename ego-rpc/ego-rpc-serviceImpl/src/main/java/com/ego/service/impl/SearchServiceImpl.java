package com.ego.service.impl;

import com.ego.result.EgoPageInfo;
import com.ego.service.SearchServiceI;
import com.ego.vo.GoodsVo;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @see(功能介绍) : 搜索商品服务
 * @version(版本号) : 1.0
 * @author(创建人) : Dylan
 * @since : JDK 1.8
 */
public class SearchServiceImpl implements SearchServiceI {

    @Autowired
    private TransportClient client;

    /**
     * 商品搜索
     *
     * @param searchStr
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public EgoPageInfo<GoodsVo> doSearch(String searchStr, Integer pageNum, Integer pageSize) {
        SearchRequestBuilder builder = client.prepareSearch("ego").setTypes("goods");
        // 设置分页
        builder.setFrom((pageNum - 1) * pageSize).setSize(pageSize);
        // 设置高亮前缀
        builder.setHighlighterPreTags("<font style='color:red;'>");
        // 设置高亮后缀
        builder.setHighlighterPostTags("</font>");
        // 设置高亮字段
        builder.addHighlightedField("goodsName");
        // 查询所有
        builder.setQuery(QueryBuilders.multiMatchQuery(searchStr, "goodsName"));
        // 开始查询
        SearchResponse response = builder.get();
        // 获取命中数据
        SearchHits hits = response.getHits();
        // 循环获取数据
        List<GoodsVo> goodsVoList = new ArrayList();
        for (SearchHit searchHit : hits) {
            if (0 >= hits.getHits().length)
                return new EgoPageInfo<GoodsVo>(pageNum, pageSize, 0);

            // 查询高亮数据
            String highLightMessage = searchHit.getHighlightFields().get("goodsName").fragments()[0].toString();
            // 初始化高亮对象
            GoodsVo goodsVo = new GoodsVo();
            goodsVo.setGoodsId(Integer.parseInt(searchHit.getId()));
            goodsVo.setGoodsName(String.valueOf(searchHit.getSource().get("goodsName")));
            goodsVo.setGoodsHlName(highLightMessage);
            goodsVo.setMarketPrice(new BigDecimal(String.valueOf(searchHit.getSource().get("marketPrice"))));
            goodsVo.setOriginalImg(String.valueOf(searchHit.getSource().get("originalImg")));
            // 添加至集合
            goodsVoList.add(goodsVo);
        }

        // 获取总条数
        Long total = hits.getTotalHits();
        // 构建分页对象
        EgoPageInfo<GoodsVo> pageInfo = new EgoPageInfo<>(pageNum, pageSize, total.intValue());
        pageInfo.setResult(goodsVoList);
        return pageInfo;
    }

}
