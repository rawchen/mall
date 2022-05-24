package com.rawchen.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rawchen.common.utils.PageUtils;
import com.rawchen.mall.order.entity.OrderReturnReasonEntity;

import java.util.Map;

/**
 * 退货原因
 *
 * @author rawchen
 * @email rawchen@qq.com
 * @date 2021-12-30 00:54:56
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

