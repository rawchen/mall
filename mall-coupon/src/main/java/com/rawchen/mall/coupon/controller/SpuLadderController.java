package com.rawchen.mall.coupon.controller;

import com.rawchen.mall.coupon.entity.SpuLadder;
import com.rawchen.mall.coupon.service.SpuLadderService;
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
 * [商品阶梯价格]控制器
 * Created by RawChen on 2021-11-22.
 **/
@RestController
@RequestMapping(value = "/api/v1/spuLadder")
@Api(tags = "[PC端]商品阶梯价格接口",  value = "SpuLadderController")
public class SpuLadderController extends BaseController {

	@Autowired
	private SpuLadderService spuLadderService;

	/**
	 * 商品阶梯价格列表
	 */
	@ApiOperation(value = "商品阶梯价格列表", notes = "商品阶梯价格列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "spuId", value = "spu_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "fullCount", value = "满几件", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "discount", value = "打几折", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "price", value = "折后价", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "addOther", value = "是否叠加其他优惠[0-不可叠加，1-可叠加]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result findAll(@ApiIgnore SpuLadder spuLadder) {
		List<SpuLadder> list = spuLadderService.findList(spuLadder);
		return Result.ok(list);
	}

	/**
	 * 商品阶梯价格分页列表
	 */
	@ApiOperation(value = "商品阶梯价格分页列表", notes = "商品阶梯价格分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页大小", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "spuId", value = "spu_id", required = false, dataType = "Long"),
			@ApiImplicitParam(paramType = "query", name = "fullCount", value = "满几件", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "discount", value = "打几折", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "price", value = "折后价", required = false, dataType = "java.lang.Float"),
			@ApiImplicitParam(paramType = "query", name = "addOther", value = "是否叠加其他优惠[0-不可叠加，1-可叠加]", required = false, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", required = false, dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "updateTime", value = "修改时间", required = false, dataType = "Date")
	})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Result findPage(@ApiIgnore SpuLadder spuLadder, HttpServletRequest request, HttpServletResponse response) {
		Page<SpuLadder> page = spuLadderService.findPage(new Page<SpuLadder>(request, response), spuLadder);
		return Result.ok(page);
	}

	/**
	 * 商品阶梯价格详情
	 */
	@ApiOperation(value = "商品阶梯价格详情", notes = "根据id获取商品阶梯价格详情信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "商品阶梯价格id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result get(@PathVariable("id") Integer id) {
		SpuLadder spuLadder = spuLadderService.get(id);
		return Result.ok(spuLadder);
	}

	/**
	 * 商品阶梯价格保存
	 */
	@ApiOperation(value = "商品阶梯价格保存", notes = "商品阶梯价格如果数据库id自增就无需传入id")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(@RequestBody SpuLadder spuLadder) {
		try {
			spuLadderService.insert(spuLadder);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败：", e);
			return Result.error("保存异常");
		}
	}

	/**
	 * 商品阶梯价格修改
	 */
	@ApiOperation(value = "商品阶梯价格修改", notes = "商品阶梯价格修改")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result update(@RequestBody SpuLadder spuLadder) {
		try {
			spuLadderService.update(spuLadder);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败：", e);
			return Result.error("修改异常");
		}
	}

	/**
	 * 商品阶梯价格删除
	 */
	@ApiOperation(value = "商品阶梯价格删除", notes = "商品阶梯价格删除")
	@ApiImplicitParam(paramType = "path", name = "id", value = "商品阶梯价格id", required = true, dataType = "int")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String ids) {
		try {
			spuLadderService.deleteAll(ids, new SpuLadder());
			return Result.ok("删除成功");
		} catch (Exception e) {
			logger.error("删除失败：", e);
			return Result.error("删除异常");
		}
	}
}
