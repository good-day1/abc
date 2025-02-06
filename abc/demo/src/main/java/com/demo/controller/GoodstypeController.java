package com.demo.controller;


import com.demo.common.Result;
import com.demo.entity.Goodstype;
import com.demo.entity.Storage;
import com.demo.mapper.GoodstypeMapper;
import com.demo.mapper.StorageMapper;
import com.demo.service.IGoodstypeService;
import com.demo.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 123
 * @since 2023-11-17
 */
@RestController
@RequestMapping("/goodstype")
public class GoodstypeController {
    @Autowired
    private IGoodstypeService goodstypeService;
    @Autowired
    private GoodstypeMapper goodstypeMapper;
    @PostMapping("/save")
    public Result save(@RequestBody Goodstype goodstype){
        return goodstypeService.save(goodstype)?Result.suc():Result.fail();
    }

    //改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Goodstype goodstype){
        return goodstypeService.updateById(goodstype);
    }
    @PostMapping("/update")
    public Result update(@RequestBody Goodstype goodstype){
        return goodstypeService.updateById(goodstype)?Result.suc():Result.fail();
    }
    //查
    @GetMapping("/listp")
    public Map<String, Object> search(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize,
                                      @RequestParam String name

    ){

        pageNum = (pageNum - 1) * pageSize;
        name = "%" + name + "%";
        List<Storage> data = goodstypeMapper.selectPage(pageNum, pageSize, name);
        Integer total = goodstypeMapper.selectTotal(name);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
//        if(user.getName()!=null){
//            lambdaQueryWrapper.like(User::getName,user.getName());
//        }
//        System.out.println(Result.suc(userService.list(lambdaQueryWrapper)));
//        return Result.suc(userService.list(lambdaQueryWrapper));

    }
    @GetMapping("/deletebyid")
    public Result delete(int id){
        return goodstypeService.removeById(id)?Result.suc():Result.fail();
    }
    @GetMapping("/list")
    public Result list(){
        List list=goodstypeService.list();
        return Result.suc(list);
    }
}
