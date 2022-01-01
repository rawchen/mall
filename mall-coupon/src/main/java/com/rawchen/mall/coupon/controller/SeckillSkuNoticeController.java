package com.rawchen.mall.coupon.controller;

import com.rawchen.mall.coupon.entity.SeckillSkuNotice;
import com.rawchen.mall.coupon.service.SeckillSkuNoticeService;
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
 * [秒杀商品通知订阅]控制器
 * Created by RawChen on 2021-11-22.
 **/
@RestController
@RequestMapping(value = "/seckillSkuNotice")
@Api(tags = "[PC端]秒杀商品通知订阅接口",  value = "SeckillSkuNoticeController")
public class SeckillSkuNoticeController extends BaseController {

	@Autowired
	private SeckillSkuNoticeService seckillSkuNoticeService;

	/**
	 * 秒杀商品通知订阅列表
	 */
	@ApiOperation(value = "秒杀商品通知订阅列表", notes = "秒杀商品通知订阅列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "memberId", value = "member_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "skuId", value = "sku_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "sessionId", value = "活动场次id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "subcribeTime", value = "订阅时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "sendTime", value = "发送时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "noticeType", value = "通知方式[0-短信，1-邮件]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore SeckillSkuNotice seckillSkuNotice) {
		List<SeckillSkuNotice> list = seckillSkuNoticeService.findList(seckillSkuNotice);
		return Result.ok(list);
	}

	/**
	 * 秒杀商品通知订阅分页列表
	 */
	@ApiOperation(value = "秒杀商品通知订阅分页列表", notes = "秒杀商品通知订阅分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "memberId", value = "member_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "skuId", value = "sku_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "sessionId", value = "活动场次id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "subcribeTime", value = "订阅时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "sendTime", value = "发送时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "noticeType", value = "通知方式[0-短信，1-邮件]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore SeckillSkuNotice seckillSkuNotice, HttpServletRequest request, HttpServletResponse response) {
		Page<SeckillSkuNotice> page = seckillSkuNoticeService.findPage(new Page<SeckillSkuNotice>(request, response), seckillSkuNotice);
		return Result.ok(page);
	}

	/**
	 * 秒杀商品通知订阅详情
	 */
	@ApiOperation(value = "秒杀商品通知订阅详情", notes = "根据id获取秒杀商品通知订阅详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "秒杀商品通知订阅id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		SeckillSkuNotice seckillSkuNotice = seckillSkuNoticeService.get(id);
		return Result.ok(seckillSkuNotice);
	}

	/**
	 * 秒杀商品通知订阅保存
	 */
	@ApiOperation(value = "秒杀商品通知订阅保存", notes = "秒杀商品通知订阅如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody SeckillSkuNotice seckillSkuNotice) {
		try {
			seckillSkuNoticeService.insert(seckillSkuNotice);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * 秒杀商品通知订阅修改
	 */
	@ApiOperation(value = "秒杀商品通知订阅修改", notes = "秒杀商品通知订阅修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody SeckillSkuNotice seckillSkuNotice) {
		try {
			seckillSkuNoticeService.update(seckillSkuNotice);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * 秒杀商品通知订阅删除
	 */
	@ApiOperation(value = "秒杀商品通知订阅删除", notes = "秒杀商品通知订阅删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "秒杀商品通知订阅id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			seckillSkuNoticeService.deleteAll(ids, new SeckillSkuNotice());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
