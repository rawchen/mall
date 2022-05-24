package com.rawchen.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rawchen.common.utils.PageUtils;
import com.rawchen.mall.order.entity.OrderOperateHistoryEntity;

import java.util.Map;

/**
 * 订单操作历史记录
 *
 * @author rawchen
 * @email rawchen@qq.com
 * @date 2021-12-30 00:54:56
 */
public interface OrderOperateHistoryService extends IService<OrderOperateHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

