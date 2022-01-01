package com.rawchen.mall.coupon.controller;

import com.rawchen.mall.coupon.entity.HomeSubject;
import com.rawchen.mall.coupon.service.HomeSubjectService;
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
 * [首页专题]控制器
 * Created by RawChen on 2021-11-22.
 **/
@RestController
@RequestMapping(value = "/homeSubject")
@Api(tags = "[PC端]首页专题接口",  value = "HomeSubjectController")
public class HomeSubjectController extends BaseController {

	@Autowired
	private HomeSubjectService homeSubjectService;

	/**
	 * 首页专题列表
	 */
	@ApiOperation(value = "首页专题列表", notes = "首页专题列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "name", value = "专题名字", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "title", value = "专题标题", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "subTitle", value = "专题副标题", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "status", value = "显示状态", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "url", value = "详情连接", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "sort", value = "排序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "img", value = "专题图片地址", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore HomeSubject homeSubject) {
		List<HomeSubject> list = homeSubjectService.findList(homeSubject);
		return Result.ok(list);
	}

	/**
	 * 首页专题分页列表
	 */
	@ApiOperation(value = "首页专题分页列表", notes = "首页专题分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "name", value = "专题名字", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "title", value = "专题标题", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "subTitle", value = "专题副标题", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "status", value = "显示状态", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "url", value = "详情连接", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "sort", value = "排序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "img", value = "专题图片地址", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore HomeSubject homeSubject, HttpServletRequest request, HttpServletResponse response) {
		Page<HomeSubject> page = homeSubjectService.findPage(new Page<HomeSubject>(request, response), homeSubject);
		return Result.ok(page);
	}

	/**
	 * 首页专题详情
	 */
	@ApiOperation(value = "首页专题详情", notes = "根据id获取首页专题详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "首页专题id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		HomeSubject homeSubject = homeSubjectService.get(id);
		return Result.ok(homeSubject);
	}

	/**
	 * 首页专题保存
	 */
	@ApiOperation(value = "首页专题保存", notes = "首页专题如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody HomeSubject homeSubject) {
		try {
			homeSubjectService.insert(homeSubject);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * 首页专题修改
	 */
	@ApiOperation(value = "首页专题修改", notes = "首页专题修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody HomeSubject homeSubject) {
		try {
			homeSubjectService.update(homeSubject);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * 首页专题删除
	 */
	@ApiOperation(value = "首页专题删除", notes = "首页专题删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "首页专题id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			homeSubjectService.deleteAll(ids, new HomeSubject());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
