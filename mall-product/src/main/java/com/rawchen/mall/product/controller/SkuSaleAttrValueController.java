package com.rawchen.mall.product.controller;

import com.rawchen.mall.product.entity.SkuSaleAttrValue;
import com.rawchen.mall.product.service.SkuSaleAttrValueService;
import com.rawchen.common.base.BaseController;
import com.rawchen.common.entity.Result;
import com.rawchen.common.entity.Page;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * [sku销售属性&值]控制器
 * Created by RawChen on 2021-12-30.
 **/
@RestController
@RequestMapping(value = "/skuSaleAttrValue")
@Api(tags = "[PC端]sku销售属性&值接口",  value = "SkuSaleAttrValueController")
public class SkuSaleAttrValueController extends BaseController {

	@Autowired
	private SkuSaleAttrValueService skuSaleAttrValueService;

	/**
	 * sku销售属性&值列表
	 */
	@ApiOperation(value = "sku销售属性&值列表", notes = "sku销售属性&值列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "skuId", value = "sku_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "attrId", value = "attr_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "attrName", value = "销售属性名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "attrValue", value = "销售属性值", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "attrSort", value = "顺序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore SkuSaleAttrValue skuSaleAttrValue) {
		List<SkuSaleAttrValue> list = skuSaleAttrValueService.findList(skuSaleAttrValue);
		return Result.ok(list);
	}

	/**
	 * sku销售属性&值分页列表
	 */
	@ApiOperation(value = "sku销售属性&值分页列表", notes = "sku销售属性&值分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "skuId", value = "sku_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "attrId", value = "attr_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "attrName", value = "销售属性名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "attrValue", value = "销售属性值", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "attrSort", value = "顺序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore SkuSaleAttrValue skuSaleAttrValue, HttpServletRequest request, HttpServletResponse response) {
		Page<SkuSaleAttrValue> page = skuSaleAttrValueService.findPage(new Page<SkuSaleAttrValue>(request, response), skuSaleAttrValue);
		return Result.ok(page);
	}

	/**
	 * sku销售属性&值详情
	 */
	@ApiOperation(value = "sku销售属性&值详情", notes = "根据id获取sku销售属性&值详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "sku销售属性&值id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		SkuSaleAttrValue skuSaleAttrValue = skuSaleAttrValueService.get(id);
		return Result.ok(skuSaleAttrValue);
	}

	/**
	 * sku销售属性&值保存
	 */
	@ApiOperation(value = "sku销售属性&值保存", notes = "sku销售属性&值如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody SkuSaleAttrValue skuSaleAttrValue) {
		try {
			skuSaleAttrValueService.insert(skuSaleAttrValue);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * sku销售属性&值修改
	 */
	@ApiOperation(value = "sku销售属性&值修改", notes = "sku销售属性&值修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody SkuSaleAttrValue skuSaleAttrValue) {
		try {
			skuSaleAttrValueService.update(skuSaleAttrValue);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * sku销售属性&值删除
	 */
	@ApiOperation(value = "sku销售属性&值删除", notes = "sku销售属性&值删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "sku销售属性&值id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			skuSaleAttrValueService.deleteAll(ids, new SkuSaleAttrValue());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
