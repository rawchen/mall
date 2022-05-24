package com.rawchen.mall.search.controller;

import com.rawchen.common.to.es.SkuEsModel;
import com.rawchen.mall.search.service.MallService;
import com.rawchen.mall.search.vo.SearchParam;
import com.rawchen.mall.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Title: SearchController</p>
 * Description：
 * date：2022/1/12 21:46
 */
@Controller
public class SearchController {

	@Autowired
	private MallService mallService;

	@GetMapping("/list.html")
	public String listPage(SearchParam searchParam, Model model, HttpServletRequest request){

		// 获取路径原生的查询属性
		searchParam.set_queryString(request.getQueryString());
		// ES中检索到的结果 传递给页面
		SearchResult result = mallService.search(searchParam);
		model.addAttribute("result", result);
		List<SkuEsModel> collect = result.getProducts().stream().sorted(Comparator.comparing(SkuEsModel::getSaleCount)).collect(Collectors.toList());
		List<SkuEsModel> collectNew = new ArrayList<>();
		for (int i = 0; i < collect.size(); i++) {
			if (i >= 3) {
				break;
			}
			collectNew.add(collect.get(i));
		}
		model.addAttribute("resultHot", collectNew);
		return "list";
	}
}
