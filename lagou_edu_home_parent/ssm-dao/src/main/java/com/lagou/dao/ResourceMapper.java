package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;

import java.util.List;

public interface ResourceMapper {

    /*
        资源分页以及多条片查询
     */
    public List<Resource> findAllResourceByPage(ResourceVo resourceVo);
}
