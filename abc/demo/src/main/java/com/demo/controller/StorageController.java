package com.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.demo.common.Result;
import com.demo.entity.Menu;
import com.demo.entity.Storage;
import com.demo.entity.User;
import com.demo.mapper.MenuMapper;
import com.demo.mapper.StorageMapper;
import com.demo.service.IStorageService;
import com.demo.service.MenuService;
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
@RequestMapping("/storage")
public class StorageController {
    @Autowired
    private IStorageService storageService;
    @Autowired
    private StorageMapper storageMapper;
    @PostMapping("/save")
    public Result save(@RequestBody Storage storage){
        return storageService.save(storage)?Result.suc():Result.fail();
    }

    //改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Storage storage){
        return storageService.updateById(storage);
    }
    @PostMapping("/update")
    public Result update(@RequestBody Storage storage){
        return storageService.updateById(storage)?Result.suc():Result.fail();
    }
    //查
    @GetMapping("/listp")
    public Map<String, Object> search(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize,
                                      @RequestParam String name

    ){

        pageNum = (pageNum - 1) * pageSize;
        name = "%" + name + "%";
        List<Storage> data = storageMapper.selectPage(pageNum, pageSize, name);
        Integer total = storageMapper.selectTotal(name);
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
        return storageService.removeById(id)?Result.suc():Result.fail();
    }


    @GetMapping("/list")
    public Result list(){
        List list=storageService.list();
        return Result.suc(list);
    }

}
