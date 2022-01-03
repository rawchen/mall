package com.rawchen.mall.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rawchen.common.base.BaseService;
import com.rawchen.mall.product.mapper.CommentReplayMapper;
import com.rawchen.mall.product.entity.CommentReplay;

/**
 * 商品评价回复关系 [CommentReplayService]服务实现.
 * Created by RawChen on 2021-12-30.
 **/
@Service
@Transactional(readOnly = false)
public class CommentReplayService extends BaseService<CommentReplayMapper, CommentReplay> {
	
}