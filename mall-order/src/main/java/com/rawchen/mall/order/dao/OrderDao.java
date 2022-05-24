package com.rawchen.mall.order.dao;

import com.rawchen.mall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 订单
 * 
 * @author rawchen
 * @email rawchen@qq.com
 * @date 2021-12-30 00:54:56
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {

	void updateOrderStatus(@Param("orderSn") String orderSn, @Param("code") Integer code);
}
