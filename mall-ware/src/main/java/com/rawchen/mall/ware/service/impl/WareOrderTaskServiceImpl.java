package com.rawchen.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rawchen.common.utils.PageUtils;
import com.rawchen.common.utils.Query;
import com.rawchen.mall.ware.dao.WareOrderTaskDao;
import com.rawchen.mall.ware.entity.WareOrderTaskEntity;
import com.rawchen.mall.ware.service.WareOrderTaskService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("wareOrderTaskService")
public class WareOrderTaskServiceImpl extends ServiceImpl<WareOrderTaskDao, WareOrderTaskEntity> implements WareOrderTaskService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WareOrderTaskEntity> page = this.page(
                new Query<WareOrderTaskEntity>().getPage(params),
                new QueryWrapper<WareOrderTaskEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public WareOrderTaskEntity getOrderTaskByOrderSn(String orderSn) {

		return this.getOne(new QueryWrapper<WareOrderTaskEntity>().eq("order_sn", orderSn));
	}

}