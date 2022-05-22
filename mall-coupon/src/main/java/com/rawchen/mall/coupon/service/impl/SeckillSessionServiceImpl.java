package com.rawchen.mall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rawchen.common.utils.PageUtils;
import com.rawchen.common.utils.Query;
import com.rawchen.mall.coupon.dao.SeckillSessionDao;
import com.rawchen.mall.coupon.entity.SeckillSessionEntity;
import com.rawchen.mall.coupon.entity.SeckillSkuRelationEntity;
import com.rawchen.mall.coupon.service.SeckillSessionService;
import com.rawchen.mall.coupon.service.SeckillSkuRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("seckillSessionService")
public class SeckillSessionServiceImpl extends ServiceImpl<SeckillSessionDao, SeckillSessionEntity> implements SeckillSessionService {

	@Autowired
	private SeckillSkuRelationService skuRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillSessionEntity> page = this.page(
                new Query<SeckillSessionEntity>().getPage(params),
                new QueryWrapper<SeckillSessionEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public List<SeckillSessionEntity> getLate3DaySession() {
    	// 计算最近三天的时间，现在到
		List<SeckillSessionEntity> list = this.list(new QueryWrapper<SeckillSessionEntity>().between("start_time", startTime(), endTime()));
		System.out.println("SeckillSessionEntitySize: " + list.size());
		if(list != null && list.size() > 0){
			return list.stream().map(session -> {
				// 给每一个活动写入他们的秒杀项
				Long id = session.getId();
				List<SeckillSkuRelationEntity> entities = skuRelationService.list(new QueryWrapper<SeckillSkuRelationEntity>().eq("promotion_session_id", id));
				session.setRelationSkus(entities);
				return session;
			}).collect(Collectors.toList());
		}
		return null;
	}

	private String startTime(){
		LocalDateTime start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
		return start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	private String endTime(){
		LocalDate acquired = LocalDate.now().plusDays(2);
		return LocalDateTime.of(acquired, LocalTime.MAX).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}