package com.rawchen.mall.coupon.controller;

import com.rawchen.mall.coupon.entity.MemberPrice;
import com.rawchen.mall.coupon.service.MemberPriceService;
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
 * [商品会员价格]控制器
 * Created by RawChen on 2021-11-22.
 **/
@RestController
@RequestMapping(value = "/api/v1/memberPrice")
@Api(tags = "[PC端]商品会员价格接口",  value = "MemberPriceController")
public class MemberPriceController extends BaseController {

	@Autowired
	private MemberPriceService memberPriceService;

	/**
	 * 商品会员价格列表
	 */
	@ApiOperation(value = "商品会员价格列表", notes = "商品会员价格列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "skuId", value = "sku_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "memberLevelId", value = "会员等级id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "memberLevelName", value = "会员等级名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "memberPrice", value = "会员对应价格", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "addOther", value = "可否叠加其他优惠[0-不可叠加优惠，1-可叠加]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore MemberPrice memberPrice) {
		List<MemberPrice> list = memberPriceService.findList(memberPrice);
		return Result.ok(list);
	}

	/**
	 * 商品会员价格分页列表
	 */
	@ApiOperation(value = "商品会员价格分页列表", notes = "商品会员价格分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "skuId", value = "sku_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "memberLevelId", value = "会员等级id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "memberLevelName", value = "会员等级名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "memberPrice", value = "会员对应价格", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "addOther", value = "可否叠加其他优惠[0-不可叠加优惠，1-可叠加]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore MemberPrice memberPrice, HttpServletRequest request, HttpServletResponse response) {
		Page<MemberPrice> page = memberPriceService.findPage(new Page<MemberPrice>(request, response), memberPrice);
		return Result.ok(page);
	}

	/**
	 * 商品会员价格详情
	 */
	@ApiOperation(value = "商品会员价格详情", notes = "根据id获取商品会员价格详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "商品会员价格id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		MemberPrice memberPrice = memberPriceService.get(id);
		return Result.ok(memberPrice);
	}

	/**
	 * 商品会员价格保存
	 */
	@ApiOperation(value = "商品会员价格保存", notes = "商品会员价格如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody MemberPrice memberPrice) {
		try {
			memberPriceService.insert(memberPrice);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * 商品会员价格修改
	 */
	@ApiOperation(value = "商品会员价格修改", notes = "商品会员价格修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody MemberPrice memberPrice) {
		try {
			memberPriceService.update(memberPrice);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * 商品会员价格删除
	 */
	@ApiOperation(value = "商品会员价格删除", notes = "商品会员价格删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "商品会员价格id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			memberPriceService.deleteAll(ids, new MemberPrice());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
