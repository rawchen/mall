package com.rawchen.mall.member.dao;

import com.rawchen.mall.member.entity.MemberLevelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员等级
 * 
 * @author rawchen
 * @email rawchen@qq.com
 * @date 2021-12-30 00:49:16
 */
@Mapper
public interface MemberLevelDao extends BaseMapper<MemberLevelEntity> {

	MemberLevelEntity getDefaultLevel();
}
