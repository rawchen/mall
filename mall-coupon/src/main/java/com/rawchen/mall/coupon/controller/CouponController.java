package com.rawchen.mall.coupon.controller;

import com.rawchen.mall.coupon.entity.Coupon;
import com.rawchen.mall.coupon.service.CouponService;
import com.rawchen.common.base.BaseController;
import com.rawchen.common.entity.Result;
import com.rawchen.common.entity.Page;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * [优惠券信息]控制器
 * Created by RawChen on 2021-11-22.
 **/
@RefreshScope
@RestController
@RequestMapping(value = "/coupon")
@Api(tags = "[PC端]优惠券信息接口",  value = "CouponController")
public class CouponController extends BaseController {

	@Autowired
	private CouponService couponService;

	@Value("${coupon.user.name}")
	private String name;

	@Value("${coupon.user.age}")
	private Integer age;

	@GetMapping("/test")
	public Result test() {
		return Result.ok(new HashMap() {{
			put("name", name);
			put("age", age);
		}});
	}

	/**
	 * 优惠券信息列表
	 */
	@ApiOperation(value = "优惠券信息列表", notes = "优惠券信息列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "couponType", value = "优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "couponImg", value = "优惠券图片", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "couponName", value = "优惠卷名字", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "num", value = "数量", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "amount", value = "金额", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "perLimit", value = "每人限领张数", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "minPoint", value = "使用门槛", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "startTime", value = "开始时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "endTime", value = "结束时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "useType", value = "使用类型[0->全场通用；1->指定分类；2->指定商品]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "note", value = "备注", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "publishCount", value = "发行数量", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "useCount", value = "已使用数量", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "receiveCount", value = "领取数量", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "enableStartTime", value = "可以领取的开始日期", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "enableEndTime", value = "可以领取的结束日期", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "code", value = "优惠码", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "memberLevel", value = "可以领取的会员等级[0->不限等级，其他-对应等级]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "publish", value = "发布状态[0-未发布，1-已发布]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore Coupon coupon) {
		List<Coupon> list = couponService.findList(coupon);
		return Result.ok(list);
	}

	/**
	 * 优惠券信息分页列表
	 */
	@ApiOperation(value = "优惠券信息分页列表", notes = "优惠券信息分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "couponType", value = "优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "couponImg", value = "优惠券图片", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "couponName", value = "优惠卷名字", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "num", value = "数量", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "amount", value = "金额", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "perLimit", value = "每人限领张数", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "minPoint", value = "使用门槛", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "startTime", value = "开始时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "endTime", value = "结束时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "useType", value = "使用类型[0->全场通用；1->指定分类；2->指定商品]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "note", value = "备注", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "publishCount", value = "发行数量", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "useCount", value = "已使用数量", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "receiveCount", value = "领取数量", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "enableStartTime", value = "可以领取的开始日期", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "enableEndTime", value = "可以领取的结束日期", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "code", value = "优惠码", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "memberLevel", value = "可以领取的会员等级[0->不限等级，其他-对应等级]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "publish", value = "发布状态[0-未发布，1-已发布]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore Coupon coupon, HttpServletRequest request, HttpServletResponse response) {
		Page<Coupon> page = couponService.findPage(new Page<Coupon>(request, response), coupon);
		return Result.ok(page);
	}

	/**
	 * 优惠券信息详情
	 */
	@ApiOperation(value = "优惠券信息详情", notes = "根据id获取优惠券信息详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "优惠券信息id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		Coupon coupon = couponService.get(id);
		return Result.ok(coupon);
	}

	/**
	 * 优惠券信息保存
	 */
	@ApiOperation(value = "优惠券信息保存", notes = "优惠券信息如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody Coupon coupon) {
		try {
			couponService.insert(coupon);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * 优惠券信息修改
	 */
	@ApiOperation(value = "优惠券信息修改", notes = "优惠券信息修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody Coupon coupon) {
		try {
			couponService.update(coupon);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * 优惠券信息删除
	 */
	@ApiOperation(value = "优惠券信息删除", notes = "优惠券信息删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "优惠券信息id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			couponService.deleteAll(ids, new Coupon());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
