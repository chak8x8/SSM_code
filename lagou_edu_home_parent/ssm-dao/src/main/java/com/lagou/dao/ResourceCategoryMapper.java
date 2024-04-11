package com.lagou.dao;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryMapper {

    /*
        查詢所有資源分类
     */
    public List<ResourceCategory> findAllResource();
}
