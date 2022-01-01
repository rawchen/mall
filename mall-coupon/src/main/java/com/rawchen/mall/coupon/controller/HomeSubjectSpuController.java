package com.rawchen.mall.coupon.controller;

import com.rawchen.mall.coupon.entity.HomeSubjectSpu;
import com.rawchen.mall.coupon.service.HomeSubjectSpuService;
import com.rawchen.common.base.BaseController;
import com.rawchen.common.entity.Result;
import com.rawchen.common.entity.Page;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * [专题商品]控制器
 * Created by RawChen on 2021-11-22.
 **/
@RestController
@RequestMapping(value = "/homeSubjectSpu")
@Api(tags = "[PC端]专题商品接口",  value = "HomeSubjectSpuController")
public class HomeSubjectSpuController extends BaseController {

	@Autowired
	private HomeSubjectSpuService homeSubjectSpuService;

	/**
	 * 专题商品列表
	 */
	@ApiOperation(value = "专题商品列表", notes = "专题商品列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "name", value = "专题名字", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "subjectId", value = "专题id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "spuId", value = "spu_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "sort", value = "排序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore HomeSubjectSpu homeSubjectSpu) {
		List<HomeSubjectSpu> list = homeSubjectSpuService.findList(homeSubjectSpu);
		return Result.ok(list);
	}

	/**
	 * 专题商品分页列表
	 */
	@ApiOperation(value = "专题商品分页列表", notes = "专题商品分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "name", value = "专题名字", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "subjectId", value = "专题id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "spuId", value = "spu_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "sort", value = "排序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore HomeSubjectSpu homeSubjectSpu, HttpServletRequest request, HttpServletResponse response) {
		Page<HomeSubjectSpu> page = homeSubjectSpuService.findPage(new Page<HomeSubjectSpu>(request, response), homeSubjectSpu);
		return Result.ok(page);
	}

	/**
	 * 专题商品详情
	 */
	@ApiOperation(value = "专题商品详情", notes = "根据id获取专题商品详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "专题商品id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		HomeSubjectSpu homeSubjectSpu = homeSubjectSpuService.get(id);
		return Result.ok(homeSubjectSpu);
	}

	/**
	 * 专题商品保存
	 */
	@ApiOperation(value = "专题商品保存", notes = "专题商品如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody HomeSubjectSpu homeSubjectSpu) {
		try {
			homeSubjectSpuService.insert(homeSubjectSpu);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * 专题商品修改
	 */
	@ApiOperation(value = "专题商品修改", notes = "专题商品修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody HomeSubjectSpu homeSubjectSpu) {
		try {
			homeSubjectSpuService.update(homeSubjectSpu);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * 专题商品删除
	 */
	@ApiOperation(value = "专题商品删除", notes = "专题商品删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "专题商品id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			homeSubjectSpuService.deleteAll(ids, new HomeSubjectSpu());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
