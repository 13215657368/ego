package com.ego.service.impl;

import com.ego.mapper.BrandMapper;
import com.ego.pojo.Brand;
import com.ego.pojo.BrandExample;
import com.ego.service.BrandServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jick on 2019/3/30.
 */
@Service

public class BrandServiceImpl  implements BrandServiceI {

    @Autowired
    private BrandMapper  brandMapper;

    //查询所有的商品品牌
    @Override
    public List<Brand> selectBrandList() {
        return brandMapper.selectByExample(new BrandExample());
    }
}
