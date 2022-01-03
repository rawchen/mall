package com.rawchen.mall.product.controller;

import com.rawchen.common.base.BaseController;
import com.rawchen.common.entity.Page;
import com.rawchen.common.entity.Result;
import com.rawchen.mall.product.entity.Category;
import com.rawchen.mall.product.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * [商品三级分类]控制器
 * Created by RawChen on 2021-12-30.
 **/
@RestController
@RequestMapping(value = "/category")
@Api(tags = "[PC端]商品三级分类接口",  value = "CategoryController")
public class CategoryController extends BaseController {

	@Autowired
	private CategoryService categoryService;


	@RequestMapping(value = "/list/tree", method = RequestMethod.GET)
	public Result listTree() {
		List<Category> categories = categoryService.listWithTree();
		return Result.ok(categories);
	}

	/**
	 * 商品三级分类列表
	 */
	@ApiOperation(value = "商品三级分类列表", notes = "商品三级分类列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "name", value = "分类名称", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "parentCid", value = "父分类id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "catLevel", value = "层级", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "showStatus", value = "是否显示[0-不显示，1显示]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "sort", value = "排序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "icon", value = "图标地址", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "productUnit", value = "计量单位", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "productCount", value = "商品数量", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore Category category) {
		List<Category> list = categoryService.findList(category);
		return Result.ok(list);
	}

	/**
	 * 商品三级分类分页列表
	 */
	@ApiOperation(value = "商品三级分类分页列表", notes = "商品三级分类分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "name", value = "分类名称", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "parentCid", value = "父分类id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "catLevel", value = "层级", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "showStatus", value = "是否显示[0-不显示，1显示]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "sort", value = "排序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "icon", value = "图标地址", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "productUnit", value = "计量单位", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "productCount", value = "商品数量", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore Category category, HttpServletRequest request, HttpServletResponse response) {
		Page<Category> page = categoryService.findPage(new Page<Category>(request, response), category);
		return Result.ok(page);
	}

	/**
	 * 商品三级分类详情
	 */
	@ApiOperation(value = "商品三级分类详情", notes = "根据id获取商品三级分类详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "商品三级分类id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		Category category = categoryService.get(id);
		return Result.ok(category);
	}

	/**
	 * 商品三级分类保存
	 */
	@ApiOperation(value = "商品三级分类保存", notes = "商品三级分类如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody Category category) {
		try {
			categoryService.insert(category);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * 商品三级分类修改
	 */
	@ApiOperation(value = "商品三级分类修改", notes = "商品三级分类修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody Category category) {
		try {
			categoryService.update(category);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * 商品三级分类删除
	 */
	@ApiOperation(value = "商品三级分类删除", notes = "商品三级分类删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "商品三级分类id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			categoryService.deleteAll(ids, new Category());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
