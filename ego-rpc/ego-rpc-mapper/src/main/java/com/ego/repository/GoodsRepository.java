package com.ego.repository;

import com.ego.vo.GoodsVo;
import org.springframework.data.repository.Repository;

// ElasticsearchRepository默认自带部分方法
public interface GoodsRepository extends Repository<GoodsVo, Integer> {
}
