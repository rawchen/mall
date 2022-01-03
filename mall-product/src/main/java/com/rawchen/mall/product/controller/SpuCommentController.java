package com.rawchen.mall.product.controller;

import com.rawchen.mall.product.entity.SpuComment;
import com.rawchen.mall.product.service.SpuCommentService;
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
 * [商品评价]控制器
 * Created by RawChen on 2021-12-30.
 **/
@RestController
@RequestMapping(value = "/spuComment")
@Api(tags = "[PC端]商品评价接口",  value = "SpuCommentController")
public class SpuCommentController extends BaseController {

	@Autowired
	private SpuCommentService spuCommentService;

	/**
	 * 商品评价列表
	 */
	@ApiOperation(value = "商品评价列表", notes = "商品评价列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "skuId", value = "sku_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "spuId", value = "spu_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "spuName", value = "商品名字", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "memberNickName", value = "会员昵称", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "star", value = "星级", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "memberIp", value = "会员ip", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "showStatus", value = "显示状态[0-不显示，1-显示]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "spuAttributes", value = "购买时属性组合", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "likesCount", value = "点赞数", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "replyCount", value = "回复数", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "resources", value = "评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "content", value = "内容", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "memberIcon", value = "用户头像", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "commentType", value = "评论类型[0 - 对商品的直接评论，1 - 对评论的回复]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore SpuComment spuComment) {
		List<SpuComment> list = spuCommentService.findList(spuComment);
		return Result.ok(list);
	}

	/**
	 * 商品评价分页列表
	 */
	@ApiOperation(value = "商品评价分页列表", notes = "商品评价分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "skuId", value = "sku_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "spuId", value = "spu_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "spuName", value = "商品名字", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "memberNickName", value = "会员昵称", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "star", value = "星级", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "memberIp", value = "会员ip", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "showStatus", value = "显示状态[0-不显示，1-显示]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "spuAttributes", value = "购买时属性组合", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "likesCount", value = "点赞数", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "replyCount", value = "回复数", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "resources", value = "评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "content", value = "内容", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "memberIcon", value = "用户头像", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "commentType", value = "评论类型[0 - 对商品的直接评论，1 - 对评论的回复]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore SpuComment spuComment, HttpServletRequest request, HttpServletResponse response) {
		Page<SpuComment> page = spuCommentService.findPage(new Page<SpuComment>(request, response), spuComment);
		return Result.ok(page);
	}

	/**
	 * 商品评价详情
	 */
	@ApiOperation(value = "商品评价详情", notes = "根据id获取商品评价详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "商品评价id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		SpuComment spuComment = spuCommentService.get(id);
		return Result.ok(spuComment);
	}

	/**
	 * 商品评价保存
	 */
	@ApiOperation(value = "商品评价保存", notes = "商品评价如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody SpuComment spuComment) {
		try {
			spuCommentService.insert(spuComment);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * 商品评价修改
	 */
	@ApiOperation(value = "商品评价修改", notes = "商品评价修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody SpuComment spuComment) {
		try {
			spuCommentService.update(spuComment);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * 商品评价删除
	 */
	@ApiOperation(value = "商品评价删除", notes = "商品评价删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "商品评价id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			spuCommentService.deleteAll(ids, new SpuComment());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
