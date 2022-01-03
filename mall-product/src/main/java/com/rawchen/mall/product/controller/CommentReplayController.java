package com.rawchen.mall.product.controller;

import com.rawchen.mall.product.entity.CommentReplay;
import com.rawchen.mall.product.service.CommentReplayService;
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
 * [商品评价回复关系]控制器
 * Created by RawChen on 2021-12-30.
 **/
@RestController
@RequestMapping(value = "/commentReplay")
@Api(tags = "[PC端]商品评价回复关系接口",  value = "CommentReplayController")
public class CommentReplayController extends BaseController {

	@Autowired
	private CommentReplayService commentReplayService;

	/**
	 * 商品评价回复关系列表
	 */
	@ApiOperation(value = "商品评价回复关系列表", notes = "商品评价回复关系列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "commentId", value = "评论id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "replyId", value = "回复id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore CommentReplay commentReplay) {
		List<CommentReplay> list = commentReplayService.findList(commentReplay);
		return Result.ok(list);
	}

	/**
	 * 商品评价回复关系分页列表
	 */
	@ApiOperation(value = "商品评价回复关系分页列表", notes = "商品评价回复关系分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "commentId", value = "评论id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "replyId", value = "回复id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore CommentReplay commentReplay, HttpServletRequest request, HttpServletResponse response) {
		Page<CommentReplay> page = commentReplayService.findPage(new Page<CommentReplay>(request, response), commentReplay);
		return Result.ok(page);
	}

	/**
	 * 商品评价回复关系详情
	 */
	@ApiOperation(value = "商品评价回复关系详情", notes = "根据id获取商品评价回复关系详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "商品评价回复关系id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		CommentReplay commentReplay = commentReplayService.get(id);
		return Result.ok(commentReplay);
	}

	/**
	 * 商品评价回复关系保存
	 */
	@ApiOperation(value = "商品评价回复关系保存", notes = "商品评价回复关系如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody CommentReplay commentReplay) {
		try {
			commentReplayService.insert(commentReplay);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * 商品评价回复关系修改
	 */
	@ApiOperation(value = "商品评价回复关系修改", notes = "商品评价回复关系修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody CommentReplay commentReplay) {
		try {
			commentReplayService.update(commentReplay);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * 商品评价回复关系删除
	 */
	@ApiOperation(value = "商品评价回复关系删除", notes = "商品评价回复关系删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "商品评价回复关系id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			commentReplayService.deleteAll(ids, new CommentReplay());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
