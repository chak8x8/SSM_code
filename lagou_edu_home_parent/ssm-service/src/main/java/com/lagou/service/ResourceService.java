package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;

import java.util.List;

public interface ResourceService {

    /*
        资源分页以及多条片查询
     */
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo);
}
