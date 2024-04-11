package com.lagou.service;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResourceVo;

import java.util.List;

public interface ResourceCategoryService {

    /*
        查询所有分类讯息
     */
    public List<ResourceCategory> findAllResourceCategory();
}
