package com.rawchen.mall.product.controller;

import com.rawchen.mall.product.entity.SkuInfo;
import com.rawchen.mall.product.service.SkuInfoService;
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
 * [sku信息]控制器
 * Created by RawChen on 2021-12-30.
 **/
@RestController
@RequestMapping(value = "/skuInfo")
@Api(tags = "[PC端]sku信息接口",  value = "SkuInfoController")
public class SkuInfoController extends BaseController {

	@Autowired
	private SkuInfoService skuInfoService;

	/**
	 * sku信息列表
	 */
	@ApiOperation(value = "sku信息列表", notes = "sku信息列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "spuId", value = "spuid", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "skuCode", value = "", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "skuName", value = "sku名称", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "skuDesc", value = "sku介绍描述", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "catalogId", value = "所属分类id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "brandId", value = "品牌id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "skuDefaultImg", value = "默认图片", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "skuTitle", value = "标题", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "skuSubtitle", value = "副标题", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "price", value = "价格", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "weight", value = "重量", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore SkuInfo skuInfo) {
		List<SkuInfo> list = skuInfoService.findList(skuInfo);
		return Result.ok(list);
	}

	/**
	 * sku信息分页列表
	 */
	@ApiOperation(value = "sku信息分页列表", notes = "sku信息分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "spuId", value = "spuid", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "skuCode", value = "", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "skuName", value = "sku名称", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "skuDesc", value = "sku介绍描述", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "catalogId", value = "所属分类id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "brandId", value = "品牌id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "skuDefaultImg", value = "默认图片", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "skuTitle", value = "标题", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "skuSubtitle", value = "副标题", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "price", value = "价格", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "weight", value = "重量", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore SkuInfo skuInfo, HttpServletRequest request, HttpServletResponse response) {
		Page<SkuInfo> page = skuInfoService.findPage(new Page<SkuInfo>(request, response), skuInfo);
		return Result.ok(page);
	}

	/**
	 * sku信息详情
	 */
	@ApiOperation(value = "sku信息详情", notes = "根据id获取sku信息详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "sku信息id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		SkuInfo skuInfo = skuInfoService.get(id);
		return Result.ok(skuInfo);
	}

	/**
	 * sku信息保存
	 */
	@ApiOperation(value = "sku信息保存", notes = "sku信息如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody SkuInfo skuInfo) {
		try {
			skuInfoService.insert(skuInfo);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * sku信息修改
	 */
	@ApiOperation(value = "sku信息修改", notes = "sku信息修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody SkuInfo skuInfo) {
		try {
			skuInfoService.update(skuInfo);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * sku信息删除
	 */
	@ApiOperation(value = "sku信息删除", notes = "sku信息删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "sku信息id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			skuInfoService.deleteAll(ids, new SkuInfo());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
