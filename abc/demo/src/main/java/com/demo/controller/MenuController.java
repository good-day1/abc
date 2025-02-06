package com.demo.controller;

import com.demo.common.Result;
import com.demo.entity.User;
import com.demo.mapper.MenuMapper;
import com.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.demo.entity.Menu;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuMapper menuMapper;
    @GetMapping("/list")
    public Result list(@RequestParam String roleId){

       List list=menuService.lambdaQuery().like(Menu::getMenuright,roleId).list();
        return Result.suc(list);
    }
}
