package com.rawchen.mall.ware.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rawchen.common.utils.PageUtils;
import com.rawchen.common.utils.Query;
import com.rawchen.common.utils.R;
import com.rawchen.mall.ware.dao.WareInfoDao;
import com.rawchen.mall.ware.entity.WareInfoEntity;
import com.rawchen.mall.ware.feign.MemberFeignService;
import com.rawchen.mall.ware.service.WareInfoService;
import com.rawchen.mall.ware.vo.FareVo;
import com.rawchen.mall.ware.vo.MemberAddressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;

@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity> implements WareInfoService {

	@Autowired
	private MemberFeignService memberFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
		QueryWrapper<WareInfoEntity> wrapper = new QueryWrapper<>();

		String key = (String) params.get("key");
		if(!StringUtils.isEmpty(key)){
			wrapper.eq("id", key).or().like("name",key).or().like("address", key).or().like("areacode", key);
		}
		IPage<WareInfoEntity> page = this.page(
                new Query<WareInfoEntity>().getPage(params),
				wrapper
        );
        return new PageUtils(page);
    }

	@Override
	public FareVo getFare(Long addrId) {

		R info = memberFeignService.addrInfo(addrId);
		FareVo fareVo = new FareVo();
		MemberAddressVo addressVo = info.getData("memberReceiveAddress", new TypeReference<MemberAddressVo>() {});
		fareVo.setMemberAddressVo(addressVo);
		if(addressVo != null){
			String phone = addressVo.getPhone();
			if(phone == null || phone.length() < 2){
				phone = new Random().nextInt(100) + "";
			}
			BigDecimal decimal = new BigDecimal(phone.substring(phone.length() - 1));
			fareVo.setFare(decimal);
		}else{
			fareVo.setFare(new BigDecimal("20"));
		}
		return fareVo;
	}
}