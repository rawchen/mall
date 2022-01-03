package com.rawchen.mall.product.controller;

import com.rawchen.mall.product.entity.Attr;
import com.rawchen.mall.product.service.AttrService;
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
 * [商品属性]控制器
 * Created by RawChen on 2021-12-30.
 **/
@RestController
@RequestMapping(value = "/attr")
@Api(tags = "[PC端]商品属性接口",  value = "AttrController")
public class AttrController extends BaseController {

	@Autowired
	private AttrService attrService;

	/**
	 * 商品属性列表
	 */
	@ApiOperation(value = "商品属性列表", notes = "商品属性列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "attrName", value = "属性名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "searchType", value = "是否需要检索[0-不需要，1-需要]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "valueType", value = "值类型[0-为单个值，1-可以选择多个值]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "icon", value = "属性图标", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "valueSelect", value = "可选值列表[用逗号分隔]", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "attrType", value = "属性类型[0-销售属性，1-基本属性]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "enable", value = "启用状态[0 - 禁用，1 - 启用]", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "catelogId", value = "所属分类", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "showDesc", value = "快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore Attr attr) {
		List<Attr> list = attrService.findList(attr);
		return Result.ok(list);
	}

	/**
	 * 商品属性分页列表
	 */
	@ApiOperation(value = "商品属性分页列表", notes = "商品属性分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "attrName", value = "属性名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "searchType", value = "是否需要检索[0-不需要，1-需要]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "valueType", value = "值类型[0-为单个值，1-可以选择多个值]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "icon", value = "属性图标", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "valueSelect", value = "可选值列表[用逗号分隔]", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "attrType", value = "属性类型[0-销售属性，1-基本属性]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "enable", value = "启用状态[0 - 禁用，1 - 启用]", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "catelogId", value = "所属分类", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "showDesc", value = "快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore Attr attr, HttpServletRequest request, HttpServletResponse response) {
		Page<Attr> page = attrService.findPage(new Page<Attr>(request, response), attr);
		return Result.ok(page);
	}

	/**
	 * 商品属性详情
	 */
	@ApiOperation(value = "商品属性详情", notes = "根据id获取商品属性详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "商品属性id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		Attr attr = attrService.get(id);
		return Result.ok(attr);
	}

	/**
	 * 商品属性保存
	 */
	@ApiOperation(value = "商品属性保存", notes = "商品属性如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody Attr attr) {
		try {
			attrService.insert(attr);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * 商品属性修改
	 */
	@ApiOperation(value = "商品属性修改", notes = "商品属性修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody Attr attr) {
		try {
			attrService.update(attr);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * 商品属性删除
	 */
	@ApiOperation(value = "商品属性删除", notes = "商品属性删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "商品属性id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			attrService.deleteAll(ids, new Attr());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
