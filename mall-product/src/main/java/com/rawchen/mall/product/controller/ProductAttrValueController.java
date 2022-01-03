package com.rawchen.mall.product.controller;

import com.rawchen.mall.product.entity.ProductAttrValue;
import com.rawchen.mall.product.service.ProductAttrValueService;
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
 * [spu属性值]控制器
 * Created by RawChen on 2021-12-30.
 **/
@RestController
@RequestMapping(value = "/productAttrValue")
@Api(tags = "[PC端]spu属性值接口",  value = "ProductAttrValueController")
public class ProductAttrValueController extends BaseController {

	@Autowired
	private ProductAttrValueService productAttrValueService;

	/**
	 * spu属性值列表
	 */
	@ApiOperation(value = "spu属性值列表", notes = "spu属性值列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "spuId", value = "商品id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "attrId", value = "属性id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "attrName", value = "属性名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "attrValue", value = "属性值", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "attrSort", value = "顺序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "quickShow", value = "快速展示【是否展示在介绍上；0-否 1-是】", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore ProductAttrValue productAttrValue) {
		List<ProductAttrValue> list = productAttrValueService.findList(productAttrValue);
		return Result.ok(list);
	}

	/**
	 * spu属性值分页列表
	 */
	@ApiOperation(value = "spu属性值分页列表", notes = "spu属性值分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "spuId", value = "商品id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "attrId", value = "属性id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "attrName", value = "属性名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "attrValue", value = "属性值", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "attrSort", value = "顺序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "quickShow", value = "快速展示【是否展示在介绍上；0-否 1-是】", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore ProductAttrValue productAttrValue, HttpServletRequest request, HttpServletResponse response) {
		Page<ProductAttrValue> page = productAttrValueService.findPage(new Page<ProductAttrValue>(request, response), productAttrValue);
		return Result.ok(page);
	}

	/**
	 * spu属性值详情
	 */
	@ApiOperation(value = "spu属性值详情", notes = "根据id获取spu属性值详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "spu属性值id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		ProductAttrValue productAttrValue = productAttrValueService.get(id);
		return Result.ok(productAttrValue);
	}

	/**
	 * spu属性值保存
	 */
	@ApiOperation(value = "spu属性值保存", notes = "spu属性值如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody ProductAttrValue productAttrValue) {
		try {
			productAttrValueService.insert(productAttrValue);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * spu属性值修改
	 */
	@ApiOperation(value = "spu属性值修改", notes = "spu属性值修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody ProductAttrValue productAttrValue) {
		try {
			productAttrValueService.update(productAttrValue);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * spu属性值删除
	 */
	@ApiOperation(value = "spu属性值删除", notes = "spu属性值删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "spu属性值id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			productAttrValueService.deleteAll(ids, new ProductAttrValue());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
