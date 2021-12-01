package com.rawchen.mall.coupon.controller;

import com.rawchen.mall.coupon.entity.SkuBounds;
import com.rawchen.mall.coupon.service.SkuBoundsService;
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
 * [商品sku积分设置]控制器
 * Created by RawChen on 2021-11-22.
 **/
@RestController
@RequestMapping(value = "/api/v1/skuBounds")
@Api(tags = "[PC端]商品sku积分设置接口",  value = "SkuBoundsController")
public class SkuBoundsController extends BaseController {

	@Autowired
	private SkuBoundsService skuBoundsService;

	/**
	 * 商品sku积分设置列表
	 */
	@ApiOperation(value = "商品sku积分设置列表", notes = "商品sku积分设置列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "skuId", value = "", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "growBounds", value = "成长积分", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "buyBounds", value = "购物积分", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "work", value = "优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore SkuBounds skuBounds) {
		List<SkuBounds> list = skuBoundsService.findList(skuBounds);
		return Result.ok(list);
	}

	/**
	 * 商品sku积分设置分页列表
	 */
	@ApiOperation(value = "商品sku积分设置分页列表", notes = "商品sku积分设置分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "skuId", value = "", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "growBounds", value = "成长积分", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "buyBounds", value = "购物积分", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "work", value = "优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore SkuBounds skuBounds, HttpServletRequest request, HttpServletResponse response) {
		Page<SkuBounds> page = skuBoundsService.findPage(new Page<SkuBounds>(request, response), skuBounds);
		return Result.ok(page);
	}

	/**
	 * 商品sku积分设置详情
	 */
	@ApiOperation(value = "商品sku积分设置详情", notes = "根据id获取商品sku积分设置详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "商品sku积分设置id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		SkuBounds skuBounds = skuBoundsService.get(id);
		return Result.ok(skuBounds);
	}

	/**
	 * 商品sku积分设置保存
	 */
	@ApiOperation(value = "商品sku积分设置保存", notes = "商品sku积分设置如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody SkuBounds skuBounds) {
		try {
			skuBoundsService.insert(skuBounds);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * 商品sku积分设置修改
	 */
	@ApiOperation(value = "商品sku积分设置修改", notes = "商品sku积分设置修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody SkuBounds skuBounds) {
		try {
			skuBoundsService.update(skuBounds);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * 商品sku积分设置删除
	 */
	@ApiOperation(value = "商品sku积分设置删除", notes = "商品sku积分设置删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "商品sku积分设置id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			skuBoundsService.deleteAll(ids, new SkuBounds());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
