package com.rawchen.mall.coupon.controller;

import com.rawchen.mall.coupon.entity.SkuFullReduction;
import com.rawchen.mall.coupon.service.SkuFullReductionService;
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
 * [商品满减信息]控制器
 * Created by RawChen on 2021-11-22.
 **/
@RestController
@RequestMapping(value = "/api/v1/skuFullReduction")
@Api(tags = "[PC端]商品满减信息接口",  value = "SkuFullReductionController")
public class SkuFullReductionController extends BaseController {

	@Autowired
	private SkuFullReductionService skuFullReductionService;

	/**
	 * 商品满减信息列表
	 */
	@ApiOperation(value = "商品满减信息列表", notes = "商品满减信息列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "skuId", value = "spu_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "fullPrice", value = "满多少", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "reducePrice", value = "减多少", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "addOther", value = "是否参与其他优惠", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore SkuFullReduction skuFullReduction) {
		List<SkuFullReduction> list = skuFullReductionService.findList(skuFullReduction);
		return Result.ok(list);
	}

	/**
	 * 商品满减信息分页列表
	 */
	@ApiOperation(value = "商品满减信息分页列表", notes = "商品满减信息分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "skuId", value = "spu_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "fullPrice", value = "满多少", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "reducePrice", value = "减多少", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "addOther", value = "是否参与其他优惠", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore SkuFullReduction skuFullReduction, HttpServletRequest request, HttpServletResponse response) {
		Page<SkuFullReduction> page = skuFullReductionService.findPage(new Page<SkuFullReduction>(request, response), skuFullReduction);
		return Result.ok(page);
	}

	/**
	 * 商品满减信息详情
	 */
	@ApiOperation(value = "商品满减信息详情", notes = "根据id获取商品满减信息详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "商品满减信息id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		SkuFullReduction skuFullReduction = skuFullReductionService.get(id);
		return Result.ok(skuFullReduction);
	}

	/**
	 * 商品满减信息保存
	 */
	@ApiOperation(value = "商品满减信息保存", notes = "商品满减信息如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody SkuFullReduction skuFullReduction) {
		try {
			skuFullReductionService.insert(skuFullReduction);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * 商品满减信息修改
	 */
	@ApiOperation(value = "商品满减信息修改", notes = "商品满减信息修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody SkuFullReduction skuFullReduction) {
		try {
			skuFullReductionService.update(skuFullReduction);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * 商品满减信息删除
	 */
	@ApiOperation(value = "商品满减信息删除", notes = "商品满减信息删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "商品满减信息id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			skuFullReductionService.deleteAll(ids, new SkuFullReduction());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}