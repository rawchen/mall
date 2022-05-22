package com.rawchen.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rawchen.common.utils.PageUtils;
import com.rawchen.mall.ware.entity.PurchaseEntity;
import com.rawchen.mall.ware.vo.MergeVo;
import com.rawchen.mall.ware.vo.PurchaseDoneVo;

import java.util.List;
import java.util.Map;

/**
 * 采购信息
 *
 * @author rawchen
 * @email rawchen@qq.com
 * @date 2022-01-06 21:09:28
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

	PageUtils queryPageUnreceivePurchase(Map<String, Object> params);

	void mergePurchase(MergeVo mergeVo);

	void received(List<Long> ids);

	void done(PurchaseDoneVo doneVo);
}

