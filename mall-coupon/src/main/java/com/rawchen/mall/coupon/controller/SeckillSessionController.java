package com.rawchen.mall.coupon.controller;

import com.rawchen.mall.coupon.entity.SeckillSession;
import com.rawchen.mall.coupon.service.SeckillSessionService;
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
 * [秒杀活动场次]控制器
 * Created by RawChen on 2021-11-22.
 **/
@RestController
@RequestMapping(value = "/seckillSession")
@Api(tags = "[PC端]秒杀活动场次接口",  value = "SeckillSessionController")
public class SeckillSessionController extends BaseController {

	@Autowired
	private SeckillSessionService seckillSessionService;

	/**
	 * 秒杀活动场次列表
	 */
	@ApiOperation(value = "秒杀活动场次列表", notes = "秒杀活动场次列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "name", value = "场次名称", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "startTime", value = "每日开始时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "endTime", value = "每日结束时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "status", value = "启用状态", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore SeckillSession seckillSession) {
		List<SeckillSession> list = seckillSessionService.findList(seckillSession);
		return Result.ok(list);
	}

	/**
	 * 秒杀活动场次分页列表
	 */
	@ApiOperation(value = "秒杀活动场次分页列表", notes = "秒杀活动场次分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "name", value = "场次名称", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "startTime", value = "每日开始时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "endTime", value = "每日结束时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "status", value = "启用状态", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore SeckillSession seckillSession, HttpServletRequest request, HttpServletResponse response) {
		Page<SeckillSession> page = seckillSessionService.findPage(new Page<SeckillSession>(request, response), seckillSession);
		return Result.ok(page);
	}

	/**
	 * 秒杀活动场次详情
	 */
	@ApiOperation(value = "秒杀活动场次详情", notes = "根据id获取秒杀活动场次详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "秒杀活动场次id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		SeckillSession seckillSession = seckillSessionService.get(id);
		return Result.ok(seckillSession);
	}

	/**
	 * 秒杀活动场次保存
	 */
	@ApiOperation(value = "秒杀活动场次保存", notes = "秒杀活动场次如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody SeckillSession seckillSession) {
		try {
			seckillSessionService.insert(seckillSession);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * 秒杀活动场次修改
	 */
	@ApiOperation(value = "秒杀活动场次修改", notes = "秒杀活动场次修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody SeckillSession seckillSession) {
		try {
			seckillSessionService.update(seckillSession);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * 秒杀活动场次删除
	 */
	@ApiOperation(value = "秒杀活动场次删除", notes = "秒杀活动场次删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "秒杀活动场次id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			seckillSessionService.deleteAll(ids, new SeckillSession());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
