package com.lagou.service;


import com.lagou.domain.Test;

import java.util.List;

public interface TestService {

    /*
        对Text表进行查询所有操作
     */
    public List<Test> findAllTest();
}
