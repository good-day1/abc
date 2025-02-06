package com.demo.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.common.QueryPageParam;
import com.demo.common.Result;
import com.demo.entity.Goods;
import com.demo.entity.Storage;
import com.demo.entity.User;
import com.demo.mapper.GoodsMapper;
import com.demo.mapper.StorageMapper;
import com.demo.service.IGoodsService;
import com.demo.service.IGoodstypeService;
import com.demo.service.IStorageService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private GoodsMapper goodsMapper;
    @PostMapping("/save")
    public Result save(@RequestBody Goods goods){
        return goodsService.save(goods)?Result.suc():Result.fail();
    }
    //改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Goods goods){
        return goodsService.updateById(goods);
    }
    @PostMapping("/update")
    public Result update(@RequestBody Goods goods){
        return goodsService.updateById(goods)?Result.suc():Result.fail();
    }
    //查
    @GetMapping("/listp")
    public Map<String, Object> search(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize,
                                      @RequestParam String name,@RequestParam String goodstype, @RequestParam String storage1
                                      ){
        pageNum = (pageNum - 1) * pageSize;
        name = "%" + name + "%";
        List<Goods> data = goodsMapper.selectPage(pageNum, pageSize, name,goodstype,storage1);
        Integer total = goodsMapper.selectTotal(name,goodstype,storage1);
        System.out.println("total="+total);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }
    @GetMapping("/deletebyid")
    public Result delete(int id){
        return goodsService.removeById(id)?Result.suc():Result.fail();
    }
}
