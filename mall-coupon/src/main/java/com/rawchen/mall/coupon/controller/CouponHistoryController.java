package com.rawchen.mall.coupon.controller;

import com.rawchen.mall.coupon.entity.CouponHistory;
import com.rawchen.mall.coupon.service.CouponHistoryService;
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
 * [优惠券领取历史记录]控制器
 * Created by RawChen on 2021-11-22.
 **/
@RestController
@RequestMapping(value = "/couponHistory")
@Api(tags = "[PC端]优惠券领取历史记录接口",  value = "CouponHistoryController")
public class CouponHistoryController extends BaseController {

	@Autowired
	private CouponHistoryService couponHistoryService;

	/**
	 * 优惠券领取历史记录列表
	 */
	@ApiOperation(value = "优惠券领取历史记录列表", notes = "优惠券领取历史记录列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "couponId", value = "优惠券id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "memberId", value = "会员id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "memberNickName", value = "会员名字", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "getType", value = "获取方式[0->后台赠送；1->主动领取]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "useType", value = "使用状态[0->未使用；1->已使用；2->已过期]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "useTime", value = "使用时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "orderId", value = "订单id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "orderSn", value = "订单号", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore CouponHistory couponHistory) {
		List<CouponHistory> list = couponHistoryService.findList(couponHistory);
		return Result.ok(list);
	}

	/**
	 * 优惠券领取历史记录分页列表
	 */
	@ApiOperation(value = "优惠券领取历史记录分页列表", notes = "优惠券领取历史记录分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "couponId", value = "优惠券id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "memberId", value = "会员id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "memberNickName", value = "会员名字", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "getType", value = "获取方式[0->后台赠送；1->主动领取]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "useType", value = "使用状态[0->未使用；1->已使用；2->已过期]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "useTime", value = "使用时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "orderId", value = "订单id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "orderSn", value = "订单号", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore CouponHistory couponHistory, HttpServletRequest request, HttpServletResponse response) {
		Page<CouponHistory> page = couponHistoryService.findPage(new Page<CouponHistory>(request, response), couponHistory);
		return Result.ok(page);
	}

	/**
	 * 优惠券领取历史记录详情
	 */
	@ApiOperation(value = "优惠券领取历史记录详情", notes = "根据id获取优惠券领取历史记录详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "优惠券领取历史记录id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		CouponHistory couponHistory = couponHistoryService.get(id);
		return Result.ok(couponHistory);
	}

	/**
	 * 优惠券领取历史记录保存
	 */
	@ApiOperation(value = "优惠券领取历史记录保存", notes = "优惠券领取历史记录如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody CouponHistory couponHistory) {
		try {
			couponHistoryService.insert(couponHistory);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * 优惠券领取历史记录修改
	 */
	@ApiOperation(value = "优惠券领取历史记录修改", notes = "优惠券领取历史记录修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody CouponHistory couponHistory) {
		try {
			couponHistoryService.update(couponHistory);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * 优惠券领取历史记录删除
	 */
	@ApiOperation(value = "优惠券领取历史记录删除", notes = "优惠券领取历史记录删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "优惠券领取历史记录id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			couponHistoryService.deleteAll(ids, new CouponHistory());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
