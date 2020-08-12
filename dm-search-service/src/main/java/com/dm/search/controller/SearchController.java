package com.dm.search.controller;

import com.dm.search.common.CommonPage;
import com.dm.search.common.CommonResult;
import com.dm.search.model.EsBook;
import com.dm.search.model.EsBookRelatedInfo;
import com.dm.search.service.DmSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

/**
 * @author dm
 * @className SearchController
 * @description 搜索服务接口暴露
 * @date 2020/8/11
 * @since JDK1.8
 */
@Slf4j
@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private DmSearchService dmSearchService;

    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<BigInteger> importAllList() {
        BigInteger count = dmSearchService.importBookIndex();
        return CommonResult.success(count, "添加成功");
    }

    /**
     * 简单搜索(根据关键词搜索)
     *
     * @param keyword  关键词
     * @param pageNum  页码
     * @param pageSize 每页数
     * @return
     */
    @RequestMapping(value = "/simple", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<EsBook>> search(@RequestParam(required = false) String keyword,
                                                   @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        Page<EsBook> esProductPage = dmSearchService.search(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(esProductPage));
    }

    /**
     * 获取搜索词相关分类（eq: 以搜索词过滤，过滤结果聚合分类名称）
     *
     * @param keyword 关键词
     * @return
     */
    @RequestMapping(value = "/searchRelatedInfo", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<EsBookRelatedInfo> searchRelatedInfo(@RequestParam(required = false) String keyword) {
        EsBookRelatedInfo esBookRelatedInfo = dmSearchService.searchRelatedInfo(keyword);
        return CommonResult.success(esBookRelatedInfo);
    }
}
