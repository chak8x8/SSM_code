package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    /*
       分页查询广告讯息
    */
    public List<PromotionAd> findAllPromotionAdByPage();

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
    public void updatePromotionAdStatus(PromotionAd promotionAd);
}
