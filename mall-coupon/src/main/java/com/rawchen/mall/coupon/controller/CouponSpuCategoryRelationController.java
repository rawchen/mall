package com.rawchen.mall.coupon.controller;

import com.rawchen.mall.coupon.entity.CouponSpuCategoryRelation;
import com.rawchen.mall.coupon.service.CouponSpuCategoryRelationService;
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
 * [优惠券分类关联]控制器
 * Created by RawChen on 2021-11-22.
 **/
@RestController
@RequestMapping(value = "/couponSpuCategoryRelation")
@Api(tags = "[PC端]优惠券分类关联接口",  value = "CouponSpuCategoryRelationController")
public class CouponSpuCategoryRelationController extends BaseController {

	@Autowired
	private CouponSpuCategoryRelationService couponSpuCategoryRelationService;

	/**
	 * 优惠券分类关联列表
	 */
	@ApiOperation(value = "优惠券分类关联列表", notes = "优惠券分类关联列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "couponId", value = "优惠券id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "categoryId", value = "产品分类id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "categoryName", value = "产品分类名称", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore CouponSpuCategoryRelation couponSpuCategoryRelation) {
		List<CouponSpuCategoryRelation> list = couponSpuCategoryRelationService.findList(couponSpuCategoryRelation);
		return Result.ok(list);
	}

	/**
	 * 优惠券分类关联分页列表
	 */
	@ApiOperation(value = "优惠券分类关联分页列表", notes = "优惠券分类关联分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "couponId", value = "优惠券id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "categoryId", value = "产品分类id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "categoryName", value = "产品分类名称", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore CouponSpuCategoryRelation couponSpuCategoryRelation, HttpServletRequest request, HttpServletResponse response) {
		Page<CouponSpuCategoryRelation> page = couponSpuCategoryRelationService.findPage(new Page<CouponSpuCategoryRelation>(request, response), couponSpuCategoryRelation);
		return Result.ok(page);
	}

	/**
	 * 优惠券分类关联详情
	 */
	@ApiOperation(value = "优惠券分类关联详情", notes = "根据id获取优惠券分类关联详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "优惠券分类关联id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		CouponSpuCategoryRelation couponSpuCategoryRelation = couponSpuCategoryRelationService.get(id);
		return Result.ok(couponSpuCategoryRelation);
	}

	/**
	 * 优惠券分类关联保存
	 */
	@ApiOperation(value = "优惠券分类关联保存", notes = "优惠券分类关联如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody CouponSpuCategoryRelation couponSpuCategoryRelation) {
		try {
			couponSpuCategoryRelationService.insert(couponSpuCategoryRelation);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * 优惠券分类关联修改
	 */
	@ApiOperation(value = "优惠券分类关联修改", notes = "优惠券分类关联修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody CouponSpuCategoryRelation couponSpuCategoryRelation) {
		try {
			couponSpuCategoryRelationService.update(couponSpuCategoryRelation);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * 优惠券分类关联删除
	 */
	@ApiOperation(value = "优惠券分类关联删除", notes = "优惠券分类关联删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "优惠券分类关联id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			couponSpuCategoryRelationService.deleteAll(ids, new CouponSpuCategoryRelation());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
