package com.rawchen.mall.product.controller;

import com.rawchen.mall.product.entity.SpuInfo;
import com.rawchen.mall.product.service.SpuInfoService;
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
 * [spu信息]控制器
 * Created by RawChen on 2021-12-30.
 **/
@RestController
@RequestMapping(value = "/spuInfo")
@Api(tags = "[PC端]spu信息接口",  value = "SpuInfoController")
public class SpuInfoController extends BaseController {

	@Autowired
	private SpuInfoService spuInfoService;

	/**
	 * spu信息列表
	 */
	@ApiOperation(value = "spu信息列表", notes = "spu信息列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "spuName", value = "商品名称", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "spuDescription", value = "商品描述", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "catalogId", value = "所属分类id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "brandId", value = "品牌id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "publishStatus", value = "上架状态[0 - 下架，1 - 上架]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore SpuInfo spuInfo) {
		List<SpuInfo> list = spuInfoService.findList(spuInfo);
		return Result.ok(list);
	}

	/**
	 * spu信息分页列表
	 */
	@ApiOperation(value = "spu信息分页列表", notes = "spu信息分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "spuName", value = "商品名称", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "spuDescription", value = "商品描述", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "catalogId", value = "所属分类id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "brandId", value = "品牌id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "publishStatus", value = "上架状态[0 - 下架，1 - 上架]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore SpuInfo spuInfo, HttpServletRequest request, HttpServletResponse response) {
		Page<SpuInfo> page = spuInfoService.findPage(new Page<SpuInfo>(request, response), spuInfo);
		return Result.ok(page);
	}

	/**
	 * spu信息详情
	 */
	@ApiOperation(value = "spu信息详情", notes = "根据id获取spu信息详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "spu信息id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		SpuInfo spuInfo = spuInfoService.get(id);
		return Result.ok(spuInfo);
	}

	/**
	 * spu信息保存
	 */
	@ApiOperation(value = "spu信息保存", notes = "spu信息如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody SpuInfo spuInfo) {
		try {
			spuInfoService.insert(spuInfo);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * spu信息修改
	 */
	@ApiOperation(value = "spu信息修改", notes = "spu信息修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody SpuInfo spuInfo) {
		try {
			spuInfoService.update(spuInfo);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * spu信息删除
	 */
	@ApiOperation(value = "spu信息删除", notes = "spu信息删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "spu信息id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			spuInfoService.deleteAll(ids, new SpuInfo());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
