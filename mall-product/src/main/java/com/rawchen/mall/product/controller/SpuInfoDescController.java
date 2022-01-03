package com.rawchen.mall.product.controller;

import com.rawchen.mall.product.entity.SpuInfoDesc;
import com.rawchen.mall.product.service.SpuInfoDescService;
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
 * [spu信息介绍]控制器
 * Created by RawChen on 2021-12-30.
 **/
@RestController
@RequestMapping(value = "/spuInfoDesc")
@Api(tags = "[PC端]spu信息介绍接口",  value = "SpuInfoDescController")
public class SpuInfoDescController extends BaseController {

	@Autowired
	private SpuInfoDescService spuInfoDescService;

	/**
	 * spu信息介绍列表
	 */
	@ApiOperation(value = "spu信息介绍列表", notes = "spu信息介绍列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "decript", value = "商品介绍", required = false, dataType = "String")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore SpuInfoDesc spuInfoDesc) {
		List<SpuInfoDesc> list = spuInfoDescService.findList(spuInfoDesc);
		return Result.ok(list);
	}

	/**
	 * spu信息介绍分页列表
	 */
	@ApiOperation(value = "spu信息介绍分页列表", notes = "spu信息介绍分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "decript", value = "商品介绍", required = false, dataType = "String")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore SpuInfoDesc spuInfoDesc, HttpServletRequest request, HttpServletResponse response) {
		Page<SpuInfoDesc> page = spuInfoDescService.findPage(new Page<SpuInfoDesc>(request, response), spuInfoDesc);
		return Result.ok(page);
	}

	/**
	 * spu信息介绍详情
	 */
	@ApiOperation(value = "spu信息介绍详情", notes = "根据id获取spu信息介绍详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "spu信息介绍id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		SpuInfoDesc spuInfoDesc = spuInfoDescService.get(id);
		return Result.ok(spuInfoDesc);
	}

	/**
	 * spu信息介绍保存
	 */
	@ApiOperation(value = "spu信息介绍保存", notes = "spu信息介绍如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody SpuInfoDesc spuInfoDesc) {
		try {
			spuInfoDescService.insert(spuInfoDesc);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * spu信息介绍修改
	 */
	@ApiOperation(value = "spu信息介绍修改", notes = "spu信息介绍修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody SpuInfoDesc spuInfoDesc) {
		try {
			spuInfoDescService.update(spuInfoDesc);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * spu信息介绍删除
	 */
	@ApiOperation(value = "spu信息介绍删除", notes = "spu信息介绍删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "spu信息介绍id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			spuInfoDescService.deleteAll(ids, new SpuInfoDesc());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
