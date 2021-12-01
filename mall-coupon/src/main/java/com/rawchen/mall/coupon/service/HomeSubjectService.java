package com.rawchen.mall.coupon.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rawchen.common.base.BaseService;
import com.rawchen.mall.coupon.mapper.HomeSubjectMapper;
import com.rawchen.mall.coupon.entity.HomeSubject;

/**
 * 首页专题表 [HomeSubjectService]服务实现.
 * Created by RawChen on 2021-11-22.
 **/
@Service
@Transactional(readOnly = false)
public class HomeSubjectService extends BaseService<HomeSubjectMapper, HomeSubject> {
	
}