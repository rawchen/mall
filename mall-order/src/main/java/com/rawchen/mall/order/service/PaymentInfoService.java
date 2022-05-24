package com.rawchen.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rawchen.common.utils.PageUtils;
import com.rawchen.mall.order.entity.PaymentInfoEntity;

import java.util.Map;

/**
 * 支付信息表
 *
 * @author rawchen
 * @email rawchen@qq.com
 * @date 2021-12-30 00:54:56
 */
public interface PaymentInfoService extends IService<PaymentInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

