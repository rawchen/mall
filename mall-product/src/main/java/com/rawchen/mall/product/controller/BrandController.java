package com.rawchen.mall.product.controller;

import com.rawchen.mall.product.entity.Brand;
import com.rawchen.mall.product.service.BrandService;
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
 * [品牌]控制器
 * Created by RawChen on 2021-12-30.
 **/
@RestController
@RequestMapping(value = "/brand")
@Api(tags = "[PC端]品牌接口",  value = "BrandController")
public class BrandController extends BaseController {

	@Autowired
	private BrandService brandService;

	/**
	 * 品牌列表
	 */
	@ApiOperation(value = "品牌列表", notes = "品牌列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "name", value = "品牌名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "logo", value = "品牌logo地址", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "descript", value = "介绍", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "showStatus", value = "显示状态[0-不显示；1-显示]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "firstLetter", value = "检索首字母", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "sort", value = "排序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore Brand brand) {
		List<Brand> list = brandService.findList(brand);
		return Result.ok(list);
	}

	/**
	 * 品牌分页列表
	 */
	@ApiOperation(value = "品牌分页列表", notes = "品牌分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "name", value = "品牌名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "logo", value = "品牌logo地址", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "descript", value = "介绍", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "showStatus", value = "显示状态[0-不显示；1-显示]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "firstLetter", value = "检索首字母", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "sort", value = "排序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore Brand brand, HttpServletRequest request, HttpServletResponse response) {
		Page<Brand> page = brandService.findPage(new Page<Brand>(request, response), brand);
		return Result.ok(page);
	}

	/**
	 * 品牌详情
	 */
	@ApiOperation(value = "品牌详情", notes = "根据id获取品牌详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "品牌id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		Brand brand = brandService.get(id);
		return Result.ok(brand);
	}

	/**
	 * 品牌保存
	 */
	@ApiOperation(value = "品牌保存", notes = "品牌如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody Brand brand) {
		try {
			brandService.insert(brand);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * 品牌修改
	 */
	@ApiOperation(value = "品牌修改", notes = "品牌修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody Brand brand) {
		try {
			brandService.update(brand);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * 品牌删除
	 */
	@ApiOperation(value = "品牌删除", notes = "品牌删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "品牌id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			brandService.deleteAll(ids, new Brand());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
