package com.rawchen.mall.coupon.controller;

import com.rawchen.mall.coupon.entity.HomeAdv;
import com.rawchen.mall.coupon.service.HomeAdvService;
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
 * [首页轮播广告]控制器
 * Created by RawChen on 2021-11-22.
 **/
@RestController
@RequestMapping(value = "/api/v1/homeAdv")
@Api(tags = "[PC端]首页轮播广告接口",  value = "HomeAdvController")
public class HomeAdvController extends BaseController {

	@Autowired
	private HomeAdvService homeAdvService;

	/**
	 * 首页轮播广告列表
	 */
	@ApiOperation(value = "首页轮播广告列表", notes = "首页轮播广告列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "name", value = "名字", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pic", value = "图片地址", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "startTime", value = "开始时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "endTime", value = "结束时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "status", value = "状态", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "clickCount", value = "点击数", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "url", value = "广告详情连接地址", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "note", value = "备注", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "sort", value = "排序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "publisherId", value = "发布者", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "authId", value = "审核者", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore HomeAdv homeAdv) {
		List<HomeAdv> list = homeAdvService.findList(homeAdv);
		return Result.ok(list);
	}

	/**
	 * 首页轮播广告分页列表
	 */
	@ApiOperation(value = "首页轮播广告分页列表", notes = "首页轮播广告分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "name", value = "名字", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pic", value = "图片地址", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "startTime", value = "开始时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "endTime", value = "结束时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "status", value = "状态", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "clickCount", value = "点击数", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "url", value = "广告详情连接地址", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "note", value = "备注", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "sort", value = "排序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "publisherId", value = "发布者", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "authId", value = "审核者", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore HomeAdv homeAdv, HttpServletRequest request, HttpServletResponse response) {
		Page<HomeAdv> page = homeAdvService.findPage(new Page<HomeAdv>(request, response), homeAdv);
		return Result.ok(page);
	}

	/**
	 * 首页轮播广告详情
	 */
	@ApiOperation(value = "首页轮播广告详情", notes = "根据id获取首页轮播广告详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "首页轮播广告id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		HomeAdv homeAdv = homeAdvService.get(id);
		return Result.ok(homeAdv);
	}

	/**
	 * 首页轮播广告保存
	 */
	@ApiOperation(value = "首页轮播广告保存", notes = "首页轮播广告如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody HomeAdv homeAdv) {
		try {
			homeAdvService.insert(homeAdv);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * 首页轮播广告修改
	 */
	@ApiOperation(value = "首页轮播广告修改", notes = "首页轮播广告修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody HomeAdv homeAdv) {
		try {
			homeAdvService.update(homeAdv);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * 首页轮播广告删除
	 */
	@ApiOperation(value = "首页轮播广告删除", notes = "首页轮播广告删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "首页轮播广告id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			homeAdvService.deleteAll(ids, new HomeAdv());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
