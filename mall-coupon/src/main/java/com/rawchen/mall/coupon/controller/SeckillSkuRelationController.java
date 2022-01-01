package com.rawchen.mall.coupon.controller;

import com.rawchen.mall.coupon.entity.SeckillSkuRelation;
import com.rawchen.mall.coupon.service.SeckillSkuRelationService;
import com.rawchen.common.base.BaseController;
import com.rawchen.common.entity.Result;
import com.rawchen.common.entity.Page;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * [秒杀活动商品关联]控制器
 * Created by RawChen on 2021-11-22.
 **/
@RestController
@RequestMapping(value = "/seckillSkuRelation")
@Api(tags = "[PC端]秒杀活动商品关联接口",  value = "SeckillSkuRelationController")
public class SeckillSkuRelationController extends BaseController {

	@Autowired
	private SeckillSkuRelationService seckillSkuRelationService;

	/**
	 * 秒杀活动商品关联列表
	 */
	@ApiOperation(value = "秒杀活动商品关联列表", notes = "秒杀活动商品关联列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "promotionId", value = "活动id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "promotionSessionId", value = "活动场次id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "skuId", value = "商品id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "seckillPrice", value = "秒杀价格", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "seckillCount", value = "秒杀总量", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "seckillLimit", value = "每人限购数量", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "seckillSort", value = "排序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore SeckillSkuRelation seckillSkuRelation) {
		List<SeckillSkuRelation> list = seckillSkuRelationService.findList(seckillSkuRelation);
		return Result.ok(list);
	}

	/**
	 * 秒杀活动商品关联分页列表
	 */
	@ApiOperation(value = "秒杀活动商品关联分页列表", notes = "秒杀活动商品关联分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "promotionId", value = "活动id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "promotionSessionId", value = "活动场次id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "skuId", value = "商品id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "seckillPrice", value = "秒杀价格", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "seckillCount", value = "秒杀总量", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "seckillLimit", value = "每人限购数量", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "seckillSort", value = "排序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore SeckillSkuRelation seckillSkuRelation, HttpServletRequest request, HttpServletResponse response) {
		Page<SeckillSkuRelation> page = seckillSkuRelationService.findPage(new Page<SeckillSkuRelation>(request, response), seckillSkuRelation);
		return Result.ok(page);
	}

	/**
	 * 秒杀活动商品关联详情
	 */
	@ApiOperation(value = "秒杀活动商品关联详情", notes = "根据id获取秒杀活动商品关联详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "秒杀活动商品关联id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		SeckillSkuRelation seckillSkuRelation = seckillSkuRelationService.get(id);
		return Result.ok(seckillSkuRelation);
	}

	/**
	 * 秒杀活动商品关联保存
	 */
	@ApiOperation(value = "秒杀活动商品关联保存", notes = "秒杀活动商品关联如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody SeckillSkuRelation seckillSkuRelation) {
		try {
			seckillSkuRelationService.insert(seckillSkuRelation);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * 秒杀活动商品关联修改
	 */
	@ApiOperation(value = "秒杀活动商品关联修改", notes = "秒杀活动商品关联修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody SeckillSkuRelation seckillSkuRelation) {
		try {
			seckillSkuRelationService.update(seckillSkuRelation);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * 秒杀活动商品关联删除
	 */
	@ApiOperation(value = "秒杀活动商品关联删除", notes = "秒杀活动商品关联删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "秒杀活动商品关联id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			seckillSkuRelationService.deleteAll(ids, new SeckillSkuRelation());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
