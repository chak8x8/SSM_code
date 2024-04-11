package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;

import java.lang.reflect.InvocationTargetException;

public interface PromotionAdService {

    /*
      分页查询广告讯息
   */
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVo promotionAdVo);

    /*
       新增广告讯息
     */
    public void savePromotionAd(PromotionAd promotionAd);

    /*
       更新广告讯息
     */
    public void updatePromotionAd(PromotionAd promotionAd);

    /*
        广告动态上下线
     */
    public void updatePromotionAdStatus(int id, int status);
}
