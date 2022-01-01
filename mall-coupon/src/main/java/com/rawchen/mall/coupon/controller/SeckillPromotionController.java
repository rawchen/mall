package com.rawchen.mall.coupon.controller;

import com.rawchen.mall.coupon.entity.SeckillPromotion;
import com.rawchen.mall.coupon.service.SeckillPromotionService;
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
 * [秒杀活动]控制器
 * Created by RawChen on 2021-11-22.
 **/
@RestController
@RequestMapping(value = "/seckillPromotion")
@Api(tags = "[PC端]秒杀活动接口",  value = "SeckillPromotionController")
public class SeckillPromotionController extends BaseController {

	@Autowired
	private SeckillPromotionService seckillPromotionService;

	/**
	 * 秒杀活动列表
	 */
	@ApiOperation(value = "秒杀活动列表", notes = "秒杀活动列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "title", value = "活动标题", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "startTime", value = "开始日期", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "endTime", value = "结束日期", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "status", value = "上下线状态", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "userId", value = "创建人", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore SeckillPromotion seckillPromotion) {
		List<SeckillPromotion> list = seckillPromotionService.findList(seckillPromotion);
		return Result.ok(list);
	}

	/**
	 * 秒杀活动分页列表
	 */
	@ApiOperation(value = "秒杀活动分页列表", notes = "秒杀活动分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "title", value = "活动标题", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "startTime", value = "开始日期", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "endTime", value = "结束日期", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "status", value = "上下线状态", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "userId", value = "创建人", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore SeckillPromotion seckillPromotion, HttpServletRequest request, HttpServletResponse response) {
		Page<SeckillPromotion> page = seckillPromotionService.findPage(new Page<SeckillPromotion>(request, response), seckillPromotion);
		return Result.ok(page);
	}

	/**
	 * 秒杀活动详情
	 */
	@ApiOperation(value = "秒杀活动详情", notes = "根据id获取秒杀活动详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "秒杀活动id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		SeckillPromotion seckillPromotion = seckillPromotionService.get(id);
		return Result.ok(seckillPromotion);
	}

	/**
	 * 秒杀活动保存
	 */
	@ApiOperation(value = "秒杀活动保存", notes = "秒杀活动如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody SeckillPromotion seckillPromotion) {
		try {
			seckillPromotionService.insert(seckillPromotion);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * 秒杀活动修改
	 */
	@ApiOperation(value = "秒杀活动修改", notes = "秒杀活动修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody SeckillPromotion seckillPromotion) {
		try {
			seckillPromotionService.update(seckillPromotion);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * 秒杀活动删除
	 */
	@ApiOperation(value = "秒杀活动删除", notes = "秒杀活动删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "秒杀活动id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			seckillPromotionService.deleteAll(ids, new SeckillPromotion());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
