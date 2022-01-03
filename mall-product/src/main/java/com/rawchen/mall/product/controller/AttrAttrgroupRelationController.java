package com.rawchen.mall.product.controller;

import com.rawchen.mall.product.entity.AttrAttrgroupRelation;
import com.rawchen.mall.product.service.AttrAttrgroupRelationService;
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
 * [属性&属性分组关联]控制器
 * Created by RawChen on 2021-12-30.
 **/
@RestController
@RequestMapping(value = "/attrAttrgroupRelation")
@Api(tags = "[PC端]属性&属性分组关联接口",  value = "AttrAttrgroupRelationController")
public class AttrAttrgroupRelationController extends BaseController {

	@Autowired
	private AttrAttrgroupRelationService attrAttrgroupRelationService;

	/**
	 * 属性&属性分组关联列表
	 */
	@ApiOperation(value = "属性&属性分组关联列表", notes = "属性&属性分组关联列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "attrId", value = "属性id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "attrGroupId", value = "属性分组id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "attrSort", value = "属性组内排序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore AttrAttrgroupRelation attrAttrgroupRelation) {
		List<AttrAttrgroupRelation> list = attrAttrgroupRelationService.findList(attrAttrgroupRelation);
		return Result.ok(list);
	}

	/**
	 * 属性&属性分组关联分页列表
	 */
	@ApiOperation(value = "属性&属性分组关联分页列表", notes = "属性&属性分组关联分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "attrId", value = "属性id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "attrGroupId", value = "属性分组id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "attrSort", value = "属性组内排序", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore AttrAttrgroupRelation attrAttrgroupRelation, HttpServletRequest request, HttpServletResponse response) {
		Page<AttrAttrgroupRelation> page = attrAttrgroupRelationService.findPage(new Page<AttrAttrgroupRelation>(request, response), attrAttrgroupRelation);
		return Result.ok(page);
	}

	/**
	 * 属性&属性分组关联详情
	 */
	@ApiOperation(value = "属性&属性分组关联详情", notes = "根据id获取属性&属性分组关联详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "属性&属性分组关联id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		AttrAttrgroupRelation attrAttrgroupRelation = attrAttrgroupRelationService.get(id);
		return Result.ok(attrAttrgroupRelation);
	}

	/**
	 * 属性&属性分组关联保存
	 */
	@ApiOperation(value = "属性&属性分组关联保存", notes = "属性&属性分组关联如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody AttrAttrgroupRelation attrAttrgroupRelation) {
		try {
			attrAttrgroupRelationService.insert(attrAttrgroupRelation);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * 属性&属性分组关联修改
	 */
	@ApiOperation(value = "属性&属性分组关联修改", notes = "属性&属性分组关联修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody AttrAttrgroupRelation attrAttrgroupRelation) {
		try {
			attrAttrgroupRelationService.update(attrAttrgroupRelation);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * 属性&属性分组关联删除
	 */
	@ApiOperation(value = "属性&属性分组关联删除", notes = "属性&属性分组关联删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "属性&属性分组关联id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			attrAttrgroupRelationService.deleteAll(ids, new AttrAttrgroupRelation());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
