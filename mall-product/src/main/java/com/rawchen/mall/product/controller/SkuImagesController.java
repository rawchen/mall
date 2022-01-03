package com.rawchen.mall.product.controller;

import com.rawchen.mall.product.entity.SkuImages;
import com.rawchen.mall.product.service.SkuImagesService;
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
 * [sku图片]控制器
 * Created by RawChen on 2021-12-30.
 **/
@RestController
@RequestMapping(value = "/skuImages")
@Api(tags = "[PC端]sku图片接口",  value = "SkuImagesController")
public class SkuImagesController extends BaseController {

	@Autowired
	private SkuImagesService skuImagesService;

	/**
	 * sku图片列表
	 */
	@ApiOperation(value = "sku图片列表", notes = "sku图片列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "skuId", value = "sku_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "imgUrl", value = "图片地址", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "imgSort", value = "排序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "defaultImg", value = "默认图[0 - 不是默认图，1 - 是默认图]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore SkuImages skuImages) {
		List<SkuImages> list = skuImagesService.findList(skuImages);
		return Result.ok(list);
	}

	/**
	 * sku图片分页列表
	 */
	@ApiOperation(value = "sku图片分页列表", notes = "sku图片分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "skuId", value = "sku_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "imgUrl", value = "图片地址", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "imgSort", value = "排序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "defaultImg", value = "默认图[0 - 不是默认图，1 - 是默认图]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore SkuImages skuImages, HttpServletRequest request, HttpServletResponse response) {
		Page<SkuImages> page = skuImagesService.findPage(new Page<SkuImages>(request, response), skuImages);
		return Result.ok(page);
	}

	/**
	 * sku图片详情
	 */
	@ApiOperation(value = "sku图片详情", notes = "根据id获取sku图片详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "sku图片id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		SkuImages skuImages = skuImagesService.get(id);
		return Result.ok(skuImages);
	}

	/**
	 * sku图片保存
	 */
	@ApiOperation(value = "sku图片保存", notes = "sku图片如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody SkuImages skuImages) {
		try {
			skuImagesService.insert(skuImages);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * sku图片修改
	 */
	@ApiOperation(value = "sku图片修改", notes = "sku图片修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody SkuImages skuImages) {
		try {
			skuImagesService.update(skuImages);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * sku图片删除
	 */
	@ApiOperation(value = "sku图片删除", notes = "sku图片删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "sku图片id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			skuImagesService.deleteAll(ids, new SkuImages());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
