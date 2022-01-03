package com.rawchen.mall.product.controller;

import com.rawchen.mall.product.entity.AttrGroup;
import com.rawchen.mall.product.service.AttrGroupService;
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
 * [属性分组]控制器
 * Created by RawChen on 2021-12-30.
 **/
@RestController
@RequestMapping(value = "/attrGroup")
@Api(tags = "[PC端]属性分组接口",  value = "AttrGroupController")
public class AttrGroupController extends BaseController {

	@Autowired
	private AttrGroupService attrGroupService;

	/**
	 * 属性分组列表
	 */
	@ApiOperation(value = "属性分组列表", notes = "属性分组列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "attrGroupName", value = "组名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "sort", value = "排序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "descript", value = "描述", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "icon", value = "组图标", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "catelogId", value = "所属分类id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore AttrGroup attrGroup) {
		List<AttrGroup> list = attrGroupService.findList(attrGroup);
		return Result.ok(list);
	}

	/**
	 * 属性分组分页列表
	 */
	@ApiOperation(value = "属性分组分页列表", notes = "属性分组分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "attrGroupName", value = "组名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "sort", value = "排序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "descript", value = "描述", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "icon", value = "组图标", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "catelogId", value = "所属分类id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore AttrGroup attrGroup, HttpServletRequest request, HttpServletResponse response) {
		Page<AttrGroup> page = attrGroupService.findPage(new Page<AttrGroup>(request, response), attrGroup);
		return Result.ok(page);
	}

	/**
	 * 属性分组详情
	 */
	@ApiOperation(value = "属性分组详情", notes = "根据id获取属性分组详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "属性分组id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		AttrGroup attrGroup = attrGroupService.get(id);
		return Result.ok(attrGroup);
	}

	/**
	 * 属性分组保存
	 */
	@ApiOperation(value = "属性分组保存", notes = "属性分组如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody AttrGroup attrGroup) {
		try {
			attrGroupService.insert(attrGroup);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * 属性分组修改
	 */
	@ApiOperation(value = "属性分组修改", notes = "属性分组修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody AttrGroup attrGroup) {
		try {
			attrGroupService.update(attrGroup);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * 属性分组删除
	 */
	@ApiOperation(value = "属性分组删除", notes = "属性分组删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "属性分组id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			attrGroupService.deleteAll(ids, new AttrGroup());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
