package com.rawchen.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rawchen.common.utils.PageUtils;
import com.rawchen.mall.ware.entity.WareInfoEntity;
import com.rawchen.mall.ware.vo.FareVo;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author rawchen
 * @email rawchen@qq.com
 * @date 2022-01-06 21:09:28
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

	/**
	 * 根据收获地址计算运费
	 */
	FareVo getFare(Long addrId);
}

