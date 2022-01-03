package com.rawchen.mall.product.controller;

import com.rawchen.mall.product.entity.SpuImages;
import com.rawchen.mall.product.service.SpuImagesService;
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
 * [spu图片]控制器
 * Created by RawChen on 2021-12-30.
 **/
@RestController
@RequestMapping(value = "/spuImages")
@Api(tags = "[PC端]spu图片接口",  value = "SpuImagesController")
public class SpuImagesController extends BaseController {

	@Autowired
	private SpuImagesService spuImagesService;

	/**
	 * spu图片列表
	 */
	@ApiOperation(value = "spu图片列表", notes = "spu图片列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "spuId", value = "spu_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "imgName", value = "图片名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "imgUrl", value = "图片地址", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "imgSort", value = "顺序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "defaultImg", value = "是否默认图", required = false, dataType = "Integer")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore SpuImages spuImages) {
		List<SpuImages> list = spuImagesService.findList(spuImages);
		return Result.ok(list);
	}

	/**
	 * spu图片分页列表
	 */
	@ApiOperation(value = "spu图片分页列表", notes = "spu图片分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "spuId", value = "spu_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "imgName", value = "图片名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "imgUrl", value = "图片地址", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "imgSort", value = "顺序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "defaultImg", value = "是否默认图", required = false, dataType = "Integer")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore SpuImages spuImages, HttpServletRequest request, HttpServletResponse response) {
		Page<SpuImages> page = spuImagesService.findPage(new Page<SpuImages>(request, response), spuImages);
		return Result.ok(page);
	}

	/**
	 * spu图片详情
	 */
	@ApiOperation(value = "spu图片详情", notes = "根据id获取spu图片详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "spu图片id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		SpuImages spuImages = spuImagesService.get(id);
		return Result.ok(spuImages);
	}

	/**
	 * spu图片保存
	 */
	@ApiOperation(value = "spu图片保存", notes = "spu图片如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody SpuImages spuImages) {
		try {
			spuImagesService.insert(spuImages);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * spu图片修改
	 */
	@ApiOperation(value = "spu图片修改", notes = "spu图片修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody SpuImages spuImages) {
		try {
			spuImagesService.update(spuImages);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * spu图片删除
	 */
	@ApiOperation(value = "spu图片删除", notes = "spu图片删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "spu图片id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			spuImagesService.deleteAll(ids, new SpuImages());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
