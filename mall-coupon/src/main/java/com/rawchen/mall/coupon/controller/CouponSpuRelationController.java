package com.rawchen.mall.coupon.controller;

import com.rawchen.mall.coupon.entity.CouponSpuRelation;
import com.rawchen.mall.coupon.service.CouponSpuRelationService;
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
 * [优惠券与产品关联]控制器
 * Created by RawChen on 2021-11-22.
 **/
@RestController
@RequestMapping(value = "/api/v1/couponSpuRelation")
@Api(tags = "[PC端]优惠券与产品关联接口",  value = "CouponSpuRelationController")
public class CouponSpuRelationController extends BaseController {

	@Autowired
	private CouponSpuRelationService couponSpuRelationService;

	/**
	 * 优惠券与产品关联列表
	 */
	@ApiOperation(value = "优惠券与产品关联列表", notes = "优惠券与产品关联列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "couponId", value = "优惠券id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "spuId", value = "spu_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "spuName", value = "spu_name", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore CouponSpuRelation couponSpuRelation) {
		List<CouponSpuRelation> list = couponSpuRelationService.findList(couponSpuRelation);
		return Result.ok(list);
	}

	/**
	 * 优惠券与产品关联分页列表
	 */
	@ApiOperation(value = "优惠券与产品关联分页列表", notes = "优惠券与产品关联分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "couponId", value = "优惠券id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "spuId", value = "spu_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "spuName", value = "spu_name", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore CouponSpuRelation couponSpuRelation, HttpServletRequest request, HttpServletResponse response) {
		Page<CouponSpuRelation> page = couponSpuRelationService.findPage(new Page<CouponSpuRelation>(request, response), couponSpuRelation);
		return Result.ok(page);
	}

	/**
	 * 优惠券与产品关联详情
	 */
	@ApiOperation(value = "优惠券与产品关联详情", notes = "根据id获取优惠券与产品关联详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "优惠券与产品关联id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		CouponSpuRelation couponSpuRelation = couponSpuRelationService.get(id);
		return Result.ok(couponSpuRelation);
	}

	/**
	 * 优惠券与产品关联保存
	 */
	@ApiOperation(value = "优惠券与产品关联保存", notes = "优惠券与产品关联如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody CouponSpuRelation couponSpuRelation) {
		try {
			couponSpuRelationService.insert(couponSpuRelation);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * 优惠券与产品关联修改
	 */
	@ApiOperation(value = "优惠券与产品关联修改", notes = "优惠券与产品关联修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody CouponSpuRelation couponSpuRelation) {
		try {
			couponSpuRelationService.update(couponSpuRelation);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * 优惠券与产品关联删除
	 */
	@ApiOperation(value = "优惠券与产品关联删除", notes = "优惠券与产品关联删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "优惠券与产品关联id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			couponSpuRelationService.deleteAll(ids, new CouponSpuRelation());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
