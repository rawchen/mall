package com.rawchen.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rawchen.common.utils.PageUtils;
import com.rawchen.mall.coupon.entity.SeckillSessionEntity;

import java.util.List;
import java.util.Map;

/**
 * 秒杀活动场次
 *
 * @author rawchen
 * @email rawchen@qq.com
 * @date 2021-12-30 00:57:53
 */
public interface SeckillSessionService extends IService<SeckillSessionEntity> {

    PageUtils queryPage(Map<String, Object> params);

	List<SeckillSessionEntity> getLate3DaySession();
}

